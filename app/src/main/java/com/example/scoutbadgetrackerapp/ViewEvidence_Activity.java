package com.example.scoutbadgetrackerapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ViewEvidence_Activity extends Activity {
    Button btnLogin, btnBadges, btnGroups, btnSelectedBadges, btnViewEvidence;
    Spinner spnMember, spnBadge, spnRequirement;
    LinearLayout lnrEvidence;
    Intent activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewevidence);
        spnMember = findViewById(R.id.spnMember);
        spnBadge = findViewById(R.id.spnBadge);
        spnRequirement = findViewById(R.id.spnRequirement);
        lnrEvidence = findViewById(R.id.lnrEvidence);
        DBHelper db = new DBHelper(this);

        Object[] userDetails = db.getUser(String.valueOf(currentUser.getUsername()));
        Object[] groupDetails = db.getGroupByID((String) userDetails[8]);
        ArrayList<ArrayList<Object>> groupMembers = db.getApprovedGroupMembers((String) userDetails[8]);
        ArrayList<ArrayList<String>> scoutDetails = new ArrayList<ArrayList<String>>();
        ArrayList<String> memberNames = new ArrayList<String>();
        ArrayList<String> badgeList = new ArrayList<String>();
        ArrayList<String> requirementList = new ArrayList<String>();
        ArrayList<String> requirementIDList = new ArrayList<String>();
        final String[] selectedScoutID = new String[1];

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
                    selectedScoutID[0] = scoutDetails.get(position-1).get(1);
                    ArrayList<ArrayList<Object>> userEvidenceList = db.getUserEvidenceList(selectedScoutID[0]);
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
                            R.layout.spinner_item,
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

        spnBadge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(position>0) {
                    spnRequirement.setVisibility(View.VISIBLE);
                    String badgeID = db.getBadgeByName(badgeList.get(position))[0];
                    ArrayList<ArrayList<String>> badgeRequirementList = db.getBadgeReqs(badgeID);
                    ArrayList<ArrayList<Object>> userBadgeEvidenceList = db.getUserBadgeEvidence(selectedScoutID[0], badgeID);
                    requirementList.add("Select a Requirement");
                    ArrayList<String> unapprovedReqID = new ArrayList<String>();
                    for(int i=0; i < userBadgeEvidenceList.size(); i++){
                        Log.d("unapproved ReqID", (String) userBadgeEvidenceList.get(i).get(6));
                        if(((String) userBadgeEvidenceList.get(i).get(3)).equals("0")){
                            unapprovedReqID.add((String) userBadgeEvidenceList.get(i).get(6));
                        }
                    }
                    for(int i=0; i<badgeRequirementList.size(); i++){
                        Log.d("reqID", badgeRequirementList.get(i).get(1));
                        if(unapprovedReqID.contains((String) badgeRequirementList.get(i).get(0))){
                            requirementList.add(badgeRequirementList.get(i).get(1));
                            requirementIDList.add((String) badgeRequirementList.get(i).get(0));
                        }
                    }
                    ArrayAdapter<String> adapterRequirements = new ArrayAdapter<>(
                            ViewEvidence_Activity.this,
                            R.layout.spinner_item,
                            requirementList
                    );
                    adapterRequirements.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnRequirement.setAdapter(adapterRequirements);

                }else{
                    spnRequirement.setVisibility(View.INVISIBLE);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spnRequirement.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(position>0) {
                    TextView txtTest = new TextView(ViewEvidence_Activity.this);
                    txtTest.setText("Test Line");
                    Log.d("Selected Scout", selectedScoutID[0]);
                    Log.d("Selected Requirement", requirementIDList.get(position-1));
                    ArrayList<ArrayList<Object>> reqEvidence = db.getSpecificUnapprovedEvidence(selectedScoutID[0] ,requirementIDList.get(position-1));
                    Log.d("Path", (String) reqEvidence.get(0).get(2));
                    ImageView imgEvidence = new ImageView(ViewEvidence_Activity.this);
                    //imgEvidence.setImageURI(evidencePath);
                    String s ="/document/raw:/storage/emulated/0/Download/IMG_E4874.HEIC";
                    String[] split = s.split("Download");
                    Log.d("Test Substring", split[1]);
                    File imgFile = new File(
                            Environment.getExternalStorageDirectory().getPath(),
                            "/Download/"+split[1]
                    );
                    if(imgFile.exists()){
                        Log.d("Image exists", "True");
                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        imgEvidence.setImageBitmap(myBitmap);
                    }else{
                        Log.d("Image exists", "False");
                    }

                    lnrEvidence.addView(txtTest);
                    lnrEvidence.addView(imgEvidence);
                }else{
                    lnrEvidence.removeAllViews();
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
