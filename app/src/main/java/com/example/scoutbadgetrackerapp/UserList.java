package com.example.scoutbadgetrackerapp;

import java.util.Date;

public class UserList {
    //used to add users to database.
    int _id, _scoutgroup;
    String _name, _username, _password, _DOB, _email, _phonenumber, _role;
    public UserList(){ }

    public UserList(String username, String password, String name, String DOB, String email, String phonenumber, String role, int scoutgroup){
        this._username = username;
        this._password = password;
        this._name = name;
        this._DOB = DOB;
        this._email = email;
        this._phonenumber = phonenumber;
        this._role = role;
        this._scoutgroup = scoutgroup;
    }

    public String getUsername(){
        return this._username;
    }
    public String getPassword(){
        return this._password;
    }
    public String getName(){
        return this._name;
    }
    public String getDOB(){
        return this._DOB;
    }
    public String getEmail(){
        return this._email;
    }
    public String getPhoneNumber(){
        return this._phonenumber;
    }
    public String getRole(){
        return this._role;
    }
    public int getScoutGroup(){
        return this._scoutgroup;
    }
    public void setName(String name){ this._name = name; }

}