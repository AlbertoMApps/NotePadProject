package com.example.notepadproject.data.repositories

import com.example.notepadproject.data.database.entities.NotesTable

interface INotesRepository {
    suspend fun insert(note: NotesTable)
    suspend fun delete(note: NotesTable)
    suspend fun update(note: NotesTable)
}