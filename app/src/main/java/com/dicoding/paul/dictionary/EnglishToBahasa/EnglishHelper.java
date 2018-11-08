package com.dicoding.paul.dictionary.EnglishToBahasa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.dicoding.paul.dictionary.EnglishToBahasa.EnglishDatabaseContract.EnglishColumns.DEFINITION;
import static com.dicoding.paul.dictionary.EnglishToBahasa.EnglishDatabaseContract.EnglishColumns.WORD;
import static com.dicoding.paul.dictionary.EnglishToBahasa.EnglishDatabaseContract.TABLE_NAME;

public class EnglishHelper {
    private Context context;
    private EnglishDatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public EnglishHelper(Context context) {
        this.context = context;
    }

    public EnglishHelper open() throws SQLException {
        databaseHelper = new EnglishDatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    public ArrayList<EnglishModel> getDataByWord(String word) {
        String result = "";
        Cursor cursor = database.query(TABLE_NAME, null, WORD+" LIKE ?",
                new String[]{word}, null, null, _ID+" ASC", null);
        cursor.moveToFirst();
        ArrayList<EnglishModel> arrayList = new ArrayList<>();
        EnglishModel englishModel;
        if (cursor.getCount()>0) {
            do {
                englishModel = new EnglishModel();
                englishModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                englishModel.setWord(cursor.getString(cursor.getColumnIndexOrThrow(WORD)));
                englishModel.setDefinition(cursor.getString(cursor.getColumnIndexOrThrow(DEFINITION)));

                arrayList.add(englishModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<EnglishModel> getAllData() {
        Cursor cursor = database.query(TABLE_NAME, null, null, null,
                null, null, _ID+" ASC",null);
        cursor.moveToFirst();
        ArrayList<EnglishModel> arrayList = new ArrayList<>();
        EnglishModel englishModel;
        if (cursor.getCount()>0) {
            do {
                englishModel = new EnglishModel();
                englishModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                englishModel.setWord(cursor.getString(cursor.getColumnIndexOrThrow(WORD)));
                englishModel.setDefinition(cursor.getString(cursor.getColumnIndexOrThrow(DEFINITION)));

                arrayList.add(englishModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(EnglishModel englishModel){
        ContentValues initialValues = new ContentValues();
        initialValues.put(WORD, englishModel.getWord());
        initialValues.put(DEFINITION, englishModel.getDefinition());
        return database.insert(TABLE_NAME, null, initialValues);
    }

    public void beginTransaction() {
        database.beginTransaction();
    }

    public void setTransactionSuccess() {
        database.setTransactionSuccessful();
    }

    public void endTransaction() {
        database.endTransaction();
    }

    public void insertTransaction(EnglishModel englishModel) {
        String SQL = "INSERT INTO "+TABLE_NAME+" ("+WORD+", "+DEFINITION+") VALUES (?, ?)";
        SQLiteStatement statement = database.compileStatement(SQL);
        statement.bindString(1, englishModel.getWord());
        statement.bindString(2, englishModel.getDefinition());
        statement.execute();
        statement.clearBindings();
    }

    public int update(EnglishModel englishModel) {
        ContentValues args = new ContentValues();
        args.put(WORD, englishModel.getWord());
        args.put(DEFINITION, englishModel.getDefinition());
        return database.update(TABLE_NAME, args, _ID+"= '"+englishModel.getId()+
        "'", null);
    }

    public int delete(int id) {
        return database.delete(TABLE_NAME, _ID+" = '"+id+"'", null);
    }
}
