package com.android.catalog.presentation.item

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.android.catalog.data.Item
import com.android.catalog.domain.usecase.GetItemsUseCase
import javax.inject.Inject

class ItemsViewModel @Inject constructor(private val getItemsUseCase: GetItemsUseCase) : ViewModel() {

    private val TAG = ItemsViewModel::class.java.simpleName
    val itemListReceivedLiveData = MutableLiveData<List<Item>>()
    val isLoad = MutableLiveData<Boolean>()

    init {
        isLoad.value = false
    }


    fun loadItems(id: Long?) {
        if (id == null) return
        getItemsUseCase.saveCategoryId(id)
        getItemsUseCase.execute(
            onSuccess = {
                isLoad.value = true
                itemListReceivedLiveData.value = it
            },
            onError = {
                it.printStackTrace()
            }
        )
    }
}