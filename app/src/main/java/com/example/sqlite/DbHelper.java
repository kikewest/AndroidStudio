package com.example.sqlite;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=2;
    private static final String DATABASE_NAME="ejemplo.db";
    private static final String TABLE_USUARIOS="usuarios";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_USUARIOS +"("+
                "id INTEGER PRIMARY KEY," +
                "nombre TEXT," +
                "dni TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE usuarios");
        onCreate(db);
    }
    public void insertar(String nombre,String dni){
        SQLiteDatabase bd= getWritableDatabase();
        bd.execSQL("INSERT into usuarios values('"+nombre+"','"+dni);
    }

}
