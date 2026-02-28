package com.example.scoutbadgetrackerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ViewEvidence_Activity extends Activity {
    Button btnLogin, btnBadges, btnGroups, btnSelectedBadges, btnViewEvidence;
    Spinner spnMember, spnBadge;
    Intent activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewevidence);
        spnMember = findViewById(R.id.spnMember);
        spnBadge = findViewById(R.id.spnBadge);
        DBHelper db = new DBHelper(this);

        Object[] userDetails = db.getUser(String.valueOf(currentUser.getUsername()));
        Object[] groupDetails = db.getGroupByID((String) userDetails[8]);
        ArrayList<ArrayList<Object>> groupMembers = db.getApprovedGroupMembers((String) userDetails[8]);
        ArrayList<ArrayList<String>> scoutDetails = new ArrayList<ArrayList<String>>();
        ArrayList<String> memberNames = new ArrayList<String>();
        int count=0;
        for(int i=0; i<groupMembers.size(); i++){
            if(groupMembers.get(i).get(1).equals("Scout")){
                memberNames.add((String) groupMembers.get(i).get(0));
                scoutDetails.add(new ArrayList<String>());
                scoutDetails.get(count).add((String) groupMembers.get(i).get(0));
                scoutDetails.get(count).add((String) groupMembers.get(i).get(4));
                count+=1;
            }
        }

        String[] membersArray = new String[memberNames.size()+1];
        membersArray[0] = "Select a Member";
        for(int i = 0; i < memberNames.size(); i++){
            membersArray[i+1]=memberNames.get(i);//badge Requirements
        }
        List<String> membersList = Arrays.asList(membersArray);
        CustomSpinnerAdapter adapterMembers = new CustomSpinnerAdapter(
                this,
                R.layout.spinner_item,
                membersList
        );
//        adapterMembers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMember.setAdapter(adapterMembers);

        spnMember.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(position>0) {
                    spnBadge.setVisibility(View.VISIBLE);
                    Log.d("Scout Name", scoutDetails.get(position-1).get(0));
                    Log.d("Scout userID", scoutDetails.get(position-1).get(1));
                    ArrayList<ArrayList<Object>> userEvidenceList = db.getUserEvidenceList(scoutDetails.get(position-1).get(1));
                    ArrayList<String> badgeList = new ArrayList<String>();
                    badgeList.add("Select a Badge");
                    for(int i=0; i<userEvidenceList.size(); i++){
                        if((((String) userEvidenceList.get(i).get(1))).equals("0")){
                            String badgeName = db.getBadgeByID((String) userEvidenceList.get(i).get(3))[1];
                            Log.d("badgeID", (String) userEvidenceList.get(i).get(3));
                            Log.d("badgeName", (db.getBadgeByID((String) userEvidenceList.get(i).get(3))).toString());
                            Log.d("badgeName", badgeName);
                            if(!(badgeList.contains(badgeName))){
                                badgeList.add(badgeName);
                            }
                        }
                    }
                    ArrayAdapter<String> adapterBadges = new ArrayAdapter<>(
                            ViewEvidence_Activity.this,
                            android.R.layout.simple_spinner_item,
                            badgeList
                    );
                    adapterBadges.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnBadge.setAdapter(adapterBadges);

                }else{
                    spnBadge.setVisibility(View.INVISIBLE);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
