package com.nomads.ethanolmixcalculator

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION)
{

    override fun onCreate(db: SQLiteDatabase) {
        var createQuery = ("CREATE TABLE $TABLE_NAME ($NAME_COL TEXT, " +
                "$TANK_SIZE_GAL_COL REAL, $TANK_SIZE_L_COL REAL)")

        db.execSQL(createQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addCar(name: String, tankSizeGal: Double, tankSizeL: Double) {
        // String-key class
        val values = ContentValues()

        values.put(NAME_COL, name)
        values.put(TANK_SIZE_GAL_COL, tankSizeGal)
        values.put(TANK_SIZE_L_COL, tankSizeL)

        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values);

        db.close()
    }

    fun getAllCars(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    companion object { // Static
        private const val DATABASE_NAME = "ETHANOL_MIX_DB"
        private val DATABASE_VERSION = 1

        var TABLE_NAME = "car_table"
        val NAME_COL = "name"
        val TANK_SIZE_GAL_COL = "tank_size_gal"
        val TANK_SIZE_L_COL = "tank_size_l"
    }
}