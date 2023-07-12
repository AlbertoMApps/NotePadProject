package com.example.notepadproject.data.repositories

import com.example.notepadproject.data.database.dao.NotesDao
import com.example.notepadproject.data.database.entities.NotesTable
import com.example.notepadproject.data.model.Note
import com.example.notepadproject.mapToNote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NotesRepository @Inject constructor(private val notesDao: NotesDao) : INotesRepository {

    override suspend fun getAllNotes(): Flow<List<Note>> = flow {
        notesDao.getAllNotes().map { it.mapToNote() }
    }

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