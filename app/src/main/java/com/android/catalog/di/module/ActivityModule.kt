package com.android.catalog.di.module

import com.android.catalog.di.provider.CatalogActivityProviders
import com.android.catalog.presentation.detailitem.ItemDetailActivity
import com.android.catalog.presentation.catalog.CatalogActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class])
interface ActivityModule {

    @ContributesAndroidInjector(modules = [CatalogActivityProviders::class])
    fun catalogActivityInjector(): CatalogActivity

    @ContributesAndroidInjector
    fun itemDetailActivityInjector(): ItemDetailActivity

}