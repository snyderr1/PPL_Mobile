package com.example.pplmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pushButton = findViewById<TextView>(R.id.pushButton)
        val pullButton = findViewById<TextView>(R.id.pullButton)
        val legsButton = findViewById<TextView>(R.id.legsButton)
        val homeButton = findViewById<TextView>(R.id.homeButton)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        pushButton.setOnClickListener{
            val destinationType : String = "Push"
            val action = NavGraphDirections.actionGlobalWorkout(destinationType)
            navController.navigate(action)
        }
        pullButton.setOnClickListener{
            val destinationType : String = "Pull"
            val action = NavGraphDirections.actionGlobalWorkout(destinationType)
            navController.navigate(action)
        }
        legsButton.setOnClickListener{
            val destinationType : String = "Legs"
            val action = NavGraphDirections.actionGlobalWorkout(destinationType)
            navController.navigate(action)
        }
        homeButton.setOnClickListener{
            navController.navigate(R.id.homeFragment)
        }


    }
}