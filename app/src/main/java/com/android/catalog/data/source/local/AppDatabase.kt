package com.android.catalog.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.android.catalog.data.Item
import com.android.catalog.data.source.local.dao.ItemDao

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val itemDao: ItemDao

    companion object {
        public val DB_NAME = "CatalogDatabase.db"
    }
}
