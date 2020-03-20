package com.kodak.sampleandroidarchitecturecomponents

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class NoteAdapter: RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClicked(note: Note)
    }

    private var notes: List<Note> = ArrayList<Note>()
    private var listener: OnItemClickListener? = null

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

    fun getNoteAt(position: Int): Note {
        return notes[position]
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class ViewHolder: RecyclerView.ViewHolder {
        var textViewTitle: TextView
        var textViewDescription: TextView
        var textViewPriority: TextView

        constructor(view: View): super(view) {
            textViewTitle = view.findViewById(R.id.text_view_title)
            textViewDescription = view.findViewById(R.id.text_view_description)
            textViewPriority = view.findViewById(R.id.text_view_priority)

            view.setOnClickListener {
                listener?.onItemClicked(notes[adapterPosition])
            }
        }
    }
}