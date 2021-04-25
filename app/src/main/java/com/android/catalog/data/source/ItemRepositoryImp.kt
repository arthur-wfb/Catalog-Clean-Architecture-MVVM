package com.android.catalog.data.source

import com.android.catalog.data.Item
import com.android.catalog.data.source.local.AppDatabase
import com.android.catalog.data.source.remote.RetrofitService
import io.reactivex.Single

/**
 * This repository is responsible for
 * fetching data [item] from server or db
 * */
class ItemRepositoryImp(
    private val database: AppDatabase,
    private val retrofitService: RetrofitService
) : ItemRepository {
    
    override fun getItemDetail(itemId: Long?): Single<Item> {
        return retrofitService.getItemDetail(itemId!!)
    }

    override fun getItems(categoryId: Long?): Single<List<Item>> {
        return retrofitService.getItems(categoryId!!)
    }

    override fun isFavorite(itemId: Long): Boolean {
        val loadOneByItemId = database.itemDao.loadOneByItemId(itemId)
        return loadOneByItemId != null
    }

    override fun deleteItem(item: Item) {
        database.itemDao.delete(item)
    }

    override fun addItem(item: Item) {
        database.itemDao.insert(item)
    }

}