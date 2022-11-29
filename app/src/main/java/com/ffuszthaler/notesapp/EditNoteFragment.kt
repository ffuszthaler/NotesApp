package com.ffuszthaler.notesapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs


class EditNoteFragment : Fragment() {
    private val args: EditNoteFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("TAG", "ARGS: ${args}")

        val editTitle = view.findViewById<TextView>(R.id.editNoteTitle)
        editTitle.text = args.note.title.toString()

        val editBody = view.findViewById<TextView>(R.id.editNoteBody)
        editBody.text = args.note.body.toString()

        val editCategory = view.findViewById<Spinner>(R.id.editNoteCategory)
        editCategory.setSelection(
            (editCategory.adapter as ArrayAdapter<String?>).getPosition(
                args.note.category.toString()
            )
        )
    }
}