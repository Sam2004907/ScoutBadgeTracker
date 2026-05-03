package com.example.scoutbadgetrackerapp;

public class EventList {
    //used to add event details to database.
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

    public String getEventName(){ return this._eventName; }

    public String getStartDateTime(){
        return this._startDateTime;
    }

    public String getEndDateTime(){
        return this._endDateTime;
    }

    public void setEndDateTime(String endDateTime){ this._endDateTime = endDateTime; }

    public String getLocation(){
        return this._location;
    }

    public int getGroupID(){
        return this._group_id;
    }

}

