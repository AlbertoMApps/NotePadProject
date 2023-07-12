package com.example.notepadproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notepadproject.data.database.dao.NotesDao
import com.example.notepadproject.data.database.entities.NotesTable

private const val NOTES_TABLE_VERSION = 1

@Database(entities = [NotesTable::class], version = NOTES_TABLE_VERSION)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun getNotesDao(): NotesDao

}
