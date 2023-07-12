package com.example.notepadproject

import com.example.notepadproject.data.database.entities.NotesTable
import com.example.notepadproject.data.model.Note

fun Note.mapToTableNote() =
    NotesTable(name = name, message = message, dateCreatedAt = dateCreatedAt)

fun NotesTable.mapToNote() =
    Note(name = name, message = message, dateCreatedAt = dateCreatedAt)
