package com.android.catalog.domain.usecase

import com.android.catalog.data.Item
import com.android.catalog.data.source.ItemRepository
import com.android.catalog.domain.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetItemDetailUseCase @Inject constructor(private val repository: ItemRepository) : SingleUseCase<Item>() {

    private var itemId: Long? = null

    fun saveItemId(id: Long) {
        itemId = id
    }

    override fun buildUseCaseSingle(): Single<Item> {
        return repository.getItemDetail(itemId)
    }

    fun deleteAsFavorite(item: Item) {
        repository.deleteItem(item)
    }

    fun addAsFavorite(item: Item) {
        repository.addItem(item)
    }

    fun isFavorite(itemId: Long): Boolean {
        return repository.isFavorite(itemId)
    }
}