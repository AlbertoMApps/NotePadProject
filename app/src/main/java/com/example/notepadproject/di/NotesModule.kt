package com.example.notepadproject.di

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.notepadproject.data.database.NotesDatabase
import com.example.notepadproject.data.repositories.INotesRepository
import com.example.notepadproject.data.repositories.NotesRepository
import com.example.notepadproject.presentation.NoteViewModel
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
    fun provideNoteViewModel(repository: INotesRepository): ViewModel =
        NoteViewModel(repository)

    @Provides
    @Singleton
    fun provideINotesRepository(
        db: NotesDatabase
    ): INotesRepository = NotesRepository(
        db.getNotesDao()
    )

    @Provides
    @Singleton
    fun provideINotesDatabase(app: Application): NotesDatabase =
        Room.databaseBuilder(
            app,
            NotesDatabase::class.java,
            "notes_db"
        ).build()

}