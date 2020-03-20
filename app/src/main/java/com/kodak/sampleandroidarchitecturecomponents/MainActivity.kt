package com.kodak.sampleandroidarchitecturecomponents

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var recyclerView: RecyclerView
    private val adapter = NoteAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
}
