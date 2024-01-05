package com.example.pplmobile
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.pplmobile.database.Exercise
import com.example.pplmobile.database.ExerciseRoomDatabase
import kotlinx.coroutines.CoroutineScope

class ExerciseAdapter(private val exerciseList: List<Exercise>, private val currentViewModel: ExerciseViewModel): RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    private val CONTENT = 1
    private val ADD = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == CONTENT) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_item, parent, false)
            return ContentViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_add_item, parent, false)
            return AddButtonViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder.itemViewType == CONTENT) {
            holder as ContentViewHolder
            val exerciseDataInstance = exerciseList[position]

            //holder.exerciseIcon.setImageResource(exerciseDataInstance.image)

            holder.exerciseName.text = exerciseDataInstance.exerciseName
            holder.exerciseQuantity.text = exerciseDataInstance.exerciseQuantity
            holder.editButton.setOnClickListener {
                if (holder.exerciseName.isEnabled) {
                    val newExercise: Exercise = Exercise(
                        exerciseName = holder.exerciseName.getText().toString(),
                        exerciseQuantity = holder.exerciseQuantity.getText().toString(),
                        exerciseType = "placeholder"
                    )
                    currentViewModel.insert(newExercise)
                }
                holder.exerciseName.isEnabled = !(holder.exerciseQuantity.isEnabled)
                holder.exerciseQuantity.isEnabled = !(holder.exerciseQuantity.isEnabled)

            }
        }

    }

    override fun getItemViewType(position:Int): Int{
        if(position == exerciseList.size){
            return ADD
        } else {
            return CONTENT
        }
    }

    override fun getItemCount(): Int {
        return exerciseList.size + 1
    }

    class ContentViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val exerciseIcon: ImageView = itemView.findViewById(R.id.exerciseIcon)
        val exerciseName: TextView = itemView.findViewById(R.id.exerciseName)
        val exerciseQuantity: TextView = itemView.findViewById(R.id.exerciseQuantity)
        val editButton: android.widget.Button = itemView.findViewById(R.id.editButton)
    }
    class AddButtonViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
    }
}