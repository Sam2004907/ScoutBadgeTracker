package com.example.scoutbadgetrackerapp;

public class GroupList {
    int _id;
    String _groupName, _district, _county;

    public GroupList(){ }

    public GroupList(String groupName, String district, String county){
        this._groupName = groupName;
        this._district = district;
        this._county = county;
    }

    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getGroupName(){
        return this._groupName;
    }

    public void setGroupName(String groupName){
        this._groupName = groupName;
    }

    public String getDistrict(){
        return this._district;
    }

    public void setDistrict(String district){
        this._district = district;
    }

    public String getCounty(){
        return this._county;
    }

    public void setCounty(String county){
        this._county = county;
    }

}

