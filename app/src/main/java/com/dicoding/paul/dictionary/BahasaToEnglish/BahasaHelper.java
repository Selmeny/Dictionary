package com.dicoding.paul.dictionary.BahasaToEnglish;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.dicoding.paul.dictionary.BahasaToEnglish.BahasaDatabaseContract.BahasaColumns.DEFINISI;
import static com.dicoding.paul.dictionary.BahasaToEnglish.BahasaDatabaseContract.BahasaColumns.KATA;
import static com.dicoding.paul.dictionary.BahasaToEnglish.BahasaDatabaseContract.TABLE_NAME;

public class BahasaHelper {
    private Context context;
    private BahasaDatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public BahasaHelper(Context context) {
        this.context = context;
    }

    public BahasaHelper open() throws SQLException {
        databaseHelper = new BahasaDatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    public ArrayList<BahasaModel> getDataByKata(String kata) {
        String result = "";
        Cursor cursor = database.query(TABLE_NAME, null, KATA+" LIKE ?",
                new String[]{kata}, null, null, _ID+" ASC", null);
        cursor.moveToFirst();
        ArrayList<BahasaModel> arrayList = new ArrayList<>();
        BahasaModel bahasaModel;
        if (cursor.getCount()>0){
            do {
                bahasaModel = new BahasaModel();
                bahasaModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                bahasaModel.setKata(cursor.getString(cursor.getColumnIndexOrThrow(KATA)));
                bahasaModel.setDefinisi(cursor.getString(cursor.getColumnIndexOrThrow(DEFINISI)));

                arrayList.add(bahasaModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<BahasaModel> getAllData() {
        Cursor cursor = database.query(TABLE_NAME, null, null, null,
                null, null, _ID+" ASC", null);
        cursor.moveToFirst();
        ArrayList<BahasaModel> arrayList = new ArrayList<>();
        BahasaModel bahasaModel;
        if (cursor.getCount()>0) {
            do {
                bahasaModel = new BahasaModel();
                bahasaModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                bahasaModel.setKata(cursor.getString(cursor.getColumnIndexOrThrow(KATA)));
                bahasaModel.setDefinisi(cursor.getString(cursor.getColumnIndexOrThrow(DEFINISI)));

                arrayList.add(bahasaModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(BahasaModel bahasaModel) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KATA, bahasaModel.getKata());
        initialValues.put(DEFINISI, bahasaModel.getDefinisi());
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

    public void insertTransaction(BahasaModel bahasaModel) {
        String SQL = "INSERT INTO "+TABLE_NAME+" ("+KATA+", "+DEFINISI+") VALUES (?, ?)";
        SQLiteStatement statement = database.compileStatement(SQL);
        statement.bindString(1, bahasaModel.getKata());
        statement.bindString(2, bahasaModel.getDefinisi());
        statement.execute();
        statement.clearBindings();
    }

    public int update(BahasaModel bahasaModel){
        ContentValues args = new ContentValues();
        args.put(KATA, bahasaModel.getKata());
        args.put(DEFINISI, bahasaModel.getDefinisi());
        return database.update(TABLE_NAME, args, _ID+"= '"+bahasaModel.getId()+
        "'", null);
    }

    public int delete(int id) {
        return database.delete(TABLE_NAME, _ID+" = '"+id+"'", null);
    }
}
