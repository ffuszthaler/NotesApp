package com.ffuszthaler.notesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteListAdapter(private val noteList: MutableList<Note>): RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {
    var onItemClick : ((Note) -> Unit)? = null

    class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val noteTitle: TextView = itemView.findViewById(R.id.noteTitle)
        val noteBody: TextView = itemView.findViewById(R.id.noteBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_list_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = noteList[position]
        holder.noteTitle.text = note.title
        holder.noteBody.text = note.body

        holder.itemView.setOnClickListener() {
            onItemClick?.invoke(note)
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }
}