package com.example.notepadproject.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notepadproject.data.database.entities.NotesTable

@Dao
interface NotesDao {

    // below is the insert method for
    // adding a new entry to our database.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: NotesTable)

    // method to delete a note.
    @Delete
    suspend fun delete(note: NotesTable)

    // method to get all the entry notes from our db ordered by ascendant.
    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes(): LiveData<List<NotesTable>>

    // method to refresh the notes.
    @Update
    suspend fun update(note: NotesTable)

}