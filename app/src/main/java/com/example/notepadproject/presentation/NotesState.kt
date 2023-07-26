package com.example.notepadproject.presentation

import com.example.notepadproject.data.model.Note

data class NotesState(
    val isLoading: Boolean = false,
    val list: List<Note> = emptyList(),
    val errorMessage: String = ""
)
