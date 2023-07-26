package com.example.notepadproject.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notepadproject.data.model.Note
import com.example.notepadproject.data.repositories.INotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
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

    private var noteJob: Job? = null

    // return all notes with flow
    fun getAllNotes(notes: List<Note>) {
        noteJob?.cancel()
        noteJob = viewModelScope.launch {
            notesRepository.getAllNotes(notes).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = NotesState(
                            list = result.data ?: listOf()
                        )
                    }

                    else -> {
                        _state.value = NotesState(
                            errorMessage = result.errorMessage ?: "No note found or added"
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    // on below line we are creating a new method for deleting a note. In this we are
    // calling a delete method from our repository to delete our note.
    fun deleteNote(note: Note) {
        noteJob?.cancel()
        noteJob = viewModelScope.launch(Dispatchers.IO) {
            notesRepository.delete(note)
        }
    }

}

sealed class Resource<T>(
    val data: T? = null,
    val errorMessage: String? = null
) {
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Success<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}
