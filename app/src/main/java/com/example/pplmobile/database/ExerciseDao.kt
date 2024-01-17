package com.example.pplmobile.database
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {

    @Query("SELECT * from exercise")
    fun getExerciseData(): Flow<List<Exercise>>

    @Query("SELECT * from exercise WHERE exerciseRow=:rowNum and type=:page")
    suspend fun getExerciseByRowAndPage(rowNum: Int, page: String): Exercise

    @Query("SELECT * from exercise")
    suspend fun getExerciseDataBlocking(): List<Exercise>


    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(row: Exercise): Long



    @Update
    suspend fun update(row: Exercise)


    @Delete
    suspend fun delete(row: Exercise)
}