package com.asvn.randomizer

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "list_table")
data class ListEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var name: String = ""
)