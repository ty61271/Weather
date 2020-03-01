package com.besterinulove.kotlin.weather.ui.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.besterinulove.kotlin.weather.R

class PictureViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
    companion object{
        fun create(parent: ViewGroup): PictureViewHolder =
           PictureViewHolder(LayoutInflater.from(parent.context).inflate(
               R.layout.row_picture,parent,false
           ))
    }
}