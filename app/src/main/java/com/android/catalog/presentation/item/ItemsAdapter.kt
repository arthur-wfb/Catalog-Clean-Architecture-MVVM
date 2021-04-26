package com.android.catalog.presentation.item

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.android.catalog.R
import com.android.catalog.data.Item
import com.android.catalog.databinding.HolderItemBinding
import com.squareup.picasso.Picasso
import java.util.ArrayList

internal class ItemsAdapter(private val listener: OnItemsAdapterListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: MutableList<Item>?
    private val mListener: OnItemsAdapterListener

    init {
        this.items = ArrayList()
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderItemBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.holder_item, parent, false
        )
        return ItemViewHolder(holderItemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).onBind(getItem(position))
    }

    private fun getItem(position: Int): Item {
        return items!![position]
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    fun addData(list: List<Item>) {
        this.items!!.clear()
        this.items.addAll(list)
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(dataBinding: ViewDataBinding) : RecyclerView.ViewHolder(dataBinding.root) {

        private val dataBinding: ViewDataBinding

        init {
            this.dataBinding = dataBinding
        }

        fun onBind(item: Item) {
            val holderItemBinding = this.dataBinding as HolderItemBinding
            holderItemBinding.itemViewModel = ItemViewModel(item)

            try {
                Picasso.get()
                    .load(item.url)
                    .placeholder(android.R.color.white)
                    .into(holderItemBinding.itemImageView)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            itemView.setOnClickListener {
                mListener.gotoDetailPage(item.id)
            }

        }
    }

    companion object {

        private val TAG = ItemsAdapter::class.java.simpleName
    }
}
