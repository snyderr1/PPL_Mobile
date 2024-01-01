package com.example.pplmobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.pplmobile.database.ExerciseRoomDatabase
import com.example.pplmobile.database.Exercise
import java.lang.IllegalStateException

class WorkoutFragment : Fragment(R.layout.workout) {
    private var currentPage: String = "Undefined Workout Type"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        val recyclerview = view.findViewById<RecyclerView>(R.id.workout_recycler)
        recyclerview.layoutManager = LinearLayoutManager(context)

        val data = ArrayList<ExerciseData>()
        val args: WorkoutFragmentArgs by navArgs()
        this.setCurrentPage(args.workoutArgs)

        try{
            val exerciseDao = ExerciseRoomDatabase.getDatabase(requireContext()).exerciseDao()

            val savedExercises: List<Exercise> =
                exerciseDao.getExerciseDao(type = "this.currentPage")
            for (row in savedExercises){
                data.add(ExerciseData(R.drawable.meirl, row.exerciseName, row.exerciseQuantity))
            }
        } catch(e: IllegalStateException) {
            data.add(ExerciseData(R.drawable.meirl, "No data", "None"))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = ExerciseAdapter(data)
        recyclerview.adapter = adapter

    }


    private fun setCurrentPage(requestedWorkout: String){
        this.currentPage = requestedWorkout
    }
}