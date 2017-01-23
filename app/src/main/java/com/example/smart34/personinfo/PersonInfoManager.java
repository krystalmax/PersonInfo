package com.example.smart34.personinfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by smart34 on 2016-10-11.
 */
public class PersonInfoManager extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "personDB";
    private static final String TABLE_NAME = "personTBL";
    private static PersonInfoManager infoManager = null;

    private PersonInfoManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public static  PersonInfoManager getInfoManager(Context context) {
        if(infoManager == null) {
            infoManager = new PersonInfoManager(context);
        }
        return  infoManager;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (Name CHAR(20) PRIMARY KEY, Phone CHAR(11), " +
                "Email CHAR(40), Address CHAR(100), gName CHAR(20));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insert(ContentValues values) {
        getWritableDatabase().insert(TABLE_NAME, null, values);
        return true;
    }

    public boolean delete(String name) {
        getWritableDatabase().execSQL("DELETE FROM " + TABLE_NAME + " WHERE Name='" + name + "';");
        return true;
    }

    public boolean update(ContentValues values) {
        String[] args = { values.getAsString("Name")};
        getWritableDatabase().update(TABLE_NAME, values, "Name=?", args);
        return  true;
    }

    public Cursor getAllData() {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME +";", null);
//        Log.i("TEST", "getAllData");
//        if(cursor.moveToNext()) {
//            String msg = cursor.getString(0) + cursor.getString(1);
//            Log.i("TEST", msg);
//        }
        return cursor;
    }

    public Cursor getData(String name) {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE Name='" + name + "';", null);
        return cursor;
    }
}
