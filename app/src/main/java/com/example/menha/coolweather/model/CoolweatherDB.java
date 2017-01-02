package com.example.menha.coolweather.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.menha.coolweather.db.CoolweatherDBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by menha on 2017/1/2.
 */

public class CoolweatherDB {
    private static final String DB_NAME = "cool_weather";
    private static final int DB_VERSION = 1;

    private SQLiteDatabase db;
    private static CoolweatherDB coolweatherDB = null;

    private CoolweatherDB(Context context){
        CoolweatherDBHelper dbHelper = new CoolweatherDBHelper(context, DB_NAME, null, DB_VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public synchronized CoolweatherDB getInstance(Context context){
        if(null == coolweatherDB)
            coolweatherDB = new CoolweatherDB(context);
        return coolweatherDB;
    }

    public void SaveProvince(Province province){
        db.execSQL("insert into " +
                "province " +
                "(code, name)" +
                " values " +
                "( " + province.getCode() +", " + province.getName() + " )");
    }
    public void SaveCity(City city){
        db.execSQL("insert into " +
                "city " +
                "(code, name, province_id)" +
                " values " +
                "( " + city.getCode() + ", " + city.getName() + "," + city.getProvinceId() + " )");
    }
    public void SaveCounty(County county){
        db.execSQL("insert into " +
                "county " +
                "(code, name, city_id)" +
                " values " +
                "( " + county.getCode() + ", " + county.getName() + "," + county.getCityId() + " )");
    }

    public List<Province> LoadProvinces(){
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.rawQuery("select * from province",null);
        while (cursor.moveToNext()){
            Province province = new Province();
            province.setId(cursor.getInt(cursor.getColumnIndex("id")));
            province.setCode(cursor.getString(cursor.getColumnIndex("code")));
            province.setName(cursor.getString(cursor.getColumnIndex("name")));
            list.add(province);
        }
        return list;
    }

    public List<City> LoadCities() {
        List<City> list = new ArrayList<City>();
        Cursor cursor = db.rawQuery("select * from city",null);
        while (cursor.moveToNext()){
            City city = new City();
            city.setId(cursor.getInt(cursor.getColumnIndex("id")));
            city.setCode(cursor.getString(cursor.getColumnIndex("code")));
            city.setName(cursor.getString(cursor.getColumnIndex("name")));
            city.setProvinceId(cursor.getInt(cursor.getColumnIndex("province_id")));
            list.add(city);
        }
        return list;
    }

    public List<County> LoadCounties() {
        List<County> list = new ArrayList<County>();
        Cursor cursor = db.rawQuery("select * from county",null);
        while (cursor.moveToNext()){
            County county = new County();
            county.setId(cursor.getInt(cursor.getColumnIndex("id")));
            county.setCode(cursor.getString(cursor.getColumnIndex("code")));
            county.setName(cursor.getString(cursor.getColumnIndex("name")));
            county.setCityId(cursor.getInt(cursor.getColumnIndex("city_id")));
            list.add(county);
        }
        return list;
    }
}
