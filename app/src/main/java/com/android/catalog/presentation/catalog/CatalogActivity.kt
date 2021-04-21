package com.android.catalog.presentation.catalog


import android.os.Bundle
import com.android.catalog.R
import com.android.catalog.data.source.Category
import com.android.catalog.presentation.category.CategoriesFragment
import com.android.catalog.presentation.detailitem.ItemDetailActivity
import com.android.catalog.presentation.item.ItemsFragment
import dagger.android.support.DaggerAppCompatActivity


class CatalogActivity : DaggerAppCompatActivity(), OnCatalogCallback {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog)

        if (savedInstanceState == null) {
            navigateToCatalogPage()
        }
    }

    private fun navigateToCatalogPage() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.catalog_container,
                CategoriesFragment.newInstance(),
                CategoriesFragment.FRAGMENT_NAME
            ).commit()
    }

    override fun navigateToCategoryPage(category: Category) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.catalog_container,
                ItemsFragment.newInstance(category.id),
                ItemsFragment.FRAGMENT_NAME
            )
            .addToBackStack(ItemsFragment.FRAGMENT_NAME)
            .commit()
    }


    override fun gotoDetailPageByItemId(id: Long) {
        ItemDetailActivity.start(this, id)
    }
}
