package com.example.notepadproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notepadproject.data.database.NoteDatabase
import com.example.notepadproject.data.database.entities.NotesTable
import com.example.notepadproject.data.repositories.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    // on below line we are creating a variable
    // for our all notes list and repository
    private val allNotes: LiveData<List<NotesTable>>
    private val repository: NoteRepository

    // on below line we are initializing
    // our dao, repository and all notes
    init {
        val dao = NoteDatabase.getDatabase(application).getNotesDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    // return all notes
    fun getAllNotes() = allNotes.value

    // on below line we are creating a new method for deleting a note. In this we are
    // calling a delete method from our repository to delete our note.
    fun deleteNote(note: NotesTable) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    // on below line we are creating a new method for updating a note. In this we are
    // calling a update method from our repository to update our note.
    fun updateNote(note: NotesTable) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }


    // on below line we are creating a new method for adding a new note to our database
    // we are calling a method from our repository to add a new note.
    fun addNote(note: NotesTable) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}
