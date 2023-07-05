package com.example.notepadproject.di

import androidx.room.Room
import com.example.notepadproject.NotepadProjectApplication
import com.example.notepadproject.data.database.NotesDatabase
import com.example.notepadproject.data.repositories.INotesRepository
import com.example.notepadproject.data.repositories.NotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotesModule {

    @Provides
    @Singleton
    fun provideINotesRepository(
        db: NotesDatabase
    ): INotesRepository = NotesRepository(
        db.getNotesDao()
    )

    @Provides
    @Singleton
    fun provideINotesDatabase(app: NotepadProjectApplication): NotesDatabase =
        Room.databaseBuilder(
            app,
            NotesDatabase::class.java,
            "notes_db"
        ).build()

}