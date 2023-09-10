package com.ktln.noteapp.repository

import androidx.lifecycle.LiveData
import com.ktln.noteapp.data.NotesDao
import com.ktln.noteapp.model.Note

class NoteRepository(private val notesDao: NotesDao) {

    val readAllData:LiveData<List<Note>> = notesDao.readAllData()

    suspend fun addNote(note: Note){
        notesDao.addNote(note)
    }

    suspend fun updateNote(note: Note){
        notesDao.updateNote(note)
    }

    suspend fun deleteNote(note: Note){
        notesDao.deleteNote(note)
    }

    suspend fun deleteAllNotes(){
        notesDao.deleteAllNotes()
    }

}