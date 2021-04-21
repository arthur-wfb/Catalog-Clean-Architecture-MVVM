package com.android.catalog.presentation.category

import android.arch.lifecycle.MutableLiveData
import com.android.catalog.data.source.Category


class CategoryViewModel {

    private val TAG = CategoryViewModel::class.java.simpleName
    val isLoad = MutableLiveData<Boolean>()
    val categoryData = MutableLiveData<Category>()

    constructor(category: Category) {
        this.categoryData.value = category
    }

    val category: Category? get() = categoryData.value

    fun set(category: Category) = {
        categoryData.value = category
    }

}