package com.asvn.randomizer

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ItemDao {
    @Insert
    suspend fun insert(item: Item)
    @Insert
    suspend fun insertAll(items: List<Item>)
    @Update
    suspend fun update(item: Item)
    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * FROM item_table WHERE id = :itemId")
    fun get(itemId: Long): LiveData<Item>

    @Query("SELECT * FROM item_table ORDER BY id DESC")
    fun getAll(): LiveData<List<Item>>
}