package com.samfonsec.hscards.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.samfonsec.hscards.databinding.LabelValueViewBinding
import com.samfonsec.hscards.presentation.model.LabelValueItem

class DetailsAdapter : ListAdapter<LabelValueItem, DetailsAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LabelValueViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: LabelValueViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: LabelValueItem) {
            with(binding) {
                label.text = item.label
                value.text = item.value
            }
        }
    }

    companion object {
        private val diffCallback: DiffUtil.ItemCallback<LabelValueItem> =
            object : DiffUtil.ItemCallback<LabelValueItem>() {
                override fun areItemsTheSame(oldItem: LabelValueItem, newItem: LabelValueItem): Boolean {
                    return oldItem.label == newItem.label
                }

                override fun areContentsTheSame(oldItem: LabelValueItem, newItem: LabelValueItem): Boolean {
                    return oldItem == newItem
                }
            }
    }
}