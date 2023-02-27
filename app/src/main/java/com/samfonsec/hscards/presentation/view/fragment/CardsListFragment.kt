package com.samfonsec.hscards.presentation.view.fragment

import android.app.ActivityOptions
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.samfonsec.hscards.R
import com.samfonsec.hscards.databinding.FragmentCardsListBinding
import com.samfonsec.hscards.domain.model.Card
import com.samfonsec.hscards.domain.utils.Constants.ARG_CARD
import com.samfonsec.hscards.presentation.extension.hide
import com.samfonsec.hscards.presentation.extension.show
import com.samfonsec.hscards.presentation.view.activity.CardDetailActivity
import com.samfonsec.hscards.presentation.view.activity.MainActivity
import com.samfonsec.hscards.presentation.view.adapter.CardAdapter
import com.samfonsec.hscards.presentation.viewModel.CardsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardsListFragment : Fragment() {

    private var _binding: FragmentCardsListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CardsViewModel by viewModel()
    private var currentClass = ""
    private val gridLayoutManager by lazy {
        GridLayoutManager(context, PORTRAIT_SPAN_COUNT)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        subscribeUi()
        if (viewModel.onClassesResult.value == null) {
            viewModel.getClasses()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == ORIENTATION_LANDSCAPE)
            gridLayoutManager.spanCount = LANDSCAPE_SPAN_COUNT
        else if (newConfig.orientation == ORIENTATION_PORTRAIT)
            gridLayoutManager.spanCount = PORTRAIT_SPAN_COUNT
    }

    private fun setupView() {
        binding.recyclerviewCards.layoutManager = gridLayoutManager
        binding.tabsClasses.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                currentClass = tab.text?.toString().orEmpty()
                loadCards()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun subscribeUi() {
        with(viewModel) {
            onClassesResult.observe(viewLifecycleOwner) { onClassesLoaded(it) }
            onCardsResult.observe(viewLifecycleOwner) { onCardsLoaded(it) }
            onLoading.observe(viewLifecycleOwner) { onLoading(it) }
            onError.observe(viewLifecycleOwner) { onError(it) }
        }
    }

    private fun onCardsLoaded(cards: List<Card>) {
        with(binding) {
            recyclerviewCards.show()
            tabsClasses.show()
            recyclerviewCards.adapter = CardAdapter { card, imageView ->
                onItemClicked(card, imageView)
            }.apply {
                submitList(cards)
            }
        }
    }

    private fun onItemClicked(card: Card, imageView: ImageView) {
        val options = ActivityOptions
            .makeSceneTransitionAnimation(
                activity as MainActivity,
                imageView,
                getString(R.string.transition_card_image)
            )
        startActivity(
            Intent(context, CardDetailActivity::class.java).putExtra(ARG_CARD, card),
            options.toBundle()
        )
    }

    private fun onClassesLoaded(classes: List<String>) {
        with(binding.tabsClasses) {
            classes.forEach {
                addTab(newTab().setText(it))
            }
        }
    }

    private fun onLoading(isLoading: Boolean) {
        with(binding) {
            progress.isVisible = isLoading
            if (isLoading) {
                errorView.root.hide()
                recyclerviewCards.hide()
            }
        }
    }

    private fun onError(isCancelling: Boolean) {
        if (!isCancelling) {
            with(binding.errorView) {
                root.show()
                btTryAgain.setOnClickListener {
                    loadCards()
                    root.hide()
                }
            }
        }
    }

    private fun loadCards() {
        viewModel.getCards(currentClass)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val PORTRAIT_SPAN_COUNT = 2
        private const val LANDSCAPE_SPAN_COUNT = 4
    }
}