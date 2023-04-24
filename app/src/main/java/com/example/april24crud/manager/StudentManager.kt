package com.example.april24crud.manager

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.april24crud.database.StudentDbHelper
import com.example.april24crud.model.Student

class StudentManager(context: Context) {
    private var studentDbHelper = StudentDbHelper(context)
    private val db: SQLiteDatabase = studentDbHelper.writableDatabase

    fun addStudent(student: Student) {
        val query =
            "insert into ${StudentDbHelper.TABLE_NAME}(${StudentDbHelper.COL_NAME},${StudentDbHelper.COL_CLASS_NAME})" + " values('${student.name}','${student.className}')"
        db.execSQL(query)
    }

    fun addStudent2(student: Student) {
        val contentValues = ContentValues()
        contentValues.put("${StudentDbHelper.COL_NAME}", student.name)
        contentValues.put("${StudentDbHelper.COL_CLASS_NAME}", student.className)
        db.insert(StudentDbHelper.TABLE_NAME, null, contentValues)
    }

    fun getAllStudent(): ArrayList<Student> {
        val students = arrayListOf<Student>()
        val query = "select * from ${StudentDbHelper.TABLE_NAME}"
        val cursor = db.rawQuery(query, null)

        val indexId = cursor.getColumnIndex(StudentDbHelper.COL_ID)
        val indexName = cursor.getColumnIndex(StudentDbHelper.COL_NAME)
        val indexClassName = cursor.getColumnIndex(StudentDbHelper.COL_CLASS_NAME)

        val haveData: Boolean = cursor.moveToFirst()
        if (haveData) {
            while (cursor.isAfterLast) {
                val id = cursor.getInt(indexId)
                val name = cursor.getString(indexName)
                val className = cursor.getString(indexClassName)

                students.add(Student(id, name, className))
                cursor.moveToNext()
            }
        }
        return students
    }

    fun getAllStudent2(): ArrayList<Student> {
        val students = arrayListOf<Student>()
        val cursor = db.query(
            StudentDbHelper.TABLE_NAME, arrayOf<String>(
                StudentDbHelper.COL_ID, StudentDbHelper.COL_NAME, StudentDbHelper.COL_CLASS_NAME
            ), null, null, null, null, null, null
        )
//        val cursor = db.query(
//            StudentDbHelper.TABLE_NAME,
//            null, null, null, null, null, null, null
//        )

        val indexId = cursor.getColumnIndex(StudentDbHelper.COL_ID)
        val indexName = cursor.getColumnIndex(StudentDbHelper.COL_NAME)
        val indexClassName = cursor.getColumnIndex(StudentDbHelper.COL_CLASS_NAME)

        val haveData: Boolean = cursor.moveToFirst()
        if (haveData) {
            while (cursor.isAfterLast) {
                val id = cursor.getInt(indexId)
                val name = cursor.getString(indexName)
                val className = cursor.getString(indexClassName)

                students.add(Student(id, name, className))
                cursor.moveToNext()
            }
        }
        return students
    }

    fun getStudent(id: Int): Student {
        val cursor = db.query(
            StudentDbHelper.TABLE_NAME,
            null,
            StudentDbHelper.COL_ID,
            arrayOf("${id}"),
            null,
            null,
            null,
            "1"
        )
        val haveData = cursor.moveToFirst()
        val indexId = cursor.getColumnIndex(StudentDbHelper.COL_ID)
        val indexName = cursor.getColumnIndex(StudentDbHelper.COL_NAME)
        val indexClassName = cursor.getColumnIndex(StudentDbHelper.COL_CLASS_NAME)

        val id = cursor.getInt(indexId)
        val name = cursor.getString(indexName)
        val className = cursor.getString(indexClassName)

        return Student(id, name, className)
    }

    fun updateStudent(student: Student) {
        val query =
            "update ${StudentDbHelper.TABLE_NAME} set ${StudentDbHelper.COL_NAME}='${student.name}'" + ",${StudentDbHelper.COL_CLASS_NAME}='${student.className}' where ${StudentDbHelper.COL_ID}=${student.id}"
        db.execSQL(query)

//        db.update()
    }

    fun deleteStudent(id: Int) {
        val query =
            "delete from ${StudentDbHelper.TABLE_NAME} where ${StudentDbHelper.COL_ID}=${id}"
        db.execSQL(query)
//        db.delete()
    }


}
