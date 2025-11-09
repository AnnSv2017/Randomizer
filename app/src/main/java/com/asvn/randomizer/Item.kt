package com.asvn.randomizer

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "item_table",
    foreignKeys = [
        ForeignKey(
            entity = ListEntity::class,
            parentColumns = ["id"],
            childColumns = ["listId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("listId")]
)
data class Item(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var name: String = "",
    var listId: Long = 0L
)
