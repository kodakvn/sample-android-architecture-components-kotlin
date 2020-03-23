package com.kodak.sampleandroidarchitecturecomponents.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kodak.sampleandroidarchitecturecomponents.R
import com.kodak.sampleandroidarchitecturecomponents.repository.model.Note


class NoteAdapter: ListAdapter<Note, NoteAdapter.ViewHolder> {

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Note> = object: DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.title.equals(newItem.title) &&
                        oldItem.description.equals(newItem.description) &&
                        oldItem.priority == newItem.priority
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(note: Note)
    }

    private var listener: OnItemClickListener? = null

    constructor() : super(DIFF_CALLBACK) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = getItem(position)
        holder.textViewTitle.text = note.title
        holder.textViewDescription.text = note.description
        holder.textViewPriority.text = note.priority.toString()
    }

    fun getNoteAt(position: Int): Note {
        return getItem(position)
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
                listener?.onItemClicked(getItem(adapterPosition))
            }
        }
    }
}