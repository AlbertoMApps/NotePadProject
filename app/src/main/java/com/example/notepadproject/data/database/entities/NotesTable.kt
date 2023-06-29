package com.example.notepadproject.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")

// Colum info of NotesTable
class NotesTable(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "message") val message: String,
    @ColumnInfo(name = "dateCreatedAt") val dateCreatedAt: String
) {
    // on below line we are specifying our key and
    // then auto generate as true and we are
    // specifying its initial value as 0
    @PrimaryKey(autoGenerate = true)
    var id = 0
}