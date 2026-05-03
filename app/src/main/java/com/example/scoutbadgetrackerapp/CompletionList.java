package com.example.scoutbadgetrackerapp;

public class CompletionList {
//    used to add completion details to the database
    int _id, _user_id, _badge_id;
    float _percentage;


    public CompletionList(float percentage, int user_id, int badge_id){
        this._percentage = percentage;
        this._user_id = user_id;
        this._badge_id = badge_id;
    }

    public float getPercentage(){
        return this._percentage;
    }

    public int getUser_ID(){
        return this._user_id;
    }

    public int getBadge_ID(){
        return this._badge_id;
    }


}

