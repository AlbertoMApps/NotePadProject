package com.example.notepadproject

import com.example.notepadproject.data.Note
import com.example.notepadproject.data.database.entities.NotesTable

fun Note.mapToTableNote() =
    NotesTable(name, message, dateCreatedAt)

fun NotesTable.mapToNote() =
    Note(name, message, dateCreatedAt)
