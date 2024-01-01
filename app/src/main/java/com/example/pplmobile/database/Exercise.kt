package com.example.pplmobile.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val exerciseName: String,
    @ColumnInfo(name = "quantity")
    val exerciseQuantity: String,
    @ColumnInfo(name = "type")
    val exerciseType: String,
)