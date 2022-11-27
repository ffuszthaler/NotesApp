package com.ffuszthaler.notesapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context): SQLiteOpenHelper(context, dbName, null, 1 ) {
    private val allNotes: MutableList<Note> = mutableListOf()

    companion object MessageDatabase{
        private const val dbName:String = "NotesDB"
        private const val tableName = "notes"
        private const val id = "id"
        private const val title = "title"
        private const val body = "body"
        private const val category = "category"
    }

    // create database
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS $tableName ($id INTEGER PRIMARY KEY, $title VARCHAR(30), $body VARCHAR(100), $category VARCHAR(50));")
    }

    // delete table if it already exists
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $tableName")
        onCreate(db)
    }

    // add new note entry to table
    fun insertData(noteTitle: String, noteBody: String, noteCategory: String) {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(title, noteTitle)
        contentValues.put(body, noteBody)
        contentValues.put(category, noteCategory)

        db.insert(tableName, null, contentValues)
    }

    // update a specific note
    fun updateData(noteId: String, noteTitle: String, noteBody: String, noteCategory: String): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()

        contentValues.put(title, noteTitle)
        contentValues.put(body, noteBody)
        contentValues.put(category, noteCategory)

        return db.update(tableName, contentValues, "_id = ?", arrayOf(noteId))
    }

    // delete a specific note
    fun deleteData(noteId : String) : Int {
        val db = this.writableDatabase
        return db.delete(tableName,"_id = ?", arrayOf(noteId))
    }

    // get list of all notes
    fun listOfAllNotes(): MutableList<Note>  {
        val db = this.readableDatabase

        // initialize list
//        val allNotes: MutableList<Note> = mutableListOf()

        // get all entries from table and put it into the list
        db.rawQuery("SELECT * FROM $tableName",null).use {
            while(it.moveToNext()){
                allNotes.add(
                    Note(
                        title = it.getString(it.getColumnIndex(MessageDatabase.title) as Int),
                        body = it.getString(it.getColumnIndex(MessageDatabase.body) as Int),
                        category = it.getString(it.getColumnIndex(MessageDatabase.category) as Int)
                    )
                )
            }
        }

        return allNotes
    }
}