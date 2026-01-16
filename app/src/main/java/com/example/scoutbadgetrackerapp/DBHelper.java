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
    private static final String TABLE_COMPLETION = "completion";
    private static final String TABLE_EVIDENCE = "evidence";
    private static final String TABLE_USERS = "users";
    private static final String TABLE_REQUIREMENTS = "requirements";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Badge Table
        String createBadgeTable = "CREATE TABLE " + TABLE_BADGES+ "("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT, "
                + "type TEXT, "
                + "Requirements TEXT, "
                + "Icon Text)";
        db.execSQL(createBadgeTable);
        // User Table
        String createUsersTable = "CREATE TABLE " + TABLE_USERS+ "("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "username TEXT, "
                + "password TEXT, "
                + "name TEXT, "
                + "DOB TEXT, "
                + "email TEXT, "
                + "phone_number TEXT, "
                + "role TEXT, "
                + "scout_group TEXT)";
        db.execSQL(createUsersTable);

        //Evidence Table
        String createEvidenceTable = "CREATE TABLE " + TABLE_EVIDENCE + "("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "type Text, "
                + "requirement_id TEXT, "
                + "evidence TEXT, "
                + "user_id INTEGER, "
                + "badge_id INTEGER, "
                + "FOREIGN KEY (user_id) REFERENCES " + TABLE_USERS + "(id), "
                + "FOREIGN KEY (badge_id) REFERENCES " + TABLE_BADGES + "(id))";
        db.execSQL(createEvidenceTable);
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
        values.put("type", badge.getType());
        values.put("Requirements", badge.getReq());
        values.put("Icon", badge.getIcon());

        db.insert(TABLE_BADGES, null, values);

        db.close(); // Closing database connection
    }

    //Update Badge
    void updateBadge(BadgeList badge) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", badge.getName());
        values.put("type", badge.getType());
        values.put("Requirements", badge.getReq());
        values.put("Icon", badge.getIcon());

        db.update(TABLE_BADGES, values, "name=?", new String[]{badge.getName()});

        db.close(); // Closing database connection
    }

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
                results.get(index).add(cursor.getString(0));//ID
                results.get(index).add(cursor.getString(1));//Name
                results.get(index).add(cursor.getString(2));//Type
                results.get(index).add(cursor.getString(3));//Reqs
                results.get(index).add(cursor.getString(4));//Icon
                index+=1;
            } while (cursor.moveToNext());
        }

        // return student list
        return results;
    }

    public String[] getBadge(String badgeName) {
        String[] results = new String[5];
        int index = 0;

        // Select All Query
        String whereQuery = "SELECT * FROM " + TABLE_BADGES + " WHERE name = ? ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(whereQuery, new String[] {badgeName});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                results[0] = cursor.getString(0); //ID
                results[1] = cursor.getString(1); //Name
                results[2] = cursor.getString(2); //Type
                results[3] = cursor.getString(3); //Reqs
                results[4] = cursor.getString(4); //Icon
                index+=1;
            } while (cursor.moveToNext());
        }

        // return student list
        return results;
    }

    //Add User
    void addUser(UserList user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        values.put("name", user.getName());
        values.put("DOB", user.getDOB());
        values.put("email", user.getEmail());
        values.put("phone_number", user.getPhoneNumber());
        values.put("role", user.getRole());
        values.put("scout_group", user.getScoutGroup());

        db.insert(TABLE_USERS, null, values);

        db.close(); // Closing database connection
    }
    //Get Users
    public ArrayList<ArrayList<Object>> getAllUsers() {

        ArrayList<ArrayList<Object>> results = new ArrayList<ArrayList<Object>>();
        int index = 0;
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                results.add(new ArrayList<Object>());
                results.get(index).add(cursor.getString(0));//ID
                results.get(index).add(cursor.getString(1));//Username
                results.get(index).add(cursor.getString(2));//Password
                results.get(index).add(cursor.getString(3));//Name
                results.get(index).add(cursor.getString(4));//DOB
                results.get(index).add(cursor.getString(5));//Email
                results.get(index).add(cursor.getString(6));//Phone Number
                results.get(index).add(cursor.getString(7));//Role
                results.get(index).add(cursor.getString(8));//Group
                index+=1;
            } while (cursor.moveToNext());
        }

        // return student list
        return results;
    }
}
