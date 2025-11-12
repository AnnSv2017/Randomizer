package com.asvn.randomizer

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface ListDao {
    @Insert
    suspend fun insertList(list: ListEntity): Long
    @Insert
    suspend fun insertItems(item: List<Item>)

    @Transaction
    suspend fun insertListWithItems(list: ListEntity, items: List<Item>) {
        val listId = insertList(list)
        val itemsWithListId = items.map { it.copy(listId = listId) }
        insertItems(itemsWithListId)
    }

    @Transaction
    @Query("SELECT name FROM list_table WHERE id = :listId LIMIT 1")
    fun getListNameById(listId: Long): LiveData<String>

    @Transaction
    @Query("SELECT * FROM list_table ORDER BY id DESC")
    fun getAllLists(): LiveData<List<ListEntity>>

    @Transaction
    @Query("SELECT * FROM list_table")
    fun getListWithItems(): LiveData<List<ListWithItems>>

    @Transaction
    @Query("SELECT * FROM list_table WHERE id = :listId")
    fun getListWithItems(listId: Long): LiveData<ListWithItems>

    @Transaction
    @Query("SELECT * FROM item_table WHERE listId = :listId")
    fun getItemsByListId(listId: Long): LiveData<List<Item>>

    @Query("UPDATE list_table SET name = :newName WHERE id = :listId")
    suspend fun updateListName(listId: Long, newName: String)

    @Query("UPDATE item_table SET name = :newName WHERE id = :itemId")
    suspend fun updateItemName(itemId: Long, newName: String)
}