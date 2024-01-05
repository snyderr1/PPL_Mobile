package com.example.pplmobile.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Exercise::class], version = 1, exportSchema=false)
abstract class ExerciseRoomDatabase: RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao

    private class ExerciseDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var exerciseDao = database.exerciseDao()


                    // Add sample words.
                    var placeholder = Exercise(exerciseName = "Placeholder", exerciseQuantity = "15", exerciseType = "none")
                    exerciseDao.insert(placeholder)

                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ExerciseRoomDatabase? = null

        fun getDatabase(context: Context, scope:CoroutineScope): ExerciseRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExerciseRoomDatabase::class.java,
                    "exercise_database"
                )
                    .addCallback(ExerciseDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}