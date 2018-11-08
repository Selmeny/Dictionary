package com.dicoding.paul.dictionary.EnglishToBahasa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.dicoding.paul.dictionary.EnglishToBahasa.EnglishDatabaseContract.EnglishColumns.DEFINITION;
import static com.dicoding.paul.dictionary.EnglishToBahasa.EnglishDatabaseContract.EnglishColumns.WORD;
import static com.dicoding.paul.dictionary.EnglishToBahasa.EnglishDatabaseContract.TABLE_NAME;

public class EnglishDatabaseHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "dbenglish";
    private static final int DATABASE_VERSION = 1;

    public static String CREATE_TABLE_ENGLISH = "create table "+TABLE_NAME+
            " ("+_ID+" integer primary key autoincrement, "+
            WORD+" text not null, "+
            DEFINITION+" text not null);";

    public EnglishDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ENGLISH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}
