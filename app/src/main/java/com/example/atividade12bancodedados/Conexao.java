package com.example.atividade12bancodedados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexao extends SQLiteOpenHelper
{
    public static final String NAME = "banco.db";
    public static final int VERSION = 1;
    private static final String SQL_CREATE = "CREATE TABLE CLIENTE (" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "NOME VARCHAR(50), " +
            "FONE VARCHAR(20), " +
            "EMAIL VARCHAR(50), " +
            "OBSERVACAO TEXT); ";

    public Conexao(@Nullable Context context)
    {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
