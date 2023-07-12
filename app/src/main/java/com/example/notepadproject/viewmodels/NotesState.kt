package com.example.notepadproject.viewmodels

import com.example.notepadproject.data.model.Note

data class NotesState(
    val isLoading: Boolean = false,
    val list: List<Note> = emptyList(),
    val message: String = ""
)