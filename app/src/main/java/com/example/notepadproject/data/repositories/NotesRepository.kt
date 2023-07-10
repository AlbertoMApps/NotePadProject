package com.example.notepadproject.data.repositories

import androidx.lifecycle.LiveData
import com.example.notepadproject.data.database.dao.NotesDao
import com.example.notepadproject.data.database.entities.NotesTable
import javax.inject.Inject

class NotesRepository @Inject constructor(private val notesDao: NotesDao) : INotesRepository {

    override val allNotes: LiveData<List<NotesTable>> = notesDao.getAllNotes()

    override suspend fun insert(note: NotesTable) {
        notesDao.insert(note)
    }

    override suspend fun delete(note: NotesTable) {
        notesDao.delete(note)
    }

    override suspend fun update(note: NotesTable) {
        notesDao.update(note)
    }
}