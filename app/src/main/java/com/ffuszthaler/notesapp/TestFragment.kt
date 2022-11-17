package com.ffuszthaler.notesapp

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class TestFragment : Fragment() {
//    private var notesDB = DatabaseHandler(requireActivity())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val testButton = view.findViewById<TextView>(R.id.testAddButton)
//        testButton.setOnClickListener() {
//            notesDB.insertData("title1", "hi there... :>", "shopping")
//        }
    }
}