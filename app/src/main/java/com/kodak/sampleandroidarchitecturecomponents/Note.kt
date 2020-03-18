package com.kodak.sampleandroidarchitecturecomponents

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(@PrimaryKey(autoGenerate = true) val id: Long = 0,
           val title: String,
           val description: String,
           val priority: Int)