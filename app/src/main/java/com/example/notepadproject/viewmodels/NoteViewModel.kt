package com.example.notepadproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notepadproject.data.Note
import com.example.notepadproject.data.database.NoteDatabase
import com.example.notepadproject.data.database.entities.NotesTable
import com.example.notepadproject.data.repositories.NoteRepository
import com.example.notepadproject.mapToNote
import com.example.notepadproject.mapToTableNote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    // on below line we are creating a variable
    // for our all notes list and repository
    private val allTableNotes: LiveData<List<NotesTable>>
    private val repository: NoteRepository

    // on below line we are initializing
    // our dao, repository and all notes
    init {
        val dao = NoteDatabase.getDatabase(application).getNotesDao()
        repository = NoteRepository(dao)
        allTableNotes = repository.allNotes
    }

    // return all notes
    fun getAllNotes(): ArrayList<Note> {
        val allNotes: ArrayList<Note> = arrayListOf()
        allTableNotes.value?.forEach { allNotes.add(it.mapToNote()) }
        return allNotes
    }

    // on below line we are creating a new method for deleting a note. In this we are
    // calling a delete method from our repository to delete our note.
    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note.mapToTableNote())
    }

    // on below line we are creating a new method for updating a note. In this we are
    // calling a update method from our repository to update our note.
    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note.mapToTableNote())
    }


    // on below line we are creating a new method for adding a new note to our database
    // we are calling a method from our repository to add a new note.
    fun addNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note.mapToTableNote())
    }
}
