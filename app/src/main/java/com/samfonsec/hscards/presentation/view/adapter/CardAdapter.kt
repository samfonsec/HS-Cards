package com.samfonsec.hscards.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.samfonsec.hscards.databinding.ItemCardBinding
import com.samfonsec.hscards.domain.model.Card
import com.samfonsec.hscards.presentation.extension.loadImage

class CardAdapter(
    private val onItemClicked: (Card, ImageView) -> Unit
) : ListAdapter<Card, CardAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)) { card, imageView ->
            onItemClicked(card, imageView)
        }
    }

    class ViewHolder(private val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(card: Card, onItemClicked: (Card, ImageView) -> Unit) {
            with(binding) {
                imageCard.loadImage(card.img)
                textName.text = card.name
                itemView.setOnClickListener {
                    onItemClicked(card, imageCard)
                }
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