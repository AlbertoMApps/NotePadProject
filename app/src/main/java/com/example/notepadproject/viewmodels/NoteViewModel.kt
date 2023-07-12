package com.example.notepadproject.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notepadproject.data.model.Note
import com.example.notepadproject.data.repositories.INotesRepository
import com.example.notepadproject.mapToTableNote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NoteViewModel @Inject constructor(
    private val notesRepository: INotesRepository
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    // return all notes with flow
    fun getAllNotes() {
        viewModelScope.launch {
            notesRepository.getAllNotes().onEach {
                _state.value = NotesState(
                    list = it
                )
            }.launchIn(this)
        }
    }

    // on below line we are creating a new method for deleting a note. In this we are
    // calling a delete method from our repository to delete our note.
    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        notesRepository.delete(note.mapToTableNote())
    }

    // on below line we are creating a new method for updating a note. In this we are
    // calling a update method from our repository to update our note.
    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        notesRepository.update(note.mapToTableNote())
    }


    // on below line we are creating a new method for adding a new note to our database
    // we are calling a method from our repository to add a new note.
    fun addNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        notesRepository.insert(note.mapToTableNote())
    }
}
