package com.example.notepadproject.data.database.dao

import androidx.room.*
import com.example.notepadproject.data.database.entities.NotesTable

@Dao
interface NotesDao {

    // method to get all the entry notes from our db ordered by ascendant.
    @Query("SELECT * FROM notesTable order by id ASC")
    suspend fun getAllNotes(): List<NotesTable>

    // below is the insert method for
    // adding a new entry to our database.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notes: List<NotesTable>)

    // method to delete a note.
    @Query("DELETE FROM notesTable WHERE id = :id")
    suspend fun delete(id: Int)

}