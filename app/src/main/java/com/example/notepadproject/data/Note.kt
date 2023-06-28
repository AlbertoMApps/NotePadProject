package com.example.notepadproject.data

import java.text.SimpleDateFormat
import java.util.Date

class Note {
    var name: String = "First title note"
    var message: String? = "First message note"
    var dateCreatedAt: String = SimpleDateFormat("yyyy-MM-dd").format(Date())
}