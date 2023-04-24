package com.example.april24crud.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class StudentDbHelper(context: Context) : SQLiteOpenHelper(context, "student.db", null, 1) {
    companion object {
        const val TABLE_NAME = "student"
        const val COL_ID = "_id"
        const val COL_NAME = "_name"
        const val COL_CLASS_NAME = "_class"
        val CREATE_TABLE_STUDENT =
            "create table $TABLE_NAME($COL_ID integer primary key autoincrement,$COL_NAME text,$COL_CLASS_NAME text)"

    }


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_STUDENT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}