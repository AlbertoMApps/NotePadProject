package com.example.notepadproject.data.repositories

import androidx.lifecycle.LiveData
import com.example.notepadproject.data.database.entities.NotesTable

interface INotesRepository {
    val allNotes: LiveData<List<NotesTable>>
    suspend fun insert(note: NotesTable)
    suspend fun delete(note: NotesTable)
    suspend fun update(note: NotesTable)
}