package com.android.catalog.presentation.category

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.android.catalog.data.source.Category
import com.android.catalog.domain.usecase.GetCategoriesUseCase
import javax.inject.Inject


class CategoriesViewModel @Inject constructor(private val getCategoryListUseCase: GetCategoriesUseCase) : ViewModel() {

    private val TAG = CategoriesViewModel::class.java.simpleName
    val categoriesReceivedLiveData = MutableLiveData<List<Category>>()
    val isLoad = MutableLiveData<Boolean>()
    val categoryData = MutableLiveData<Category>()

    init {
        isLoad.value = false
    }

    val category: Category? get() = categoryData.value

    fun set(category: Category) = {
        categoryData.value = category
    }

    fun loadCategories() {
        getCategoryListUseCase.execute(
            onSuccess = {
                isLoad.value = true
                categoriesReceivedLiveData.value = it
            },
            onError = {
                it.printStackTrace()
            }
        )
    }
}