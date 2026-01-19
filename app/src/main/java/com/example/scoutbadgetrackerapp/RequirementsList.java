package com.example.scoutbadgetrackerapp;

public class RequirementsList {
    int _id, _badge_id;
    String _details;

    public RequirementsList(){ }

    public RequirementsList(String details, int badge_id){
        this._details = details;
        this._badge_id = badge_id;
    }

    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getDetails(){
        return this._details;
    }

    public void setDetails(String details){
        this._details = details;
    }

    public int getBadgeID(){
        return this._badge_id;
    }

    public void setBadgeID(int badge_id){
        this._badge_id = badge_id;
    }

}

