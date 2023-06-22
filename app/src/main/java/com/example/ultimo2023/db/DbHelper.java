package com.example.ultimo2023.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "producto.db";
    public static final String TABLE_PRODUCTOS = "productos";
    public static final String COLUMN_NOMBRE="nombre";
    public static final String COLUMN_ID = "id";


    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PRODUCTOS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOMBRE + " TEXT, " + "precio INTEGER, "+
                "imagen TEXT, " + "descripcion TEXT"+
                ");";
        //CREATE TABLE productos (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT);

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTOS);
        onCreate(db);
    }
}
