package com.example.scoutbadgetrackerapp;

public class BadgeList {
    int _id;
    String _name, _type, _req, _icon;

    public BadgeList(){ }

    public BadgeList(String name, String type, String req, String icon){
        this._name = name;
        this._type = type;
        this._req = req;
        this._icon = icon;
    }

    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getName(){
        return this._name;
    }

    public void setName(String name){
        this._name = name;
    }
    public String getType(){
        return this._type;
    }

    public void setType(String type){
        this._type = type;
    }

    public String getReq(){
        return this._req;
    }
    public void setReq(String req){
        this._req = req;
    }

    public String getIcon(){
        return this._icon;
    }

    public void setIcon(String icon){
        this._icon = icon;
    }

}
