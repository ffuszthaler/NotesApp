package com.ffuszthaler.notesapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(val id: String, val title: String, val body: String, val category: String): Parcelable
