package com.ffuszthaler.notesapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager


class HomeFragment : Fragment() {
    lateinit var notesDB: DatabaseHandler
    lateinit var noteList: MutableList<Note>

    private lateinit var recyclerView: RecyclerView
    lateinit var noteListAdapter: NoteListAdapter

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
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // manage recyclerview
        recyclerView = view.findViewById(R.id.notesRecyclerView)
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)

        // setting recycler view layout to staggered grid
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = staggeredGridLayoutManager

//        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        noteListAdapter = NoteListAdapter(noteList)
        recyclerView.adapter = noteListAdapter

        noteListAdapter.onItemClick = { note ->
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(note))
        }

        val newNoteButton = view.findViewById<Button>(R.id.newNoteButton)
        newNoteButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_newNoteFragment)
        }

        return view
    }
}