package com.kodak.sampleandroidarchitecturecomponents

import android.R.attr
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kodak.sampleandroidarchitecturecomponents.Const.Companion.ADD_NOTE_REQUEST
import com.kodak.sampleandroidarchitecturecomponents.Const.Companion.EXTRA_DESCRIPTION
import com.kodak.sampleandroidarchitecturecomponents.Const.Companion.EXTRA_PRIORITY
import com.kodak.sampleandroidarchitecturecomponents.Const.Companion.EXTRA_TITLE


class MainActivity : AppCompatActivity() {

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var floatingButton: FloatingActionButton
    private val adapter = NoteAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        floatingButton = findViewById(R.id.button_add_note)
        floatingButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AddNoteActivity::class.java)
            startActivityForResult(intent, ADD_NOTE_REQUEST)
        }

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        noteViewModel = ViewModelProviders.of(this, CustomViewModelFactory(this)).get(NoteViewModel::class.java)
        noteViewModel.getAllNotes().observe(this, Observer<List<Note>> {
            Toast.makeText(this@MainActivity, "onChanged", Toast.LENGTH_SHORT).show()
            adapter.setNotes(it)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        var note: Note? = null
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ADD_NOTE_REQUEST -> {
                    val title = data?.getStringExtra(EXTRA_TITLE)
                    val description = data?.getStringExtra(EXTRA_DESCRIPTION)
                    val priority = data?.getIntExtra(EXTRA_PRIORITY, 1)

                    if (title != null && description != null && priority != null)
                    note = Note(title = title!!, description = description!!, priority = priority!!)
                }
            }

            if (note != null) {
                noteViewModel.insert(note)
                Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show()
            }
        }

        if (note == null) {
            Toast.makeText(this, "Note not saved!", Toast.LENGTH_SHORT).show()
            Toast.makeText(this, "Note not saved!", Toast.LENGTH_SHORT).show()
        }
    }
}
