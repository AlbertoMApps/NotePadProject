package com.example.notepadproject.data.repositories

import com.example.notepadproject.data.model.Note
import com.example.notepadproject.presentation.Resource
import kotlinx.coroutines.flow.Flow

interface INotesRepository {
    fun getAllNotes(): Flow<Resource<List<Note>>>
    fun insert(note: Note): Flow<Resource<Note>>
    suspend fun delete(note: Note)
    suspend fun update(note: Note)
}