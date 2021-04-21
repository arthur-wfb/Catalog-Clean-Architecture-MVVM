package com.android.catalog.data.source.remote

import com.android.catalog.data.Item
import com.android.catalog.data.source.Category
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("albums/")
    fun getCategories(): Single<List<Category>>

    @GET("albums/{id}/photos")
    fun getItems(@Path("id") id: Long): Single<List<Item>>

    @GET("photos/{id}")
    fun getItemDetail(@Path("id") id: Long):Single<Item>
}