package com.besterinulove.kotlin.weather.ui.widget

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.besterinulove.kotlin.weather.databinding.RowElementBinding
import com.besterinulove.kotlin.weather.ui.adapter.Type

class ElementViewHolder(
    private val binding: RowElementBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): ElementViewHolder =
            ElementViewHolder(
                RowElementBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }

    fun onBind(elementType: Type.Element, onClickListener: (Int) -> Unit) {
        binding.element = elementType.element
        itemView.setOnClickListener {
            onClickListener(elementType.element.index)
        }
        binding.executePendingBindings()
    }
}