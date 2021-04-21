package com.android.catalog.presentation.item


/**
 * To make an interaction between [ItemsAdapter] & [ItemsFragment]
 * */
interface OnItemsAdapterListener{

    fun gotoDetailPage(id: Long)

}