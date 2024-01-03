package com.example.pplmobile

import androidx.annotation.WorkerThread
import com.example.pplmobile.database.Exercise
import com.example.pplmobile.database.ExerciseDao
import kotlinx.coroutines.flow.Flow

class ExerciseRepository(private val exerciseDao: ExerciseDao) {
    val all: Flow<List<Exercise>> = exerciseDao.getExerciseData()

    @WorkerThread
    suspend fun insert(exercise: Exercise){
        exerciseDao.insert(exercise)
    }
    @WorkerThread
    suspend fun remove(exercise: Exercise){
        exerciseDao.insert(exercise)
    }
}