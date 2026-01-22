package com.example.scoutbadgetrackerapp;

public class EvidenceList {
    int _id, _user_id, _badge_id, _requirement_id;
    String _type, _evidencePath;

    public EvidenceList(){ }

    public EvidenceList(String type, String evidencePath, int user_id, int badge_id, int requirement_id){
        this._type = type;
        this._evidencePath = evidencePath;
        this._user_id = user_id;
        this._badge_id = badge_id;
        this._requirement_id = requirement_id;
    }

    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getType(){ return this._type; }

    public void setType(String type){
        this._type = type;
    }

    public String getEvidencePath(){
        return this._evidencePath;
    }

    public void setEvidencePath(String evidencePath){
        this._evidencePath = evidencePath;
    }

    public int getUserID(){
        return this._user_id;
    }

    public void setUserID(int user_id){
        this._user_id = user_id;
    }

    public int getBadgeID(){ return this._badge_id; }

    public void setBadgeID(int badge_id){
        this._badge_id = badge_id;
    }

    public int getRequirementID(){
        return this._requirement_id;
    }

    public void setRequirementID(int requirement_id){
        this._requirement_id = requirement_id;
    }

}

