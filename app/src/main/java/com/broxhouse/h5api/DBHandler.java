package com.broxhouse.h5api;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.util.Log;

import com.broxhouse.h5api.models.metadata.Map;
import com.broxhouse.h5api.models.metadata.Medal;
import com.broxhouse.h5api.models.metadata.Weapon;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Halo5RandStats.db";
    public static final String TABLE_WEAPONS = "weapons";
    public static final String COLUMN_WEAPON_ID = "_id";
    public static final String COLUMN_WEAPON_NAME = "weaponname";
    public static final String COLUMN_WEAPON_IMG = "weaponimg";

    public static final String TABLE_MAPS = "maps";
    public static final String COLUMN_MAP_ID = "_id";
    public static final String COLUMN_MAP_NAME = "mapname";
    public static final String COLUMN_MAP_IMG = "mapimg";

    public static final String TABLE_MEDALS = "medals";
    public static final String COLUMN_MEDAL_ID = "_id";
    public static final String COLUMN_MEDAL_NAME = "medalname";
    public static final String COLUMN_MEDAL_IMG = "medalimg";


    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query_weapons = "CREATE TABLE " + TABLE_WEAPONS + "("
                + COLUMN_WEAPON_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_WEAPON_NAME + " TEXT " +
                COLUMN_WEAPON_IMG + " TEXT " +
                ");";
        String query_maps = "CREATE TABLE " + TABLE_MAPS + "("
                + COLUMN_MAP_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_MAP_NAME + " TEXT " +
                COLUMN_MAP_IMG + " TEXT " +
                ");";
        String query_medals = "CREATE TABLE " + TABLE_MEDALS + "("
                + COLUMN_MEDAL_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_MEDAL_NAME + " TEXT " +
                COLUMN_MEDAL_IMG + " TEXT " +
                ");";
        db.execSQL(query_weapons);
        db.execSQL(query_maps);
        db.execSQL(query_medals);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_WEAPONS);
        onCreate(db);
    }

    //Add new row to the database
    public void addWeapon(Weapon weapon){
        ContentValues values = new ContentValues();
        values.put(COLUMN_WEAPON_NAME, weapon.getName());
        values.put(COLUMN_WEAPON_ID, weapon.getId());
        values.put(COLUMN_WEAPON_IMG, weapon.getSmallIconImageUrl());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_WEAPONS, null, values);
        db.close();
    }

    public void addMap(Map map){
        ContentValues values = new ContentValues();
        values.put(COLUMN_MAP_NAME, map.getName());
        values.put(COLUMN_MAP_ID, map.getId());
        values.put(COLUMN_MAP_IMG, map.getImageUrl());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_MAPS, null, values);
        db.close();
    }

    public void addMedal(Medal medal){
        Log.i("database", "adding Medal" + medal.getName());
        ContentValues values = new ContentValues();
        values.put(COLUMN_MEDAL_NAME, medal.getName());
        values.put(COLUMN_MEDAL_ID, medal.getId());
        values.put(COLUMN_MEDAL_IMG, medal.getSpriteLocation().getSpriteSheetUri());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_MAPS, null, values);
        db.close();
    }

    public String getMedalDetails(){
        Log.i("database", "getting DB details");
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_MEDALS;

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to first row in your results
        c.moveToFirst();

        while (!c.isAfterLast()){
            if(c.getString(c.getColumnIndex(COLUMN_MEDAL_NAME)) != null){
                Log.i("database", dbString + "iterating through db loop");
                dbString += c.getString(c.getColumnIndex(COLUMN_MEDAL_NAME));
                dbString += "\n";
            }
        }
        db.close();
        return dbString;
    }


}
