package com.example.scoutbadgetrackerapp;

public class BadgeList {
    int _id;
    String _name, _type, _icon;

    public BadgeList(){ }

    public BadgeList(String name, String type, String icon){
        this._name = name;
        this._type = type;
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

    public String getIcon(){
        return this._icon;
    }

    public void setIcon(String icon){
        this._icon = icon;
    }

}
