package com.ffuszthaler.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val notesDB = DatabaseHandler(this)

        val addNoteButton = findViewById<Button>(R.id.addNoteButton) as Button
        addNoteButton.setOnClickListener {
            notesDB.insertData("title1", "hi there... :>", "shopping")
        }
    }
}