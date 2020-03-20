package com.kodak.sampleandroidarchitecturecomponents

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class NoteAdapter: RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    private var notes: List<Note> = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]
        holder.textViewTitle.text = note.title
        holder.textViewDescription.text = note.description
        holder.textViewPriority.text = note.priority.toString()
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    class ViewHolder: RecyclerView.ViewHolder {
        var textViewTitle: TextView
        var textViewDescription: TextView
        var textViewPriority: TextView

        constructor(view: View): super(view) {
            textViewTitle = view.findViewById(R.id.text_view_title)
            textViewDescription = view.findViewById(R.id.text_view_description)
            textViewPriority = view.findViewById(R.id.text_view_priority)
        }
    }
}