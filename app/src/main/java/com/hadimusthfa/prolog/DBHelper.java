package com.hadimusthfa.prolog;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.FileInputStream;


class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Alpha";
    public static final String TABLE_NAME = "User";
    public static final String USER_NAME = "Username";
    public static final String EMAIL = "Email";
    public static final String MOBILE = "Mobile";
    public static final String DOB = "Dob";
    public static final String PASSWORD = "Password";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE User(Username TEXT PRIMARY KEY, Email TEXT, Mobile TEXT, Dob TEXT, Password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addUser(String username, String email, String mobile, String dob, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, username);
        contentValues.put(EMAIL, email);
        contentValues.put(MOBILE, mobile);
        contentValues.put(DOB, dob);
        contentValues.put(PASSWORD, password);

        long res = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return res;
    }

    public boolean checkUser(String username, String password) {
        String[] colmns = {USER_NAME};
        SQLiteDatabase db = getReadableDatabase();
        String select = USER_NAME + "=?" + " and " + PASSWORD + "=?";
        String[] selectArgs = {username, password};
        Cursor cursor = db.query(TABLE_NAME, colmns, select, selectArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();

        if (count > 0)
            return true;
        else
            return false;
    }

    public String[] readUser(Context c, String index) {
        String[] colmns = {USER_NAME, EMAIL, MOBILE, DOB};
        SQLiteDatabase db = getReadableDatabase();
        String select = USER_NAME + "=?";
        String[] selectArgs = {index};
        Cursor cursor = db.query(TABLE_NAME, colmns
                , select
                , selectArgs, null, null, null);

        String[] s = new String[6];
        int count = cursor.getCount();
        while (cursor.moveToNext()) {
            int i;

            i = cursor.getColumnIndexOrThrow(USER_NAME);
            s[i] = cursor.getString(i);

            i = cursor.getColumnIndexOrThrow(EMAIL);
            s[i] = cursor.getString(i);

            i = cursor.getColumnIndexOrThrow(MOBILE);
            s[i] = cursor.getString(i);

            i = cursor.getColumnIndexOrThrow(DOB);
            s[i] = cursor.getString(i);

        }

        cursor.close();

        if (count > 0)
            return s;
        else
            return null;
    }

}

