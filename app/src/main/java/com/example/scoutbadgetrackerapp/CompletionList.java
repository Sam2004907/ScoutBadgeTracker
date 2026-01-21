package com.example.scoutbadgetrackerapp;

public class CompletionList {
    int _id, _user_id, _badge_id;
    float _percentage;

    public CompletionList(){ }

    public CompletionList(float percentage, int user_id, int badge_id){
        this._percentage = percentage;
        this._user_id = user_id;
        this._badge_id = badge_id;
    }

    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public float getPercentage(){
        return this._percentage;
    }

    public void setPercentage(float percentage){
        this._percentage = percentage;
    }

    public int getUser_ID(){
        return this._user_id;
    }

    public void setUser_ID(int user_id){
        this._user_id = user_id;
    }

    public int getBadge_ID(){
        return this._badge_id;
    }

    public void setBadge_ID(int badge_id){
        this._badge_id = badge_id;
    }

}

