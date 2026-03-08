package com.example.scoutbadgetrackerapp;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ViewEvidence_Activity extends Activity {
    Button btnShow;
    Spinner spnMember, spnBadge, spnRequirement;
    LinearLayout lnrEvidence;
    private int progressStatus = 0;
    private int requirementPosition = 0;
    private Handler handler = new Handler();
    Intent activity;
    private String userID, badgeID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewevidence);
        spnMember = findViewById(R.id.spnMember);
        spnBadge = findViewById(R.id.spnBadge);
        spnRequirement = findViewById(R.id.spnRequirement);
        lnrEvidence = findViewById(R.id.lnrEvidence);
        btnShow = findViewById(R.id.btnShow);
        DBHelper db = new DBHelper(this);

        Object[] userDetails = db.getUser(String.valueOf(currentUser.getUsername()));
        Object[] groupDetails = db.getGroupByID((String) userDetails[8]);
        ArrayList<ArrayList<Object>> groupMembers = db.getApprovedGroupMembers((String) userDetails[8]);
        ArrayList<ArrayList<String>> scoutDetails = new ArrayList<ArrayList<String>>();
        ArrayList<String> memberNames = new ArrayList<String>();
        ArrayList<String> badgeList = new ArrayList<String>();
        ArrayList<String> requirementList = new ArrayList<String>();
        ArrayList<String> requirementIDList = new ArrayList<String>();

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
                badgeList.clear();
                if(position>0) {
                    spnBadge.setVisibility(View.VISIBLE);
                    Log.d("Scout Name", scoutDetails.get(position-1).get(0));
                    Log.d("Scout userID", scoutDetails.get(position-1).get(1));
                    userID = scoutDetails.get(position-1).get(1);
                    ArrayList<ArrayList<Object>> userEvidenceList = db.getUserEvidenceList(userID);
                    badgeList.add("Select a Badge");
                    for(int i=0; i<userEvidenceList.size(); i++){
                        if((((String) userEvidenceList.get(i).get(1))).equals("unapproved")){
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
                    Log.d("adaptersize", String.valueOf(badgeList.size()));
                    if(badgeList.size()<=1){
                        noEvidence();
                        spnBadge.setVisibility(View.INVISIBLE);
                    }
                    adapterBadges.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnBadge.setAdapter(adapterBadges);
                }else{
                    spnBadge.setVisibility(View.INVISIBLE);
                    spnRequirement.setVisibility(View.INVISIBLE);
                    spnBadge.setAdapter(null);
                    spnRequirement.setAdapter(null);
                    btnShow.setVisibility(View.INVISIBLE);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                spnBadge.setVisibility(View.INVISIBLE);
                spnRequirement.setVisibility(View.INVISIBLE);
                spnBadge.setAdapter(null);
                spnRequirement.setAdapter(null);
                btnShow.setVisibility(View.INVISIBLE);
            }
        });

        spnBadge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                requirementList.clear();
                if(position>0) {
                    spnRequirement.setVisibility(View.VISIBLE);
                    badgeID = db.getBadgeByName(badgeList.get(position))[0];
                    ArrayList<ArrayList<String>> badgeRequirementList = db.getBadgeReqs(badgeID);
                    ArrayList<ArrayList<Object>> userBadgeEvidenceList = db.getUserBadgeEvidence(userID, badgeID);
                    requirementList.add("Select a Requirement");
                    ArrayList<String> unapprovedReqID = new ArrayList<String>();
                    for(int i=0; i < userBadgeEvidenceList.size(); i++){
                        Log.d("unapproved ReqID", (String) userBadgeEvidenceList.get(i).get(6));
                        if(((String) userBadgeEvidenceList.get(i).get(3)).equals("unapproved")){
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
                    spnRequirement.setAdapter(null);
                    btnShow.setVisibility(View.INVISIBLE);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                spnRequirement.setVisibility(View.INVISIBLE);
                spnRequirement.setAdapter(null);
                btnShow.setVisibility(View.INVISIBLE);
            }
        });
        spnRequirement.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                requirementPosition = position;
                btnShow.setVisibility(View.VISIBLE);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lnrEvidence.removeAllViews();
                if(requirementPosition>0) {
                    TextView txtTest = new TextView(ViewEvidence_Activity.this);
                    txtTest.setText("Test Line");
                    Log.d("Selected Scout", userID);
                    Log.d("Selected Requirement", requirementIDList.get(requirementPosition - 1));
                    ArrayList<ArrayList<Object>> reqEvidence = db.getSpecificUnapprovedEvidence(userID, requirementIDList.get(requirementPosition - 1));
                    if(reqEvidence.size()>0) {
                        progressStatus=0;
                        ViewEvidence_Activity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int progressUpdate = 100 / reqEvidence.size();
                                while (progressStatus < 100) {
                                    try {
                                        for (int i = 0; i < reqEvidence.size(); i++) {
                                            addEvidenceView((String) reqEvidence.get(i).get(2), (String) reqEvidence.get(i).get(0), (String) reqEvidence.get(i).get(1));
                                            progressStatus += progressUpdate;
                                        }
                                    } catch (Exception e) {
                                        Log.e("tag", e.getMessage());
                                    }
                                }
                            }
                        });
                    }else{
                        noEvidence();
                    }
                }else{
                    lnrEvidence.removeAllViews();
                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    private void noEvidence(){
        lnrEvidence.removeAllViews();
        TextView noEvidence = new TextView(ViewEvidence_Activity.this);
        noEvidence.setText("No Unapproved Evidence");
        noEvidence.setTextSize(24);
        lnrEvidence.addView(noEvidence);
    }

    private void addEvidenceView(String path, String evidenceID, String evidenceType){

        ImageView imgEvidence = new ImageView(ViewEvidence_Activity.this);
        TextView txtEvidence = new TextView(ViewEvidence_Activity.this);
        Button btnApprove = new Button(ViewEvidence_Activity.this);
        Button btnDeny = new Button(ViewEvidence_Activity.this);
        GridLayout grdLayout = new GridLayout(ViewEvidence_Activity.this);

        File imgFile = new File(path);
        if(imgFile.exists()){
            imgEvidence.setImageURI(Uri.fromFile(imgFile));
        }else{
            imgEvidence.setImageResource(R.drawable.ic_launcher_foreground);
        }

        //set Evidence linear layout component details
        txtEvidence.setText("Evidence ID: "+evidenceID+" Evidence Type: "+evidenceType);
        btnApprove.setText("Approve");
        btnApprove.setContentDescription((CharSequence) evidenceID);
        btnDeny.setText("Deny");
        btnDeny.setContentDescription((CharSequence) evidenceID);
        txtEvidence.setTextSize(18);
        txtEvidence.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        grdLayout.setColumnCount(2);
        grdLayout.setRowCount(1);

        //add button click calls
        btnApprove.setOnClickListener(getOnApproveClick(btnApprove));
        btnDeny.setOnClickListener(getOnDenyClick(btnDeny));

        //add components to Evidence linear layout
        lnrEvidence.addView(txtEvidence);
        lnrEvidence.addView(imgEvidence, 1000,1000);
        grdLayout.addView(btnApprove);
        grdLayout.addView(btnDeny);
        lnrEvidence.addView(grdLayout);
    }

    View.OnClickListener getOnApproveClick(final Button button)  {
        return new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("Approve ButtonClick", (String) v.getContentDescription());
                DBHelper db = new DBHelper(ViewEvidence_Activity.this);
                db.updateEvidenceApproval((String) v.getContentDescription(), "approved");
                String[] completionDetails = db.getCompletion(userID, badgeID);

                ArrayList<ArrayList<String>> requirements = db.getBadgeReqs(badgeID);
                int reqNumEvidence = 0;
                float percentage = 0;
                for(int i=0; i<requirements.size(); i++){
                    reqNumEvidence += Integer.parseInt(requirements.get(i).get(2));
                }
                Log.d("reqNumEvidence", String.valueOf(reqNumEvidence));
                int badgeEvidence = db.getUserBadgeEvidence(String.valueOf(currentUser.getUserID()), badgeID).size();
                Log.d("badgeEvidence", String.valueOf(badgeEvidence));

                if(completionDetails[0]==null){
                    //Add new completion
                    Log.d("CompletionDetails", "Empty");
                    db.addCompletion(new CompletionList(percentage, Integer.parseInt(userID), Integer.parseInt(badgeID)));
                    completionDetails = db.getCompletion(userID, badgeID);
                }else{
                    percentage = Float.parseFloat(completionDetails[1]);
                }
                //Add one evidence piece worth to percentage value.
                percentage += (double) 1/ (double )reqNumEvidence;
                Log.d("percentage", String.valueOf(percentage));
                db.updateCompletion(completionDetails[0], percentage);

                lnrEvidence.removeAllViews();
                btnShow.performClick();

            }
        };
    }
    View.OnClickListener getOnDenyClick(final Button button)  {
        return new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("Deny Button Click", (String) v.getContentDescription());
                DBHelper db = new DBHelper(ViewEvidence_Activity.this);
                //db.updateEvidenceApproval((String) v.getContentDescription(), "denied");
            }
        };
    }
}
