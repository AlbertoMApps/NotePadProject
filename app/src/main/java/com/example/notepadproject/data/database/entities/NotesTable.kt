package com.example.notepadproject.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")
data class NotesTable(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "message")
    val message: String,
    @ColumnInfo(name = "dateCreatedAt")
    val dateCreatedAt: String
)