package com.asvn.randomizer

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
data class Item(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var name: String = ""
)
