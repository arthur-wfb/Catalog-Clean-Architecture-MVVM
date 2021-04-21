package com.android.catalog.presentation.item

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.android.catalog.data.Item

class ItemViewModel(val item: Item) :ViewModel() {

    private val TAG = ItemViewModel::class.java.simpleName
    val isLoad = MutableLiveData<Boolean>()
    val itemData = MutableLiveData<Item>()

    init {
        this.itemData.value = item
    }

    fun set(item: Item) = {
        itemData.value = item
    }

}