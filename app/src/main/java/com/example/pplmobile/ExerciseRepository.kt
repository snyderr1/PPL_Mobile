package com.example.pplmobile

import androidx.annotation.WorkerThread
import androidx.lifecycle.asLiveData
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
    suspend fun update(exercise: Exercise){
        exerciseDao.update(exercise)
    }
    @WorkerThread
    suspend fun updateByParameter(exercise: Exercise){
        var updatee: Exercise = exerciseDao.getExerciseByRowAndPage(exercise.exerciseRow, exercise.exerciseType)
        updatee.exerciseName = exercise.exerciseName
        updatee.exerciseQuantity = exercise.exerciseQuantity
        exerciseDao.update(updatee)
    }

    @WorkerThread
    suspend fun remove(exercise: Exercise){
        exerciseDao.insert(exercise)
    }
}