package com.android.catalog.data.source

import io.reactivex.Single

/**
 * To make an interaction between [CategoryRepositoryImp] & [GetCategoriesUseCase]
 * */
interface CategoryRepository {

    fun getCategories(): Single<List<Category>>
}