package com.samfonsec.hscards.presentation.view.activity

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.samfonsec.hscards.R
import com.samfonsec.hscards.databinding.ActivityCardDetailsBinding
import com.samfonsec.hscards.domain.model.Card
import com.samfonsec.hscards.domain.utils.Constants
import com.samfonsec.hscards.presentation.extension.loadImage
import com.samfonsec.hscards.presentation.extension.setTextFromHtml
import com.samfonsec.hscards.presentation.model.LabelValueItem
import com.samfonsec.hscards.presentation.view.adapter.DetailsAdapter

class CardDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCardDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getCard()?.let {
            setupView(it)
        }
    }

    @Suppress("DEPRECATION")
    private fun getCard() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        intent?.getParcelableExtra(Constants.ARG_CARD, Card::class.java)
    else
        intent?.getParcelableExtra(Constants.ARG_CARD)


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun setupView(card: Card) {
        with(binding) {
            setSupportActionBar(toolbar)
            textFlavor.text = card.flavor
            imageCard.loadImage(card.img)
            textDescription.setTextFromHtml(card.text.orEmpty())
            recyclerviewDetails.adapter = DetailsAdapter().apply {
                submitList(card.details())
            }
        }
        setupActionBar(card.name.orEmpty())
    }

    private fun setupActionBar(actionBarTitle: String) {
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            title = actionBarTitle
        }
    }

    private fun Card.details(): List<LabelValueItem> = listOf(
        LabelValueItem(getString(R.string.label_cost), cost?.toString()),
        LabelValueItem(getString(R.string.label_attack), attack?.toString()),
        LabelValueItem(getString(R.string.label_health), health?.toString()),
        LabelValueItem(getString(R.string.label_durability), durability?.toString()),
        LabelValueItem(getString(R.string.label_type), type),
        LabelValueItem(getString(R.string.label_faction), faction),
        LabelValueItem(getString(R.string.label_rarity), rarity),
        LabelValueItem(getString(R.string.label_set), cardSet),
    ).filter {
        it.value != null
    }
}
