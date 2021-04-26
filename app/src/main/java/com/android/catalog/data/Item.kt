package com.android.catalog.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    @PrimaryKey val id: Long,
    val title: String,
    val url: String,
    val thumbnailUrl: String?
)