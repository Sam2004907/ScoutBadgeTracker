package com.example.scoutbadgetrackerapp;

public class EventList {
    int _id, _group_id;
    String _eventName, _startDateTime, _endDateTime, _location;

    public EventList(){ }

    public EventList(String eventName, String startDateTime, String endDateTime, String location, int group_id){
        this._eventName = eventName;
        this._startDateTime = startDateTime;
        this._endDateTime = endDateTime;
        this._location = location;
        this._group_id = group_id;
    }

    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getEventName(){ return this._eventName; }

    public void setEventName(String eventName){
        this._eventName = eventName;
    }

    public String getStartDateTime(){
        return this._startDateTime;
    }

    public void setStartDateTime(String startDateTime){
        this._startDateTime = startDateTime;
    }

    public String getEndDateTime(){
        return this._endDateTime;
    }

    public void setEndDateTime(String endDateTime){ this._endDateTime = endDateTime; }

    public String getLocation(){
        return this._location;
    }

    public void setLocation(String location){
        this._location = location;
    }

    public int getGroupID(){
        return this._group_id;
    }

    public void setGroupID(int group_id){
        this._group_id = group_id;
    }

}

