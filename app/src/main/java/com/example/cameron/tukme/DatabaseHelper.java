package com.example.cameron.tukme;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Cameron on 2/27/2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "TUKTUKS.db";
    public static final String TABLE_NAME_PASSENGER = "USER";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "USERNAME";
    public static final String COL_5 = "PASSWORD";
    public static final String COL_6 = "USER_TYPE";
    public static final String COL_7 = "RATING";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME_PASSENGER + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT, SURNAME TEXT, USERNAME TEXT, PASSWORD TEXT, USER_TYPE INTEGER, RATING REAL) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" +TABLE_NAME_PASSENGER);


        onCreate(db);
    }


    public boolean insertData(String name, String surname, String username, String password, int userType, float rating)
    {
        SQLiteDatabase db  = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, surname);
        contentValues.put(COL_4, username);
        contentValues.put(COL_5, password);
        contentValues.put(COL_6, userType);
        contentValues.put(COL_7, rating);


        long result = db.insert(TABLE_NAME_PASSENGER, null, contentValues);
        if(result == -1)
        {
            return false;
        }else
        {
            return true;
        }
    }

    public Cursor getDrivers()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {COL_1, COL_2,COL_3,COL_4,COL_5,COL_6,COL_7};
        Cursor result = db.query(TABLE_NAME_PASSENGER, columns, "USER_TYPE=?", new String[]{"1"}, null, null, null);
        return result;
    }

    public Cursor getData(String username, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {COL_1, COL_2,COL_3,COL_4,COL_5,COL_6,COL_7};
        Cursor result = db.query(TABLE_NAME_PASSENGER, columns, "USERNAME=? and PASSWORD=?", new String[]{username, password}, null, null, null);
        return result;
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME_PASSENGER,null);
        return result;
    }

    public boolean updatePassword(int id, String name, String surname, String username, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1, id);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, surname);
        contentValues.put(COL_4,username);
        contentValues.put(COL_5, password);
        db.update(TABLE_NAME_PASSENGER, contentValues, "ID = ?", new String[] { Integer.toString(id) });

        return true;
    }

    public int deleteData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_PASSENGER, "ID = ?", new String[] {id});
    }

}
