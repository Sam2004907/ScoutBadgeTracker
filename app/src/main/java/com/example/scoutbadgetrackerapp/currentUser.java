package com.example.scoutbadgetrackerapp;

public class currentUser {
    static String _username, _role, _name;
    static int _userID;

    currentUser(String username, String name, String role, int userID){
        _username = username;
        _name = name;
        _role = role;
        _userID = userID;
    }

    public static int getUserID(){
        return _userID;
    }
    public static String getUsername(){
        return _username;
    }
    public static String getUserRole(){
        return _role;
    }

}
