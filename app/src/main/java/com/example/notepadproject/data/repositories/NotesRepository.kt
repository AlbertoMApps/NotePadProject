package com.example.notepadproject.data.repositories

import com.example.notepadproject.data.database.dao.NotesDao
import com.example.notepadproject.data.model.Note
import com.example.notepadproject.mapToNote
import com.example.notepadproject.mapToTableNote
import com.example.notepadproject.presentation.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NotesRepository @Inject constructor(private val notesDao: NotesDao) : INotesRepository {

    override fun getAllNotes(): Flow<Resource<List<Note>>> = flow {
        val notes = notesDao.getAllNotes().map { it.mapToNote() }
        if (notes.isEmpty()) {
            emit(Resource.Error(message = "Empty list"))
        } else {
            emit(Resource.Success(data = notes))
        }
    }

    override fun insert(note: Note): Flow<Resource<Note>> = flow {
        notesDao.insert(note.mapToTableNote())
        getAllNotes()
    }

    override suspend fun delete(note: Note) {
        notesDao.delete(note.mapToTableNote())
    }

    override suspend fun update(note: Note) {
        notesDao.update(note.mapToTableNote())
    }
}