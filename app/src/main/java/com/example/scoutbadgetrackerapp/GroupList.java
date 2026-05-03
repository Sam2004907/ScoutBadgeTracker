package com.example.scoutbadgetrackerapp;

public class GroupList {
    //used to add group data to database.
    int _id;
    String _groupName, _district, _county;

    public GroupList(String groupName, String district, String county){
        this._groupName = groupName;
        this._district = district;
        this._county = county;
    }

    public String getGroupName(){
        return this._groupName;
    }

    public String getDistrict(){
        return this._district;
    }

    public String getCounty(){
        return this._county;
    }

}

