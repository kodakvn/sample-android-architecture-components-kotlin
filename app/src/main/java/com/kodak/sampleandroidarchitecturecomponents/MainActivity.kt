package com.kodak.sampleandroidarchitecturecomponents

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
//        noteViewModel.getAllNotes().observe(this, Observer<List<Note>> {
//            Toast.makeText(this@MainActivity, "onChanged", Toast.LENGTH_SHORT).show()
//        })

        noteViewModel = ViewModelProviders.of(this,CustomViewModelFactory(this)).get(NoteViewModel::class.java)

        noteViewModel.getAllNotes().observe(this, Observer<List<Note>> {
            Toast.makeText(this@MainActivity, "onChanged", Toast.LENGTH_SHORT).show()
        })
    }
}
