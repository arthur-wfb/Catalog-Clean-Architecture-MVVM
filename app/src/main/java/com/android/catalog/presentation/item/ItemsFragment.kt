package com.android.catalog.presentation.item

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.android.catalog.R
import com.android.catalog.databinding.FragmentItemsBinding
import com.android.catalog.presentation.catalog.OnCatalogCallback
import dagger.android.support.DaggerFragment
import java.lang.ClassCastException
import javax.inject.Inject


class ItemsFragment : DaggerFragment(), OnItemsAdapterListener {

    private lateinit var fragmentItemsBinding: FragmentItemsBinding
    private var adapter: ItemsAdapter? = null
    private var mCallback: OnCatalogCallback? = null
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: ItemsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(ItemsViewModel::class.java)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnCatalogCallback) {
            mCallback = context
        } else throw ClassCastException(context.toString() + "must implement OnCatalogCallback!")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ItemsAdapter(this)
        val categoryId = arguments?.let { it.getLong(KEY_CATEGORY_ID) }
        viewModel.loadItems(categoryId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentItemsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_items, container, false)
        fragmentItemsBinding.itemsViewModel = viewModel
        fragmentItemsBinding.itemsRecyclerView.adapter = adapter

        viewModel.isLoad.observe(this, Observer {
            if (it!!) {
                fragmentItemsBinding.itemsProgressBar.visibility = View.GONE
            } else {
                fragmentItemsBinding.itemsProgressBar.visibility = View.VISIBLE
            }
        })

        viewModel.itemListReceivedLiveData.observe(this, Observer {
            adapter?.addData(it!!)
        })

        return fragmentItemsBinding.root
    }

    override fun gotoDetailPage(id: Long) {
        mCallback?.gotoDetailPageByItemId(id)
    }

    override fun onDetach() {
        super.onDetach()
        mCallback = null
    }


    companion object {

        val KEY_CATEGORY_ID = "KEY_CATEGORY_ID"
        val FRAGMENT_NAME = ItemsFragment::class.java.name


        @JvmStatic
        fun newInstance(id: Long) =
            ItemsFragment().apply {
                arguments = Bundle().apply {
                    putLong(KEY_CATEGORY_ID, id)
                }
            }
    }

}