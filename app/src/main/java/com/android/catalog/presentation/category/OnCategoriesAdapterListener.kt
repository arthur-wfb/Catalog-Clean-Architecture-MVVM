package com.android.catalog.presentation.category

import com.android.catalog.data.source.Category

/**
 * To make an interaction between [CategoriesAdapter] & [CategoriesFragment]
 * */
interface OnCategoriesAdapterListener {

    fun showItems(category: Category)
}