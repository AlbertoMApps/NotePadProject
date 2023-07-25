package com.example.notepadproject.ui.common

import com.example.notepadproject.data.model.Note
import java.text.SimpleDateFormat
import java.util.Date

val newNote = Note(
    name = "Untitled note",
    message = "",
    dateCreatedAt = SimpleDateFormat("dd MMM yyyy").format(
        Date()
    )
)