package com.android.catalog.data.source.local.dao

import androidx.room.*
import com.android.catalog.data.Item

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Item)

    @Query("SELECT * FROM Item")
    fun loadAll(): MutableList<Item>

    @Delete
    fun delete(item: Item)

    @Query("SELECT * FROM Item where id = :itemId")
    fun loadOneByItemId(itemId: Long): Item?

    @Update
    fun update(item: Item)

}