package com.example.pplmobile

enum class WorkoutType {
    Push, Pull, Legs;

    override fun toString(): String{
        when(this){
            Push -> return("Push")
            Pull -> return("Pull")
            Legs -> return("Legs")
        }
    }
}