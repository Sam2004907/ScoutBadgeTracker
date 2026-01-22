package com.example.scoutbadgetrackerapp;

public class currentUser {
    String _name, _role;
    static int _userID;

    currentUser(String name, String role, int userID){
        _name = name;
        _role = role;
        _userID = userID;
    }

    public static int getUserID(){
        return _userID;
    }

}
