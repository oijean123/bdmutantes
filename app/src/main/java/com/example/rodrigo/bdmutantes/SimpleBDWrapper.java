package com.example.rodrigo.bdmutantes;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SimpleBDWrapper extends SQLiteOpenHelper {
    public static final String MUTANTES = "Mutantes";
    public static final String HABILIDADES = "Habilidades";
    public static final String MUTANTE_NAME = "_name";
    public static final String MUTANTE_SKILL = "_skill";

    private static final String DATABASE_NAME = "Mutantes.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_MUTANTES = "CREATE TABLE IF NOT EXISTS " + MUTANTES + "(" + MUTANTE_NAME + " primary key not null, " + MUTANTE_SKILL + " text not null);";
    private static final String TABLE_HABILIDADES = "CREATE TABLE IF NOT EXISTS " + HABILIDADES + "(" + MUTANTE_NAME + " text not null, " + MUTANTE_SKILL + " text not null, foreign key(" + MUTANTE_NAME + ") REFERENCES MUTANTES(" + MUTANTE_NAME +") ON DELETE CASCADE)";

    public SimpleBDWrapper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(TABLE_HABILIDADES);
        db.execSQL(TABLE_MUTANTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int ondVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + MUTANTES);
        db.execSQL("DROP TABLE IF EXISTS " + HABILIDADES);
        onCreate(db);
    }
}
