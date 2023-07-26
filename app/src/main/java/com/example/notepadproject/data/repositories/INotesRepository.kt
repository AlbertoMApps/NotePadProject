package com.example.notepadproject.data.repositories

import com.example.notepadproject.data.model.Note
import com.example.notepadproject.presentation.Resource
import kotlinx.coroutines.flow.Flow

interface INotesRepository {
    fun getAllNotes(notes: List<Note>): Flow<Resource<List<Note>>>
    suspend fun delete(note: Note)

}