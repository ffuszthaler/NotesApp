package com.ffuszthaler.notesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class NewNoteFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_new_note, container, false)

        val newNoteBackButton = view.findViewById<Button>(R.id.newNoteBackButton)
        newNoteBackButton.setOnClickListener {
            findNavController().navigate(R.id.action_newNoteFragment_to_homeFragment)
        }

        return view
    }
}