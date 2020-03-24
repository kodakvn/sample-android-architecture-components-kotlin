package com.kodak.sampleandroidarchitecturecomponents.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kodak.sampleandroidarchitecturecomponents.*
import com.kodak.sampleandroidarchitecturecomponents.utils.Const.Companion.ADD_NOTE_REQUEST
import com.kodak.sampleandroidarchitecturecomponents.utils.Const.Companion.EDIT_NOTE_REQUEST
import com.kodak.sampleandroidarchitecturecomponents.utils.Const.Companion.EXTRA_DESCRIPTION
import com.kodak.sampleandroidarchitecturecomponents.utils.Const.Companion.EXTRA_PRIORITY
import com.kodak.sampleandroidarchitecturecomponents.utils.Const.Companion.EXTRA_TITLE
import com.kodak.sampleandroidarchitecturecomponents.update.AddEditNoteActivity
import com.kodak.sampleandroidarchitecturecomponents.repository.model.Note
import javax.inject.Inject
import com.kodak.sampleandroidarchitecturecomponents.utils.Const.Companion.EXTRA_ID as EXTRA_ID

class MainActivity : AppCompatActivity() {

    @Inject lateinit var viewModel: MainActivityViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var floatingButton: FloatingActionButton
    private val adapter = NoteAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupMainActivityComponent()

        floatingButton = findViewById(R.id.button_add_note)
        floatingButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
            startActivityForResult(intent, ADD_NOTE_REQUEST)
        }

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

//        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
//        viewModel.getAllNotes().observe(this, Observer<List<Note>> {
//            Toast.makeText(this@MainActivity, "onChanged", Toast.LENGTH_SHORT).show()
//            adapter.submitList(it)
//        })

        itemTouchCallback.attachToRecyclerView(recyclerView)

        adapter.setOnItemClickListener(object: NoteAdapter.OnItemClickListener {
            override fun onItemClicked(note: Note) {
                val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
                intent.putExtra(EXTRA_ID, note.id)
                intent.putExtra(EXTRA_TITLE, note.title)
                intent.putExtra(EXTRA_DESCRIPTION, note.description)
                intent.putExtra(EXTRA_PRIORITY, note.priority)
                startActivityForResult(intent, EDIT_NOTE_REQUEST)
            }
        })
    }

    private fun setupMainActivityComponent() {
        App.getInstance()
            .getAppComponent()
            .inject(MainActivityModule(this))
            .inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_all_notes -> {
                viewModel.deleteAllNotes()
                Toast.makeText(this, "All notes deleted", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ADD_NOTE_REQUEST -> {
                    val title = data?.getStringExtra(EXTRA_TITLE)
                    val description = data?.getStringExtra(EXTRA_DESCRIPTION)
                    val priority = data?.getIntExtra(EXTRA_PRIORITY, 1)

                    if (title == null || description == null || priority == null) {
                        Toast.makeText(this, "Note not saved!", Toast.LENGTH_SHORT).show()
                        return
                    }

                    val note = Note(title = title!!, description = description!!, priority = priority!!)
                    viewModel.insert(note)
                    Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show()
                }
                EDIT_NOTE_REQUEST -> {
                    val id = data?.getLongExtra(EXTRA_ID, -1L)

                    if (id == null || id!! == -1L) {
                        Toast.makeText(this, "Note can't be updated", Toast.LENGTH_SHORT).show()
                        return
                    }

                    val title = data?.getStringExtra(EXTRA_TITLE)
                    val description = data?.getStringExtra(EXTRA_DESCRIPTION)
                    val priority = data?.getIntExtra(EXTRA_PRIORITY, 1)

                    if (title == null || description == null || priority == null) {
                        Toast.makeText(this, "Note not saved!", Toast.LENGTH_SHORT).show()
                        return
                    }

                    val note = Note(id, title, description, priority)
                    viewModel.update(note)
                    Toast.makeText(this, "Note updated!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private val itemTouchCallback = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)) {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
            return super.getMovementFlags(recyclerView, viewHolder)
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun setDefaultDragDirs(defaultDragDirs: Int) {
            super.setDefaultDragDirs(defaultDragDirs)
        }

        override fun setDefaultSwipeDirs(defaultSwipeDirs: Int) {
            super.setDefaultSwipeDirs(defaultSwipeDirs)
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            viewModel.delete(adapter.getNoteAt(viewHolder.adapterPosition))
            Toast.makeText(this@MainActivity, "Note deleted", Toast.LENGTH_SHORT).show()
        }

        override fun getDragDirs(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
            return super.getDragDirs(recyclerView, viewHolder)
        }

        override fun getSwipeDirs(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
            return super.getSwipeDirs(recyclerView, viewHolder)
        }
    })
}
