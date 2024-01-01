package com.example.pplmobile
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pplmobile.database.ExerciseRoomDatabase

class ExerciseAdapter(private val exerciseList: List<ExerciseData>): RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val exerciseDataInstance = exerciseList[position]

        holder.exerciseIcon.setImageResource(exerciseDataInstance.image)

        holder.exerciseName.text = exerciseDataInstance.exerciseName
        holder.exerciseQuantity.text = exerciseDataInstance.exerciseQuantity
        holder.editButton.setOnClickListener{
            if(holder.exerciseName.isEnabled) {

                holder.exerciseName.isEnabled = false
                holder.exerciseQuantity.isEnabled = false
            }
            holder.exerciseQuantity.isEnabled = !(holder.exerciseQuantity.isEnabled)

        }
    }

    private fun insertExercise(){

    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val exerciseIcon: ImageView = itemView.findViewById(R.id.exerciseIcon)
        val exerciseName: TextView = itemView.findViewById(R.id.exerciseName)
        val exerciseQuantity: TextView = itemView.findViewById(R.id.exerciseQuantity)
        val editButton: android.widget.Button = itemView.findViewById(R.id.editButton)
    }
}