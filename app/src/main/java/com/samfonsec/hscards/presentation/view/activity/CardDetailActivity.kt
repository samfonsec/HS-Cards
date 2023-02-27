package com.samfonsec.hscards.presentation.view.activity

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.samfonsec.hscards.R
import com.samfonsec.hscards.databinding.ActivityCardDetailsBinding
import com.samfonsec.hscards.databinding.LabelValueViewBinding
import com.samfonsec.hscards.domain.model.Card
import com.samfonsec.hscards.domain.utils.Constants
import com.samfonsec.hscards.presentation.extension.loadImage
import com.samfonsec.hscards.presentation.extension.setTextFromHtml

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
            setLabelValue(cost, getString(R.string.label_cost), card.cost?.toString())
            setLabelValue(attack, getString(R.string.label_attack), card.attack?.toString())
            setLabelValue(health, getString(R.string.label_health), card.health?.toString())
            setLabelValue(durability, getString(R.string.label_durability), card.durability?.toString())
            setLabelValue(type, getString(R.string.label_type), card.type)
            setLabelValue(rarity, getString(R.string.label_rarity), card.rarity)
            setLabelValue(faction, getString(R.string.label_faction), card.faction)
            setLabelValue(set, getString(R.string.label_set), card.cardSet)
        }
        setupActionBar(card.name.orEmpty())
    }

    private fun setLabelValue(lvBinding: LabelValueViewBinding, label: String, value: String?) {
        with(lvBinding) {
            this.label.text = label
            this.value.text = value
            root.isVisible = !value.isNullOrEmpty()
        }
    }

    private fun setupActionBar(actionBarTitle: String) {
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            title = actionBarTitle
        }
    }

    @Suppress("DEPRECATION")
    private fun getCard() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        intent?.getParcelableExtra(Constants.ARG_CARD, Card::class.java)
    else
        intent?.getParcelableExtra(Constants.ARG_CARD)

}
