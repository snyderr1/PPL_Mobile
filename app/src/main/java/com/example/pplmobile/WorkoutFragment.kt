package com.example.pplmobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WorkoutFragment : Fragment(R.layout.workout) {
    private var currentPage: String = "Undefined Workout Type"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        val recyclerview = view.findViewById<RecyclerView>(R.id.workout_recycler)
        recyclerview.layoutManager = LinearLayoutManager(context)

        val data = ArrayList<ExerciseData>()

        val args: WorkoutFragmentArgs by navArgs()
        this.setCurrentPage(args.workoutArgs)
        for (i in 1..20) {
            data.add(ExerciseData(R.drawable.meirl, this.currentPage, "None"))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = ExerciseAdapter(data)
        recyclerview.adapter = adapter

    }
    public fun setCurrentPage(requestedWorkout: String){
        this.currentPage = requestedWorkout
    }
}