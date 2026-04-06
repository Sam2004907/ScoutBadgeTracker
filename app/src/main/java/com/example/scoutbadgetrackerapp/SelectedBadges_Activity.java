package com.example.scoutbadgetrackerapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SelectedBadges_Activity extends Activity{
    ImageView imgBadge;
    TextView txtTitle, txtBadgeInfo, txtComplete;
    Button btnEvidence, btnSelectedBack, btnAward;
    ProgressBar pgbCompletion;
    LinearLayout layout;
    GridLayout lnrMembers;
    ScrollView scvAward;
    Spinner spnAward;
    Intent activity;
    String badgeID = "";
    double reqNumEvidence = 0;
    ArrayList<ArrayList<String>> requirements = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBHelper db = new DBHelper(this);

        setContentView(R.layout.activity_selectedbadge);
        layout = findViewById(R.id.layout);
        lnrMembers = findViewById(R.id.lnrMembers);

        imgBadge = findViewById(R.id.imgBadge);
        txtTitle = findViewById(R.id.txtTitle);
        txtBadgeInfo = findViewById(R.id.txtBadgeInfo);
        btnEvidence = findViewById(R.id.btnEvidence);
        pgbCompletion = findViewById(R.id.pgbCompletion);
        txtComplete = findViewById(R.id.txtComplete);

        btnAward = findViewById(R.id.btnAward);
        scvAward = findViewById(R.id.scvAward);
        spnAward = findViewById(R.id.spnAward);

        if(currentUser.getUserRole().equals("Leader")){
            layout.removeView(pgbCompletion);
            layout.removeView(txtComplete);
            layout.removeView(btnEvidence);
        }else{
            layout.removeView(btnAward);
            layout.removeView(scvAward);
            layout.removeView(spnAward);
        }
        btnSelectedBack = findViewById(R.id.btnSelectedBack);


        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String badgeName = extras.getString("key");
            //The key argument here must match that used in the other activity

            String[] badge = db.getBadgeByName(badgeName);

            badgeID = badge[0];
            String iconName = badge[3];
            imgBadge.setImageResource(
                    getResources().getIdentifier(iconName, "drawable", getPackageName())
            );
            txtTitle.setText(badgeName);
            requirements = db.getBadgeReqs(badge[0]);
            String[] badgeReqs = new String[requirements.size()+2];
            badgeReqs[0] = "Select a Requirement";
            badgeReqs[1] = "Award Badge";
            txtBadgeInfo.append("\n");
            for(int i=0; i<requirements.size(); i++){
                txtBadgeInfo.append((i+1)+". "+requirements.get(i).get(1) + ". \n");
                reqNumEvidence += Integer.parseInt(requirements.get(i).get(2));
                if(currentUser.getUserRole().equals("Leader")){
                    badgeReqs[i+2]=requirements.get(i).get(1);//badge Requirements
                }
            }
            if(currentUser.getUserRole().equals("Leader")){
                ArrayAdapter<String> adapterReqs = new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        badgeReqs
                );
                adapterReqs.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnAward.setAdapter(adapterReqs);

                spnAward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        ((TextView)parentView.getChildAt(0)).setTextColor(Color.WHITE);
                        if(position > 0){
                            lnrMembers.removeAllViews();
                            btnAward.setEnabled(true);
                            btnAward.setClickable(true);

                            Object[] userDetails = db.getUser(String.valueOf(currentUser.getUsername()));
                            Log.d("groupNum", (String) userDetails[8]);
                            ArrayList<ArrayList<Object>> approvedMembers = db.getApprovedGroupMembers((String) userDetails[8]);
                            String memberName, memberRole, memberID;
                            lnrMembers.setRowCount(approvedMembers.size());
                            lnrMembers.setColumnCount(2);

                            for(int i = 0; i < approvedMembers.size(); i++){
                                memberName = (String) approvedMembers.get(i).get(0);
                                memberRole = (String) approvedMembers.get(i).get(1);
                                memberID = (String) approvedMembers.get(i).get(4);
                                CheckBox checkBox = new CheckBox(SelectedBadges_Activity.this);
                                TextView txtID = new TextView(SelectedBadges_Activity.this);

                                if (memberRole.equals("Scout")) {
                                    String[] completion = db.getCompletion(memberID, badge[0]);
                                    boolean completed = false;
                                    if(completion[1]!=null){
                                        if(Double.parseDouble(completion[1]) >= 0.9){
                                            completed = true;
                                        }
                                    }
                                    if(position >= 2){
                                        btnAward.setText("Update");
                                        ArrayList<ArrayList<Object>> userEvidence = db.getUserBadgeReqEvidence(memberID, badge[0], requirements.get(position-2).get(0));

                                        if(userEvidence.size() > 0){
                                            int count = 0;
                                            int numEvidence = Integer.parseInt(requirements.get(position-2).get(2));
                                            for(int x =0; x < userEvidence.size(); x++){
                                                if(userEvidence.get(x).get(3).equals("approved")){//approved
                                                    count+=1;
                                                }
                                            }

                                            if(count < numEvidence || completed){
                                                Log.d("member", memberName);
                                                lnrMembers.addView(addCheckBox(checkBox, memberName, memberRole));
                                                txtID.setText(memberID);
                                                txtID.setVisibility(View.INVISIBLE);
                                                lnrMembers.addView(txtID);
                                            }
                                        }else{
                                            lnrMembers.addView(addCheckBox(checkBox, memberName, memberRole));
                                            txtID.setText(memberID);
                                            txtID.setVisibility(View.INVISIBLE);
                                            lnrMembers.addView(txtID);
                                        }
                                    }else{
                                        if(!completed){
                                            lnrMembers.addView(addCheckBox(checkBox, memberName, memberRole));
                                            txtID.setText(memberID);
                                            txtID.setVisibility(View.INVISIBLE);
                                            lnrMembers.addView(txtID);
                                            btnAward.setText("Award Badge");
                                        }
                                    }
                                }
                            }
                        }else{
                            btnAward.setEnabled(false);
                            btnAward.setClickable(false);
                            lnrMembers.removeAllViews();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

            }else{
                Log.d("reqNumEvidence", String.valueOf(reqNumEvidence));
                ArrayList<ArrayList<Object>> badgeEvidence = new ArrayList<ArrayList<Object>>();
                badgeEvidence = db.getUserBadgeEvidence(String.valueOf(currentUser.getUserID()), badgeID);
                double unapprovedCount = 0;
                for(int i=0; i<badgeEvidence.size(); i++){
                    if(badgeEvidence.get(i).get(3).equals("unapproved")){
                        unapprovedCount += 1;
                    }
                }
                Log.d("unapprovedCount", String.valueOf(unapprovedCount));
                String[] completionDetails = db.getCompletion(String.valueOf(currentUser.getUserID()),badge[0]);
                float percentage = 0;
                double secondPercentage = (unapprovedCount/reqNumEvidence)*100;
                Log.d("secondPercentage", String.valueOf(secondPercentage));
                if(completionDetails[1]!=null){
                    percentage = Float.parseFloat(completionDetails[1])*100;
                }
                //Log.d("percentage", String.valueOf(percentage));
                if(percentage > 99){
                    pgbCompletion.setVisibility(View.INVISIBLE);
                    pgbCompletion.setEnabled(false);
                    btnEvidence.setVisibility(View.INVISIBLE);
                    btnEvidence.setEnabled(false);
                    txtComplete.setText(R.string.complete);
                }else{
                    pgbCompletion.setMax(100);
                    pgbCompletion.setProgress((int) percentage);
                    pgbCompletion.setSecondaryProgress((int) (secondPercentage+percentage));
                    txtComplete.setText(((int) percentage)+"% Complete");
                }
            }


        }
        String finalBadgeID = badgeID;
        btnEvidence.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity = new Intent(SelectedBadges_Activity.this, Evidence_Activity.class);
                activity.putExtra("key", finalBadgeID);
                activity.putExtra("desc", extras.getString("key"));
                startActivity(activity);
            }
        });
        btnSelectedBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity = new Intent(SelectedBadges_Activity.this, Badges_Activity.class);
                startActivity(activity);

            }
        });
        double finalReqNumEvidence = reqNumEvidence;
        btnAward.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int position = spnAward.getSelectedItemPosition();
                if(position > 1){
                    updateCompletion(
                            db,
                            0,
                            badgeID,
                            position
                    );
                }else{
                    updateCompletion(db, 1,badgeID, position);
                }
                spnAward.setSelection(0);
                lnrMembers.removeAllViews();
                btnAward.setEnabled(false);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    private CheckBox addCheckBox(CheckBox cb, String memberName, String memberRole){
        cb.setTextSize(20);
        cb.setText("Name: " + memberName + " Role: " + memberRole);
        cb.setTextColor(Color.WHITE);
        return cb;
    }
    private void updateCompletion(DBHelper db, float percentage, String badgeID, int position){
        for(int i=0; i < lnrMembers.getChildCount(); i++){
            View child = lnrMembers.getChildAt(i);
            if(child instanceof CheckBox) {
                if(((CheckBox) child).isChecked()){
                    i +=1;
                    child = lnrMembers.getChildAt(i);
                    if (child instanceof TextView) {
                        String userID = (String) ((TextView) child).getText();
                        String[] completion = db.getCompletion(userID, badgeID);
                        ArrayList<ArrayList<Object>> userEvidence = new ArrayList<>();
                        int evidenceAdd = 0;
                        if(position >= 2){
                            userEvidence = db.getUserBadgeReqEvidence(userID, badgeID, requirements.get(position-2).get(0));
                            evidenceAdd += updateEvidence(db, userID, badgeID, position, Integer.parseInt(requirements.get(position-2).get(2)), userEvidence);
                        }else{
                            ArrayList<ArrayList<String>> badgeReqs = db.getBadgeReqs(badgeID);
                            for(int x=0; x<badgeReqs.size(); x++){
                                position +=1;
                                userEvidence = db.getUserBadgeReqEvidence(userID, badgeID, badgeReqs.get(x).get(0));
                                updateEvidence(db, userID, badgeID, position, Integer.parseInt(badgeReqs.get(x).get(2)), userEvidence);
                            }
                        }
                        if(completion[1]!=null){
                            if(position > 1 && percentage != 1){
                                percentage += (double) evidenceAdd/ reqNumEvidence;
                                percentage += Float.parseFloat(completion[1]);
                                Log.d("completion", completion[1]);
                            }
                            Log.d("percentage1", String.valueOf(percentage));
                            db.updateCompletion(completion[0], percentage); //updateCompletion
                        }else{
                            if(percentage != 1){
                                percentage += (double) evidenceAdd/ reqNumEvidence;
                            }
                            Log.d("percentage2", String.valueOf(percentage));
                            db.addCompletion(new CompletionList(percentage, Integer.parseInt(userID), Integer.parseInt(badgeID)));
                        }

                    }
                }
            }
        }
    }
    private int updateEvidence(DBHelper db, String userID, String badgeID, int position, int numEvidence, ArrayList<ArrayList<Object>> userEvidence){
        if(userEvidence.size() > 0) {
            int count = 0;
            for (int x = 0; x < userEvidence.size(); x++) {
                if (userEvidence.get(x).get(3).equals("approved")) {//approved
                    count += 1;
                }
            }
            numEvidence -= count;
        }
        for(int x=0; x<numEvidence; x++){
            db.addEvidence(new EvidenceList("","",Integer.parseInt(userID), Integer.parseInt(badgeID), Integer.parseInt(requirements.get(position-2).get(0)), "approved"));
        }
        return numEvidence;
    }
}
