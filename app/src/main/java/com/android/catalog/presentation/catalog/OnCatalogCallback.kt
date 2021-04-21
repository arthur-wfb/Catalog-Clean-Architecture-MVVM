package com.android.catalog.presentation.catalog

import com.android.catalog.data.source.Category

/**
 * To make an interaction between [CatalogActivity] & its children*/
interface OnCatalogCallback {

    fun navigateToCategoryPage(category: Category)

    fun gotoDetailPageByItemId(id: Long)
}