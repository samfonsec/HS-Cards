package com.samfonsec.hscards.presentation.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.samfonsec.hscards.R
import com.samfonsec.hscards.databinding.FragmentCardsListBinding
import com.samfonsec.hscards.domain.model.Card
import com.samfonsec.hscards.domain.utils.Constants.ARG_CARD
import com.samfonsec.hscards.domain.utils.Constants.ARG_TITLE
import com.samfonsec.hscards.presentation.extension.hide
import com.samfonsec.hscards.presentation.extension.show
import com.samfonsec.hscards.presentation.view.activity.CardDetailActivity
import com.samfonsec.hscards.presentation.view.adapter.CardAdapter
import com.samfonsec.hscards.presentation.viewModel.CardsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardsListFragment : Fragment() {

    private var _binding: FragmentCardsListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CardsViewModel by viewModel()

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

    private fun setupView() {
        binding.tabsClasses.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewModel.selectedTab = tab.position
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
        binding.tabsClasses.show()
        binding.recyclerviewCards.adapter = CardAdapter {
            onItemClicked(it)
        }.apply {
            submitList(cards)
        }
    }

    private fun onItemClicked(card: Card) {
        startActivity(Intent(context, CardDetailActivity::class.java).putExtra(ARG_CARD, card))

        /*findNavController().navigate(
            R.id.action_cardsListFragment_to_cardDetailsFragment,
            bundleOf(
                ARG_CARD to card,
                ARG_TITLE to card.name
            )
        )*/
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
            recyclerviewCards.isVisible = !isLoading
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
        val selectedClass = currentTab()?.text.toString()
        viewModel.getCards(selectedClass)
    }

    private fun currentTab() = binding.tabsClasses.getTabAt(viewModel.selectedTab)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}