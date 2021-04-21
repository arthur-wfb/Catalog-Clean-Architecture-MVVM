package com.android.catalog.data.source

import com.android.catalog.data.source.local.AppDatabase
import com.android.catalog.data.source.remote.RetrofitService
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Category] from server or db
 * */
class CategoryRepositoryImp(private val database: AppDatabase,
                            private val retrofitService: RetrofitService) : CategoryRepository {


    override fun getCategories(): Single<List<Category>> {
        return retrofitService.getCategories()
    }


}