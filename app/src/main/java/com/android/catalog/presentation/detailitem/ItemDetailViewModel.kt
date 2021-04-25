package com.android.catalog.presentation.detailitem

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.android.catalog.data.Item
import com.android.catalog.domain.usecase.GetItemDetailUseCase
import javax.inject.Inject

class ItemDetailViewModel @Inject constructor(private val getItemDetailUseCase: GetItemDetailUseCase) : ViewModel() {

    private val TAG = ItemDetailViewModel::class.java.simpleName
    val itemData = MutableLiveData<Item>()
    val isLoad = MutableLiveData<Boolean>()
    val isFavorite = MutableLiveData<Boolean>()

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

    fun updateFavoriteStatus() {
        if (itemData.value == null) return
        if (isFavorite.value == true) {
            isFavorite.value = false
            getItemDetailUseCase.deleteAsFavorite(itemData.value!!)
        } else {
            isFavorite.value = true
            getItemDetailUseCase.addAsFavorite(itemData.value!!)
        }
    }

    fun checkFavoriteStatus(itemId: Long) {
        isFavorite.value = getItemDetailUseCase.isFavorite(itemId)
    }
}