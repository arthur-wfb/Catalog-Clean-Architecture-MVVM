package com.android.catalog.presentation.detailitem

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.android.catalog.R
import com.android.catalog.databinding.ActivityItemDetailBinding
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ItemDetailActivity : DaggerAppCompatActivity(), OnItemDetailCallback {

    private lateinit var activityItemDetailBinding: ActivityItemDetailBinding
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: ItemDetailViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(ItemDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityItemDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_item_detail)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        activityItemDetailBinding.itemDetailViewModel = viewModel

        val itemId = intent?.extras?.getLong(KEY_ITEM_ID) ?: return
        viewModel.getDetail(itemId)
        viewModel.checkFavoriteStatus(itemId)

        viewModel.itemData.observe(this, Observer {
            activityItemDetailBinding.detailTitleTextView.setText(it?.title)
            try {
                Picasso.get()
                    .load(it?.url)
                    .into(activityItemDetailBinding.detailToolbarImageView)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })

        viewModel.isFavorite.observe(this, Observer {
            if (it != true) {
                activityItemDetailBinding.detailFab.setImageResource(R.drawable.ic_star_empty_white_vector)
            } else {
                activityItemDetailBinding.detailFab.setImageResource(R.drawable.ic_star_full_vector)
            }
        })

        activityItemDetailBinding.detailFab.setOnClickListener {
            viewModel.updateFavoriteStatus()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private val TAG = ItemDetailActivity::class.java.name
        private val KEY_ITEM_ID = "KEY_ITEM_ID"

        fun start(context: Context, id: Long) {
            val starter = Intent(context, ItemDetailActivity::class.java)
            val bundle = Bundle().apply {
                putLong(KEY_ITEM_ID, id)
            }
            starter.putExtras(bundle)
            context.startActivity(starter)
        }
    }

}