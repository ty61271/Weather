package com.besterinulove.kotlin.weather.ui

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.besterinulove.kotlin.weather.db.entity.WeatherElementEntity

@BindingAdapter("element")
fun TextView.setParameterInfo(element: WeatherElementEntity?) {
    element?.let {
        this.text = element.parameterName + element.parameterUnit
    }
}