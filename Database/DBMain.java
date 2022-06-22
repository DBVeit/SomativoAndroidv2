package com.example.mycartandroidv2.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBMain extends SQLiteOpenHelper {

    public static final String DBNAME = "myCartShop2";
    public static final String TABLENAME = "listaCompra";
    public static final int VER = 1;
    String query;

    public DBMain(@Nullable Context context) {
        super(context, DBNAME, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        query = "CREATE TABLE "+TABLENAME+"(idItem INTEGER PRIMARY KEY AUTOINCREMENT, nomeItem TEXT, qtdItem INTEGER, valorItem REAL, chkItem INTEGER, nomeLista TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        query="DROP TABLE IF EXISTS "+TABLENAME+"";
        db.execSQL(query);
        onCreate(db);
    }
}