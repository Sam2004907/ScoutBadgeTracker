package com.example.scoutbadgetrackerapp;

public class EvidenceList {
    //used to add evidence to databse
    int _id, _user_id, _badge_id, _requirement_id;
    String _type, _evidencePath, _approval;


    public EvidenceList(String type, String evidencePath, int user_id, int badge_id, int requirement_id, String approval){
        this._type = type;
        this._evidencePath = evidencePath;
        this._user_id = user_id;
        this._badge_id = badge_id;
        this._requirement_id = requirement_id;
        this._approval = approval;
    }

    public String getType(){ return this._type; }

    public String getEvidencePath(){
        return this._evidencePath;
    }

    public int getUserID(){
        return this._user_id;
    }

    public int getBadgeID(){ return this._badge_id; }

    public int getRequirementID(){
        return this._requirement_id;
    }

    public String getApproval(){
        return this._approval;
    }

}

