package com.ktln.noteapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "notes_table")
data class Note (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title:String,
    val detail:String
) : Parcelable