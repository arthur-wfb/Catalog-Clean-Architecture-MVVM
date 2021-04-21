package com.android.catalog.data.source

import com.android.catalog.data.Item
import io.reactivex.Single


/**
 * To make an interaction between [ItemRepositoryImp] & [GetItemsUseCase]
 * */
interface ItemRepository {

    fun getItems(categoryId: Long?): Single<List<Item>>

    fun getItemDetail(itemId: Long?): Single<Item>
}