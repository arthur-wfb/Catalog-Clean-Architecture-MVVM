package com.android.catalog.di.builder

import android.arch.lifecycle.ViewModel
import com.android.catalog.di.ViewModelKey
import com.android.catalog.presentation.category.CategoriesViewModel
import com.android.catalog.presentation.detailitem.ItemDetailViewModel
import com.android.catalog.presentation.item.ItemsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(CategoriesViewModel::class)
    abstract fun bindCategoriesViewModel(categoriesViewModel: CategoriesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ItemsViewModel::class)
    abstract fun bindItemsViewModel(itemsViewModel: ItemsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ItemDetailViewModel::class)
    abstract fun bindItemDetailViewModel(itemDetailViewModel: ItemDetailViewModel): ViewModel

}