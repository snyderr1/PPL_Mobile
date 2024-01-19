package com.example.pplmobile
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pplmobile.database.Exercise

class ExerciseAdapter(private val currentViewModel: ExerciseViewModel, private val currentPage: String): RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    private val CONTENT = 1
    private val ADD = 2
    private var addEnabled = true
    private var exerciseList: List<Exercise> = emptyList<Exercise>()

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
            holder.editButton.setOnClickListener {editButtonListener(holder, position)}
            holder.removeButton.setOnClickListener {removeButtonListener(holder, position)}
        } else {

            holder as AddButtonViewHolder
            holder.addButton.setOnClickListener {addButtonListener(holder, position)}
        }
    }

    override fun getItemViewType(position:Int): Int{
        if(position == exerciseList.size || exerciseList.size == 0){
            return ADD
        } else {
            return CONTENT
        }
    }

    override fun getItemCount(): Int {
        return exerciseList.size + 1
    }

    fun setData(data: List<Exercise>){
        this.exerciseList = data
        notifyDataSetChanged()
    }


    class ContentViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val exerciseIcon: ImageView = itemView.findViewById(R.id.exerciseIcon)
        val exerciseName: TextView = itemView.findViewById(R.id.exerciseName)
        val exerciseQuantity: TextView = itemView.findViewById(R.id.exerciseQuantity)
        val editButton: android.widget.Button = itemView.findViewById(R.id.editButton)
        val removeButton: android.widget.Button = itemView.findViewById(R.id.removeButton)
    }

    class AddButtonViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val addButton: com.google.android.material.floatingactionbutton.FloatingActionButton = itemView.findViewById(R.id.addExercise)
    }


    private fun editButtonListener(holder: ContentViewHolder, position: Int){
        if (holder.exerciseName.isEnabled) {
            this.addEnabled = true
            holder.removeButton.visibility = View.GONE
            holder.exerciseIcon.visibility = View.VISIBLE
            val newExercise: Exercise = Exercise(
                exerciseName = holder.exerciseName.getText().toString(),
                exerciseQuantity = holder.exerciseQuantity.getText().toString(),
                exerciseType = currentPage,
                exerciseRow = position,
            )
            currentViewModel.update(newExercise)
            this.notifyItemChanged(position)
        } else {
            holder.removeButton.visibility = View.VISIBLE
            holder.exerciseIcon.visibility = View.GONE
            this.addEnabled = false
        }

        holder.exerciseName.isEnabled = !(holder.exerciseQuantity.isEnabled)
        holder.exerciseQuantity.isEnabled = !(holder.exerciseQuantity.isEnabled)
    }
    private fun removeButtonListener(holder: ContentViewHolder, position: Int){
        if (holder.exerciseName.isEnabled) {
            val newExercise: Exercise = Exercise(
                exerciseName = holder.exerciseName.getText().toString(),
                exerciseQuantity = holder.exerciseQuantity.getText().toString(),
                exerciseType = currentPage,
                exerciseRow = position,
            )
            currentViewModel.delete(newExercise)
            this.notifyDataSetChanged()
            holder.exerciseName.isEnabled = !(holder.exerciseQuantity.isEnabled)
            holder.exerciseQuantity.isEnabled = !(holder.exerciseQuantity.isEnabled)
            holder.removeButton.visibility = View.GONE
            holder.exerciseIcon.visibility = View.VISIBLE
            this.addEnabled = true
        }
    }
    private fun addButtonListener(holder: AddButtonViewHolder, position: Int){
        if(this.addEnabled) {
            val newExercise: Exercise = Exercise(
                exerciseName = position.toString(),
                exerciseQuantity = "Touch to start typing",
                exerciseType = currentPage,
                exerciseRow = position
            )
            currentViewModel.insert(newExercise)
            this.notifyItemChanged(position)
        }
    }
}