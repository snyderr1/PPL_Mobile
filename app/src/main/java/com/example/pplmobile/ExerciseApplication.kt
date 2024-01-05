package com.example.pplmobile

import android.app.Application
import com.example.pplmobile.database.ExerciseRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ExerciseApplication: Application() {
    val appScope = CoroutineScope(SupervisorJob())
    val db by lazy {ExerciseRoomDatabase.getDatabase(this, appScope)}
    val repo by lazy {ExerciseRepository(db.exerciseDao())}
}