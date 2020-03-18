package com.kodak.sampleandroidarchitecturecomponents

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(@PrimaryKey(autoGenerate = true) private var id: Long = 0,
           private val title: String,
           private val description: String,
           private val priority: Int)