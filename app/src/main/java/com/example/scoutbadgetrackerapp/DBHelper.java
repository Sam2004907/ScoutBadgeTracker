package com.example.scoutbadgetrackerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ScoutBadgeTracker";
    private static final String TABLE_BADGES = "badges";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createBadgeTable = "CREATE TABLE " + TABLE_BADGES+ "("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT, "
                + "Requirements TEXT, "
                + "Icon Text)";
        db.execSQL(createBadgeTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BADGES);
        onCreate(db);
    }

    //Add Badge
    void addBadge(BadgeList badge) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", badge.getName());
        values.put("Requirements", badge.getReq());
        values.put("Icon", badge.getIcon());

        db.insert(TABLE_BADGES, null, values);

        db.close(); // Closing database connection
    }

    //Update Badge

    //Delete Badge

    //Get Badges
    public ArrayList<ArrayList<String>> getAllBadges() {

        ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
        int index = 0;
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_BADGES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                results.add(new ArrayList<String>());
                results.get(index).add(cursor.getString(0));
                results.get(index).add(cursor.getString(1));
                results.get(index).add(cursor.getString(2));
                results.get(index).add(cursor.getString(3));
                index+=1;
            } while (cursor.moveToNext());
        }

        // return student list
        return results;
    }

    public String[] getBadge(String badgeName) {
        String[] results = new String[4];
        int index = 0;

        // Select All Query
        String whereQuery = "SELECT * FROM " + TABLE_BADGES + " WHERE name = ? ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(whereQuery, new String[] {badgeName});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                results[0] = cursor.getString(0);
                results[1] = cursor.getString(1);
                results[2] = cursor.getString(2);
                results[3] = cursor.getString(3);
                index+=1;
            } while (cursor.moveToNext());
        }

        // return student list
        return results;
    }
}
