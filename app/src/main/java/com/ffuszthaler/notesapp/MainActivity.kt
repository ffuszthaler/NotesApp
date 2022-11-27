package com.ffuszthaler.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val notesDB = DatabaseHandler(this)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.noteFragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        // back buttons in navbar
        setupActionBarWithNavController(navController)

//        val addNoteButton = findViewById<Button>(R.id.addNoteButton) as Button
//        addNoteButton.setOnClickListener {
//            notesDB.insertData("title1", "hi there... :>", "shopping")
//        }
    }

    // correctly handle back stack with fragments
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}