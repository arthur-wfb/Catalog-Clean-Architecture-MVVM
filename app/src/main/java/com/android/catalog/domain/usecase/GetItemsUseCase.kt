package com.android.catalog.domain.usecase

import com.android.catalog.data.Item
import com.android.catalog.data.source.ItemRepository
import com.android.catalog.domain.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject


class GetItemsUseCase @Inject constructor(private val repository: ItemRepository) : SingleUseCase<List<Item>>() {

    private var categoryId: Long? = null

    fun saveCategoryId(id: Long) {
        categoryId = id
    }

    override fun buildUseCaseSingle(): Single<List<Item>> {
        return repository.getItems(categoryId)
    }
}