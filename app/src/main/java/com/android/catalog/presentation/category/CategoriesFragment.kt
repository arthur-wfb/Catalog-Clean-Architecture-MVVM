package com.android.catalog.presentation.category

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.android.catalog.R
import com.android.catalog.data.source.Category
import com.android.catalog.databinding.FragmentCategoriesBinding
import com.android.catalog.presentation.catalog.OnCatalogCallback
import dagger.android.support.DaggerFragment
import java.lang.ClassCastException

import javax.inject.Inject

class CategoriesFragment : DaggerFragment(), OnCategoriesAdapterListener {

    private lateinit var fragmentCategoriesBinding: FragmentCategoriesBinding
    private var adapter: CategoriesAdapter? = null
    private var mCallback: OnCatalogCallback? = null
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: CategoriesViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(CategoriesViewModel::class.java)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnCatalogCallback) {
            mCallback = context
        } else throw ClassCastException(context.toString() + "must implement OnCatalogCallback!")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = CategoriesAdapter(this)
        viewModel.loadCategories()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentCategoriesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_categories, container, false)
        fragmentCategoriesBinding.categoriesViewModel = viewModel
        fragmentCategoriesBinding.categoriesRecyclerView.adapter = adapter

        viewModel.isLoad.observe(this, Observer {
            if (!it!!) {
                fragmentCategoriesBinding.categoriesProgressBar.visibility = View.VISIBLE
            } else {
                fragmentCategoriesBinding.categoriesProgressBar.visibility = View.GONE
            }
        })

        viewModel.categoriesReceivedLiveData.observe(this, Observer {
            initRecyclerView(it!!)
        })

        return fragmentCategoriesBinding.root
    }

    override fun showItems(category: Category) {
        mCallback?.navigateToCategoryPage(category)
    }

    private fun initRecyclerView(categories: List<Category>) {
        Log.i("CategoriesFragment", categories.toString())
        adapter?.addData(categories)
    }

    override fun onDetach() {
        super.onDetach()
        adapter = null
        mCallback = null
    }


    companion object {

        val FRAGMENT_NAME = CategoriesFragment::class.java.name

        @JvmStatic
        fun newInstance() =
            CategoriesFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}