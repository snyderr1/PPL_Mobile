package com.example.pplmobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.pplmobile.database.ExerciseRoomDatabase
import com.example.pplmobile.database.Exercise
import java.lang.IllegalStateException

class WorkoutFragment: Fragment(R.layout.workout) {
    private var currentPage: String = "Undefined Workout Type"
    private val currentViewModel: ExerciseViewModel by activityViewModels{
        ExerciseViewModel.ExerciseViewModelFactory((activity?.application as ExerciseApplication).repo)
    }
    private var data: MutableList<Exercise> = ArrayList<Exercise>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        val recyclerview = view.findViewById<RecyclerView>(R.id.workout_recycler)
        recyclerview.layoutManager = LinearLayoutManager(this.context)


        val args: WorkoutFragmentArgs by navArgs()
        this.setCurrentPage(args.workoutArgs)
        val adapter = ExerciseAdapter(this.currentViewModel)
        this.currentViewModel.data.observe(viewLifecycleOwner, Observer{
            val newData: List<Exercise> = it
            adapter.setData(newData)

        })
        recyclerview.adapter = adapter

    }


    private fun setCurrentPage(requestedWorkout: String){
        this.currentPage = requestedWorkout
    }
}