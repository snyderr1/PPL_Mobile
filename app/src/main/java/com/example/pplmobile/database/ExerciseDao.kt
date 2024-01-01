package com.example.pplmobile.database
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ExerciseDao {

    @Query("SELECT * from exercise WHERE type = :type")
    fun getExerciseDao(type:String): List<Exercise>

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(row: Exercise)

    @Update
    suspend fun update(row: Exercise)

    @Delete
    suspend fun delete(row: Exercise)
}