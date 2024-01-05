package com.example.pplmobile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.pplmobile.database.Exercise
import com.example.pplmobile.database.ExerciseRoomDatabase
import kotlinx.coroutines.launch

data class ExerciseViewState(
    val data: ArrayList<Exercise>? = null,
    val name: String = "None"
)
class ExerciseViewModel(private val repo: ExerciseRepository): ViewModel() {
    val data: LiveData<List<Exercise>> = repo.all.asLiveData()

    fun insert(exercise:Exercise) = viewModelScope.launch{
        repo.insert(exercise)
    }


    class ExerciseViewModelFactory(private val repository: ExerciseRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ExerciseViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ExerciseViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}