package com.ffuszthaler.notesapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs


class EditNoteFragment : Fragment() {
    private val args: EditNoteFragmentArgs by navArgs()
    lateinit var notesDB: DatabaseHandler
    private lateinit var editTitle: TextView
    private lateinit var editBody: TextView
    private lateinit var editCategory: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val activityContext = context
        notesDB = DatabaseHandler(activityContext)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit_note, container, false)

//        val editNoteBackButton = view.findViewById<Button>(R.id.editNoteBackButton)
//        editNoteBackButton.setOnClickListener {
//            findNavController().navigate(R.id.action_editNoteFragment_to_homeFragment)
//        }

        val editNoteSaveButton = view.findViewById<Button>(R.id.editNoteSaveButton)
        editNoteSaveButton.setOnClickListener() {
            updateNote(view)

            val noteNavController = findNavController()
            val action = EditNoteFragmentDirections.actionEditNoteFragmentToHomeFragment()
            noteNavController.navigate(action)
        }

        val editNoteDeleteButton = view.findViewById<Button>(R.id.editNoteDeleteButton)
        editNoteDeleteButton.setOnClickListener() {
            deleteNote(view)

            val noteNavController = findNavController()
            val action = EditNoteFragmentDirections.actionEditNoteFragmentToHomeFragment()
            noteNavController.navigate(action)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        Log.d("TAG", "ARGS: ${args}")

        editTitle = view.findViewById<TextView>(R.id.editNoteTitle)
        editTitle.text = args.note.title.toString()

        editBody = view.findViewById<TextView>(R.id.editNoteBody)
        editBody.text = args.note.body.toString()

        editCategory = view.findViewById<Spinner>(R.id.editNoteCategory)
        editCategory.setSelection(
            (editCategory.adapter as ArrayAdapter<String?>).getPosition(
                args.note.category.toString()
            )
        )
    }

    private fun updateNote(view: View) {
        val id = args.note.id
        val title = editTitle.text.toString()
        val body = editBody.text.toString()
        val category = editCategory.selectedItem.toString()

        Log.d("TAG", "ARGS: id:${id}, title:${title}, body:${body}, cat:${category}")

        notesDB.updateData(id, title, body, category)
    }

    private fun deleteNote(view: View) {
        val id = args.note.id

        notesDB.deleteData(id)
    }
}