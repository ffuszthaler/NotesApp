package com.ffuszthaler.notesapp

import android.app.AlertDialog
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
    lateinit var activityContext: Context

    private lateinit var editTitle: TextView
    private lateinit var editBody: TextView
    private lateinit var editCategory: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        activityContext = context
        notesDB = DatabaseHandler(activityContext)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit_note, container, false)

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
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        notesDB.updateData(id, title, body, category)
    }

    private fun deleteNote(view: View) {
        val builder = AlertDialog.Builder(activityContext)

        builder.setTitle("Delete Note?")
        builder.setMessage("This note will be permanently deleted.")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, which ->
                val id = args.note.id
                notesDB.deleteData(id)

                val noteNavController = findNavController()
                val action = EditNoteFragmentDirections.actionEditNoteFragmentToHomeFragment()
                noteNavController.navigate(action)
            }
            .setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }

        val alert = builder.create()
        alert.show()
    }
}