package com.samfonsec.hscards.presentation.view.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.samfonsec.hscards.domain.model.Card
import com.samfonsec.hscards.domain.utils.Constants.ARG_CARD
import com.samfonsec.hscards.presentation.extension.loadImage

class CardDetailsFragment : Fragment() {

   /* private var _binding: FragmentCardDetailsBinding? = null
    private val binding get() = _binding!!
    private var card: Card? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        card = getCardFromArguments()
        setupView()
    }

    private fun setupView() {
        binding.imageCard.loadImage(card?.img)
    }

    @Suppress("DEPRECATION")
    private fun getCardFromArguments() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        arguments?.getParcelable(ARG_CARD, Card::class.java)
    else
        arguments?.getParcelable(ARG_CARD)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }*/
}
