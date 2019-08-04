package com.yash.androidsample

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHandler(
    context: Context, name: String?,
    factory: SQLiteDatabase.CursorFactory?, version: Int
) :
    SQLiteOpenHelper(
        context, DATABASE_NAME,
        factory, DATABASE_VERSION
    ) {

    companion object {

        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "sample.db"
        val TABLE_USER = "users"
        val TABLE_ALERTEMAIL = "alertemail"

        val COLUMN_USERNAME = "name"
        val COLUMN_USERPASSWORD = "password"

        val COLUMN_EMAIL = "emailid"
    }

    override fun onCreate(db: SQLiteDatabase) {
        var CREATE_PRODUCTS_TABLE = ("CREATE TABLE " +
                TABLE_USER + "("
                + COLUMN_USERNAME + " TEXT PRIMARY KEY," +
                COLUMN_USERPASSWORD
                + " TEXT" + ")")
        db.execSQL(CREATE_PRODUCTS_TABLE)

        CREATE_PRODUCTS_TABLE = ("CREATE TABLE " +
                TABLE_ALERTEMAIL + "("
                + COLUMN_USERNAME + " TEXT," +
                COLUMN_EMAIL
                + " TEXT" + ")")
        db.execSQL(CREATE_PRODUCTS_TABLE)
    }

    override fun onUpgrade(
        db: SQLiteDatabase, oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER)
        onCreate(db)

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALERTEMAIL)
        onCreate(db)
    }

    fun AddUser(user: UserModel): Boolean {
        val db = this.writableDatabase
        var success: Boolean
        try {
            val values = ContentValues()
            values.put(COLUMN_USERNAME, user.username)
            values.put(COLUMN_USERPASSWORD, user.userpassword)

            db.insert(TABLE_USER, null, values)
            success = true
        } catch (e: Exception) {
            success = false
        } finally {
            db.close()
        }
        return success
    }

    fun GetUsers(): ArrayList<UserModel> {
        val query = "SELECT * FROM $TABLE_USER"

        val db = this.writableDatabase

        val cursor = db.rawQuery(query, null)
        cursor!!.moveToFirst()

        var users = ArrayList<UserModel>()

        do {
            val name = cursor.getString(0)
            val password = cursor.getString(1)
            val modelobj = UserModel(name, password)

            users.add(modelobj)
        } while (cursor.moveToNext())
        cursor.close()

        db.close()
        return users
    }

    fun Addemail(email: AlertModel) {
        val values = ContentValues()
        values.put(COLUMN_EMAIL, email.userEmail)
    }
}
