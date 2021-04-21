package com.android.catalog.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Item(
    @PrimaryKey val id: Long,
    val title: String,
    val url: String,
    val thumbnailUrl: String?
)