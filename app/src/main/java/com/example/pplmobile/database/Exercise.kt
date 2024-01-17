package com.example.pplmobile.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    var exerciseName: String,
    @ColumnInfo(name = "quantity")
    var exerciseQuantity: String,
    @ColumnInfo(name = "type")
    val exerciseType: String,
    @ColumnInfo(name = "exerciseRow")
    val exerciseRow: Int
)