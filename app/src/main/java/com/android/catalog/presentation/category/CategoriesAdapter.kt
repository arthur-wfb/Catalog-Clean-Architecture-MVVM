package com.android.catalog.presentation.category

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.android.catalog.R
import com.android.catalog.data.source.Category
import com.android.catalog.databinding.HolderCategoryBinding
import java.util.ArrayList

internal class CategoriesAdapter(private val listener: OnCategoriesAdapterListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val categories: MutableList<Category>?
    private val mListener: OnCategoriesAdapterListener

    init {
        this.categories = ArrayList()
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderCategoryBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.holder_category, parent, false
        )
        return CategoryViewHolder(holderCategoryBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CategoryViewHolder).onBind(getItem(position))
    }

    private fun getItem(position: Int): Category {
        return categories!![position]
    }

    override fun getItemCount(): Int {
        return categories?.size ?: 0
    }

    fun addData(list: List<Category>) {
        this.categories!!.clear()
        this.categories.addAll(list)
        notifyDataSetChanged()
    }

    inner class CategoryViewHolder(dataBinding: ViewDataBinding) : RecyclerView.ViewHolder(dataBinding.root) {

        private val dataBinding: ViewDataBinding

        init {
            this.dataBinding = dataBinding
        }

        fun onBind(category: Category) {
            val holderCategoryBinding = this.dataBinding as HolderCategoryBinding
            val categoryViewModel = CategoryViewModel(category)
            holderCategoryBinding.categoryViewModel = categoryViewModel

            itemView.setOnClickListener {
                mListener.showItems(category)
            }

        }
    }

    companion object {

        private val TAG = CategoriesAdapter::class.java.simpleName
    }
}
