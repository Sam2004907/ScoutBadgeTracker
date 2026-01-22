package com.example.scoutbadgetrackerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
    private static final String TABLE_GROUPS = "groups";

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
                + "scout_group_id INTEGER, "
                + "FOREIGN KEY (scout_group_id) REFERENCES " + TABLE_GROUPS + "(id))";
        db.execSQL(createUsersTable);

        //Evidence Table
        String createEvidenceTable = "CREATE TABLE " + TABLE_EVIDENCE + "("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "type Text, "
                + "evidencePath TEXT, "
                + "approved BOOLEAN, "
                + "user_id INTEGER, "
                + "badge_id INTEGER, "
                + "requirement_id INTEGER, "
                + "FOREIGN KEY (user_id) REFERENCES " + TABLE_USERS + "(id), "
                + "FOREIGN KEY (badge_id) REFERENCES " + TABLE_BADGES + "(id), "
                + "FOREIGN KEY (requirement_id) REFERENCES " + TABLE_REQUIREMENTS + "(id))";
        db.execSQL(createEvidenceTable);

        //Requirements Table
        String createRequirementsTable = "CREATE TABLE " + TABLE_REQUIREMENTS + "("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "details TEXT, "
                + "numOfEvidence INTEGER, "
                + "badge_id INTEGER, "
                + "FOREIGN KEY (badge_id) REFERENCES " + TABLE_BADGES + "(id))";
        db.execSQL(createRequirementsTable);

        //Groups Table
        String createGroupsTable = "CREATE TABLE " + TABLE_GROUPS + "("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "groupName TEXT, "
                + "district TEXT, "
                + "county TEXT)";
        db.execSQL(createGroupsTable);

        //Completion Table
        String createCompletionTable = "CREATE TABLE " + TABLE_COMPLETION + "("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "percentage FLOAT,"
                + "user_id INTEGER, "
                + "badge_id INTEGER, "
                + "FOREIGN KEY (user_id) REFERENCES " + TABLE_USERS + "(id), "
                + "FOREIGN KEY (badge_id) REFERENCES " + TABLE_BADGES + "(id))";
        db.execSQL(createCompletionTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BADGES);
        onCreate(db);
    }

    //Add Badge
    void addBadge(BadgeList badge) {
        Log.d("DB run", "addBadge ran");
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", badge.getName());
        values.put("type", badge.getType());
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
        values.put("Icon", badge.getIcon());

        db.update(TABLE_BADGES, values, "name=?", new String[]{badge.getName()});

        db.close(); // Closing database connection
    }

    //Delete Badge

    //Get Badges
    public ArrayList<ArrayList<String>> getAllBadges() {
        Log.d("DB run", "getAllBadges ran");

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
                results.get(index).add(cursor.getString(3));//Icon
                index+=1;
            } while (cursor.moveToNext());
        }

        // return Badges list
        return results;
    }
    //Get Badge
    public String[] getBadge(String badgeName) {
        String[] results = new String[4];

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
                results[3] = cursor.getString(3); //Icon
            } while (cursor.moveToNext());
        }

        // return Badge list
        return results;
    }

    //Add User
    void addUser(UserList user) {
        Log.d("DB run", "addUser ran");
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        values.put("name", user.getName());
        values.put("DOB", user.getDOB());
        values.put("email", user.getEmail());
        values.put("phone_number", user.getPhoneNumber());
        values.put("role", user.getRole());
        values.put("scout_group_id", user.getScoutGroup());

        db.insert(TABLE_USERS, null, values);

        db.close(); // Closing database connection
    }
    //Get Users
    public ArrayList<ArrayList<Object>> getAllUsers() {
        Log.d("DB run", "getAllUsers ran");

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

        // return Users list
        return results;
    }

    //Add Requirements
    void addRequirement(RequirementsList requirement) {
        Log.d("DB run", "addRequirement ran");
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("details", requirement.getDetails());
        values.put("numOfEvidence", requirement.getnumOfEvidence());
        values.put("badge_id", requirement.getBadgeID());

        db.insert(TABLE_REQUIREMENTS, null, values);

        db.close(); // Closing database connection
    }

    //Get Requirements
    public ArrayList<ArrayList<String>> getAllReqs() {

        ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
        int index = 0;
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_REQUIREMENTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                results.add(new ArrayList<String>());
                results.get(index).add(cursor.getString(0));//ID
                results.get(index).add(cursor.getString(1));//Details
                results.get(index).add(cursor.getString(2));//numOfEvidence
                results.get(index).add(cursor.getString(3));//Badge_id
                index+=1;
            } while (cursor.moveToNext());
        }

        // return Reqs list
        return results;
    }
    public ArrayList<ArrayList<String>> getBadgeReqs(String badge_id) {

        ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
        Log.d("Badge_id", badge_id);

        // Select Badge_id Query
        String selectQuery = "SELECT * FROM " + TABLE_REQUIREMENTS + " WHERE badge_id = ? ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[] {badge_id});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            int index = 0;
            do {
                results.add(new ArrayList<String>());
                results.get(index).add(cursor.getString(0));//id
                results.get(index).add(cursor.getString(1));//Details
                results.get(index).add(cursor.getString(2));//numofEvidence
                index+=1;
            } while (cursor.moveToNext());
        }

        // return Badge Reqs list
        return results;
    }

    //Add Group
    void addGroup(GroupList group) {
        Log.d("DB run", "addGroup ran");
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("groupName", group.getGroupName());
        values.put("district", group.getDistrict());
        values.put("county", group.getCounty());

        db.insert(TABLE_GROUPS, null, values);

        db.close(); // Closing database connection
    }

    //Get Groups
    public ArrayList<ArrayList<String>> getAllGroups() {

        ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
        int index = 0;
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_GROUPS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                results.add(new ArrayList<String>());
                results.get(index).add(cursor.getString(0));//ID
                results.get(index).add(cursor.getString(1));//groupName
                results.get(index).add(cursor.getString(2));//district
                results.get(index).add(cursor.getString(3));//county
                index+=1;
            } while (cursor.moveToNext());
        }

        // return Group list
        return results;
    }

    public Object[] getGroup(String groupName) {

        Object[] results = new Object[4];
        Log.d("groupName", groupName);

        // Select Badge_id Query
        String selectQuery = "SELECT * FROM " + TABLE_GROUPS + " WHERE groupName = ? ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[] {groupName});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                results[0] = cursor.getString(0); //ID
                results[1] = cursor.getString(1); //groupName
                results[2] = cursor.getString(2); //district
                results[3] = cursor.getString(3); //county
            } while (cursor.moveToNext());
        }
        // return Group
        return results;
    }

    //Add Completion
    void addCompletion(CompletionList completion) {
        Log.d("DB run", "addCompletion ran");
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("percentage", completion.getPercentage());
        values.put("user_id", completion.getUser_ID());
        values.put("badge_id", completion.getBadge_ID());

        db.insert(TABLE_COMPLETION, null, values);

        db.close(); // Closing database connection
    }

    //Add Evidence
    void addEvidence(EvidenceList evidence) {
        Log.d("DB run", "addEvidence ran");
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("type", evidence.getType());
        values.put("evidencePath", evidence.getEvidencePath());
        values.put("approved", false);
        values.put("user_id", evidence.getUserID());
        values.put("badge_id", evidence.getBadgeID());
        values.put("requirement_id", evidence.getRequirementID());

        db.insert(TABLE_EVIDENCE, null, values);

        db.close(); // Closing database connection
    }
    public ArrayList<ArrayList<Object>> getUserBadgeEvidence(String userID, String badgeID) {

        ArrayList<ArrayList<Object>> results = new ArrayList<ArrayList<Object>>();
        Log.d("badgeID, userID", badgeID +", "+userID);

        // Select Badge_id Query
        String selectQuery = "SELECT * FROM " + TABLE_EVIDENCE + " WHERE user_id = ? AND badge_id = ?";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[] {userID, badgeID});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            int index = 0;
            do {
                results.add(new ArrayList<Object>());
                results.get(index).add(cursor.getString(0));//ID
                results.get(index).add(cursor.getString(1));//type
                results.get(index).add(cursor.getString(2));//EvidencePath
                results.get(index).add(cursor.getString(3));//approved
                results.get(index).add(cursor.getString(4));//user_id
                results.get(index).add(cursor.getString(5));//badge_id
                results.get(index).add(cursor.getString(6));//requirement_id
                index+=1;
            } while (cursor.moveToNext());
        }
        // return Group
        return results;
    }
}