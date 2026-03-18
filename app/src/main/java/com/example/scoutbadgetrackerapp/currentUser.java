package com.example.scoutbadgetrackerapp;

public class currentUser {
    static String _username, _role, _name, _approval;
    static int _userID;

    currentUser(String username, String name, String role, String approval, int userID){
        _username = username;
        _name = name;
        _role = role;
        _approval = approval;
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
    public static String getUserApproval(){
        return _approval;
    }

}
