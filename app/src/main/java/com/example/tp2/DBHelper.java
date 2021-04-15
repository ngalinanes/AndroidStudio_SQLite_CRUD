package com.example.tp2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Cities.db";

    public DBHelper (Context context){
        super(context, "Cities.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table cities(city TEXT primary key, country TEXT, people TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists cities");
    }

    public Boolean insertData(String city, String country, String people){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("city", city);
        contentValues.put("country", country);
        contentValues.put("people", people);
        long result = MyDB.insert("cities", null, contentValues);
        if (result==-1) return false;
        else {
            return true;
        }
    }

    public Boolean checkcity(String city){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from cities where city = ?", new String[] {city});
        if (cursor.getCount() > 0) return true;
        else {
            return false;
        }
    }

    public void getcity(EditText citysearch, EditText country, EditText people){
        String city = citysearch.getText().toString();
        Cursor res = this.getReadableDatabase().rawQuery("select country,people from cities where city= ?", new String[] {city});
        citysearch.setText("");
        while (res.moveToNext()){
            country.append(res.getString(0));
            people.append(res.getString(1));
            citysearch.append(city);
        }
    }

    public void deletecity(EditText citysearch, EditText country, EditText people){
        String city = citysearch.getText().toString();
        Cursor res = this.getWritableDatabase().rawQuery("delete from cities where city=?", new String[] {city});
        country.setText("");
        people.setText("");
        citysearch.setText("");
        while (res.moveToNext()){
            country.setText("");
            people.setText("");
            citysearch.setText("");
        }
    }
}
