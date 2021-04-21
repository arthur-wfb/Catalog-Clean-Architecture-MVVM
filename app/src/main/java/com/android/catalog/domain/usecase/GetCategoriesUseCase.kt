package com.android.catalog.domain.usecase

import com.android.catalog.data.source.Category
import com.android.catalog.data.source.CategoryRepository
import com.android.catalog.domain.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject



class GetCategoriesUseCase @Inject constructor(private val repository: CategoryRepository): SingleUseCase<List<Category>>() {


    override fun buildUseCaseSingle(): Single<List<Category>> {
        return repository.getCategories()
    }
}