package com.example.scoutbadgetrackerapp;

import java.util.Date;

public class UserList {
    int _id;
    String _name, _username, _password, _DOB, _email, _phonenumber, _role, _scoutgroup;
    public UserList(){ }

    public UserList(String username, String password, String name, String DOB, String email, String phonenumber, String role, String scoutgroup){
        this._username = username;
        this._password = password;
        this._name = name;
        this._DOB = DOB;
        this._email = email;
        this._phonenumber = phonenumber;
        this._role = role;
        this._scoutgroup = scoutgroup;
    }

    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
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
    public String getScoutGroup(){
        return this._scoutgroup;
    }

    public void setUsername(String username){ this._username = username; }
    public void setPassword(String password){ this._password = password; }
    public void setName(String name){ this._name = name; }
    public void setDOB(String DOB){ this._DOB = DOB; }
    public void setEmail(String email){ this._email = email; }
    public void setPhoneNumber(String phonenumber){ this._phonenumber = phonenumber; }
    public void setRole(String role){ this._role = role; }
    public void setScoutGroup(String scoutgroup){ this._scoutgroup = scoutgroup; }

}