package com.example.notepadproject.data.repositories

import androidx.lifecycle.LiveData
import com.example.notepadproject.data.database.dao.NotesDao
import com.example.notepadproject.data.database.entities.NotesTable

class NoteRepository(private val notesDao: NotesDao) {

    val allNotes: LiveData<List<NotesTable>> = notesDao.getAllNotes()

    suspend fun insert(note: NotesTable) {
        notesDao.insert(note)
    }

    suspend fun delete(note: NotesTable) {
        notesDao.delete(note)
    }

    suspend fun update(note: NotesTable) {
        notesDao.update(note)
    }
}