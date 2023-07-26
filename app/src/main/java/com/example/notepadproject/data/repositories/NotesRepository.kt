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

    override fun getAllNotes(notes: List<Note>): Flow<Resource<List<Note>>> = flow {
        emit(Resource.Loading())
        notesDao.insert(notes.map { it.mapToTableNote() })
        val newNotes = notesDao.getAllNotes().map { it.mapToNote() }
        emit(Resource.Loading(data = newNotes))
        if (newNotes.isEmpty()) {
            emit(Resource.Error(message = "Empty list"))
        } else {
            emit(Resource.Success(data = newNotes))
        }
    }

    override fun delete(noteId: Int): Flow<Resource<List<Note>>> = flow {
        emit(Resource.Loading())
        notesDao.delete(noteId)
        val newNotes = notesDao.getAllNotes().map { it.mapToNote() }
        emit(Resource.Loading(data = newNotes))
        if (newNotes.isEmpty()) {
            emit(Resource.Error(message = "Empty list"))
        } else {
            emit(Resource.Success(data = newNotes))
        }
    }

}