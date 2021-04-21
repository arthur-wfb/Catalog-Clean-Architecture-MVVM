package com.android.catalog.presentation.detailitem

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.android.catalog.data.Item
import com.android.catalog.domain.usecase.GetItemDetailUseCase
import javax.inject.Inject

class ItemDetailViewModel@Inject constructor(private val getItemDetailUseCase: GetItemDetailUseCase) : ViewModel() {

    private val TAG = ItemDetailViewModel::class.java.simpleName
    val itemData = MutableLiveData<Item>()
    val isLoad = MutableLiveData<Boolean>()

    init {
        isLoad.value = false
    }

    fun getDetail(id: Long?) {
        if (id == null) return
        getItemDetailUseCase.saveItemId(id)
        getItemDetailUseCase.execute(
            onSuccess = {
                isLoad.value = true
                itemData.value = it
            },
            onError = {
                it.printStackTrace()
            }
        )
    }
}