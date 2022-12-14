package com.ffuszthaler.notesapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.navigation.fragment.findNavController

class NewNoteFragment : Fragment() {
    lateinit var notesDB: DatabaseHandler
    lateinit var noteList: MutableList<Note>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val activityContext = context
        notesDB = DatabaseHandler(activityContext)
        noteList = notesDB.listOfAllNotes()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_new_note, container, false)

        val newNoteAddButton = view.findViewById<Button>(R.id.newNoteAddButton)
        newNoteAddButton.setOnClickListener() {
            saveNote(view)

            val noteNavController = findNavController()
            val action = NewNoteFragmentDirections.actionNewNoteFragmentToHomeFragment()
            noteNavController.navigate(action)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun saveNote(view: View) {
        val newNoteTitle = view.findViewById<EditText>(R.id.newNoteTitle)
        val title: String = newNoteTitle.text.toString()

        val newNoteBody = view.findViewById<EditText>(R.id.newNoteBody)
        val body: String = newNoteBody.text.toString()

        val newNoteBackCategory = view.findViewById<Spinner>(R.id.newNoteCategory)
        val category: String = newNoteBackCategory.selectedItem.toString()

        notesDB.insertData(title, body, category)
    }
}