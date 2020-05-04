package com.example.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper (Context context){
        super(context,"score.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String s="create table Personne(nom Text, prenom Text, age INTEGER);" ;
        db.execSQL(s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
