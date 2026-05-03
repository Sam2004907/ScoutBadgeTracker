package com.example.scoutbadgetrackerapp;

public class BadgeList {
    // this class standardises the data being passed to a database
    String _name, _type, _icon;

    public BadgeList(String name, String type, String icon){
        this._name = name;
        this._type = type;
        this._icon = icon;
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


    public String getIcon(){
        return this._icon;
    }

}
