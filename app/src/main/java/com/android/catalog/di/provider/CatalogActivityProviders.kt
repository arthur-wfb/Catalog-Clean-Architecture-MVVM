package com.android.catalog.di.provider

import com.android.catalog.presentation.category.CategoriesFragment
import com.android.catalog.presentation.item.ItemsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CatalogActivityProviders {

    @ContributesAndroidInjector
    abstract fun provideCategoriesFragment(): CategoriesFragment

    @ContributesAndroidInjector
    abstract fun provideItemsFragment(): ItemsFragment

}