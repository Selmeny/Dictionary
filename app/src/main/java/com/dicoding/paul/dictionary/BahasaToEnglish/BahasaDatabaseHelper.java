package com.dicoding.paul.dictionary.BahasaToEnglish;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.dicoding.paul.dictionary.BahasaToEnglish.BahasaDatabaseContract.BahasaColumns.DEFINISI;
import static com.dicoding.paul.dictionary.BahasaToEnglish.BahasaDatabaseContract.BahasaColumns.KATA;
import static com.dicoding.paul.dictionary.BahasaToEnglish.BahasaDatabaseContract.TABLE_NAME;

public class BahasaDatabaseHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "dbbahasa";
    private static final int DATABASE_VERSION = 1;

    public static String CREATE_BAHASA_TABLE = "create table "+TABLE_NAME+
            " ("+_ID+" integer primary key autoincrement, "+
            KATA+" text not null, "+
            DEFINISI+" text not null);";

    public BahasaDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BAHASA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}
