package com.example.menha.coolweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by menha on 2017/1/2.
 */

public class CoolweatherDBHelper extends SQLiteOpenHelper {

    private static final String createTableProvince = "create table province (" +
            "id integer primary key autoincrement, " +
            "name text, " +
            "code text)";
    private static final String createTableCity = "create table city (" +
            "id integer primary key autoincrement, " +
            "name text, " +
            "code text, " +
            "province_id integer)";
    private static final String createTableCounty = "create table county (" +
            "id integer primary key autoincrement, " +
            "name text, " +
            "code text, " +
            "city_id integer)";

    private Context mContext;

    public CoolweatherDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(createTableProvince);
            db.execSQL(createTableCity);
            db.execSQL(createTableCounty);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
