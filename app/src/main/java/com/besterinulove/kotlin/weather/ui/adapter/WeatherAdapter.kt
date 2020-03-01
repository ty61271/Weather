package com.besterinulove.kotlin.weather.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.besterinulove.kotlin.weather.db.entity.WeatherElementEntity
import com.besterinulove.kotlin.weather.db.entity.WeatherRelation
import com.besterinulove.kotlin.weather.ui.widget.ElementViewHolder
import com.besterinulove.kotlin.weather.ui.widget.PictureViewHolder

class WeatherAdapter(
    private val onClickListener:(Int)->Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var elements: List<Type> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ELEMENT_TYPE -> ElementViewHolder.create(parent)
            else -> PictureViewHolder.create(parent)
        }
    }

    override fun getItemCount() = if (elements.isNotEmpty()) elements.size - 1 else 0

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ElementViewHolder->holder.onBind(elements[position] as Type.Element,onClickListener)
        }
    }

    override fun getItemViewType(position: Int) = when (elements[position]) {
        is Type.Element -> ELEMENT_TYPE
        is Type.PICTURE -> PICTURE_TYPE
    }

    fun submit(weatherRelation: WeatherRelation?) {
        weatherRelation?.let {
            elements = it.elements.flatMap {elementEntity->
                listOf(Type.Element(elementEntity), Type.PICTURE)
            }
            notifyDataSetChanged()
        }
    }

    companion object {
        private const val ELEMENT_TYPE = 100
        private const val PICTURE_TYPE = 101
    }
}

sealed class Type {
    data class Element(val element: WeatherElementEntity) : Type()
    object PICTURE : Type()
}