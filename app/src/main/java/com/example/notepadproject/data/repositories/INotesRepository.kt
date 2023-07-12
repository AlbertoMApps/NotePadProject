package com.example.notepadproject.data.repositories

import com.example.notepadproject.data.database.entities.NotesTable
import com.example.notepadproject.data.model.Note
import kotlinx.coroutines.flow.Flow

interface INotesRepository {
    suspend fun getAllNotes(): Flow<List<Note>>
    suspend fun insert(note: NotesTable)
    suspend fun delete(note: NotesTable)
    suspend fun update(note: NotesTable)
}