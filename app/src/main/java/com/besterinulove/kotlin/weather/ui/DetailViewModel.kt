package com.besterinulove.kotlin.weather.ui

import androidx.lifecycle.*
import com.besterinulove.kotlin.weather.data.DetailRepository

class DetailViewModel(
    private val repository: DetailRepository
) : ViewModel() {
    private val elementIndex = MutableLiveData<Int>()
    val element = elementIndex.switchMap {
        liveData {
            emit(repository.getElementWithIndex(it))
        }
    }

    fun getElementWithIndex(index: Int) {
        elementIndex.value = index
    }
}