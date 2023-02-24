package com.samfonsec.hscards.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.samfonsec.hscards.databinding.ItemCardBinding
import com.samfonsec.hscards.domain.model.Card
import com.samfonsec.hscards.presentation.extension.loadImage

class CardAdapter(
    private val onItemClicked: (Card) -> Unit
) : ListAdapter<Card, CardAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let { serie ->
            holder.bind(serie)
            holder.itemView.setOnClickListener { onItemClicked(serie) }
        }
    }

    class ViewHolder(private val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(card: Card) {
            with(binding) {
                imageCard.loadImage(card.img)
                textName.text = card.name
            }
        }
    }

    companion object {
        private val diffCallback: DiffUtil.ItemCallback<Card> =
            object : DiffUtil.ItemCallback<Card>() {
                override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
                    return oldItem.cardId == newItem.cardId
                }

                override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
                    return oldItem == newItem
                }
            }
    }
}