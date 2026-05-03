package com.example.scoutbadgetrackerapp;

public class RequirementsList {
    //used for adding requirements to database
    int _id, _badge_id, _numOfEvidence;
    String _details;


    public RequirementsList(String details, int numOfEvidence, int badge_id){
        this._details = details;
        this._numOfEvidence = numOfEvidence;
        this._badge_id = badge_id;
    }

    public String getDetails(){
        return this._details;
    }

    public void setDetails(String details){
        this._details = details;
    }

    public int getnumOfEvidence(){
        return this._numOfEvidence;
    }

    public int getBadgeID(){
        return this._badge_id;
    }

}

