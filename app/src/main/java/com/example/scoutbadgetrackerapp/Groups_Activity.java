package com.example.scoutbadgetrackerapp;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Groups_Activity extends Activity{
    TextView txtTitle, txtParentTitle, txtParentList, txtMemberTitle, txtLeaderTitle, txtScoutName;
    Button btnEditLeaders, btnEditMembers, btnGroupBack, btnLeaveGroup, btnUpdateDetails;
    LinearLayout lytLeaderList, lytMemberList;
    Spinner spnPosition;
    Intent activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        txtTitle = findViewById(R.id.txtTitle);
        txtLeaderTitle = findViewById(R.id.txtLeaderTitle);
        lytLeaderList = findViewById(R.id.lytLeaderList);
        txtMemberTitle = findViewById(R.id.txtMemberTitle);
        lytMemberList = findViewById(R.id.lytMemberList);
        txtParentTitle = findViewById(R.id.txtParentTitle);
        txtParentList = findViewById(R.id.txtParentList);
        btnEditLeaders = findViewById(R.id.btnEditLeaders);
        btnEditMembers = findViewById(R.id.btnEditMembers);
        btnGroupBack = findViewById(R.id.btnGroupBack);
        btnLeaveGroup = findViewById(R.id.btnLeaveGroup);

        txtScoutName = findViewById(R.id.txtScoutName);
        spnPosition = findViewById(R.id.spnPosition);
        btnUpdateDetails = findViewById(R.id.btnUpdateDetails);

        DBHelper db = new DBHelper(this);

        if(currentUser.getUserApproval().equals("unapproved") || currentUser.getUserApproval().equals("Denied")){
            txtMemberTitle.setVisibility(View.INVISIBLE);
            txtParentTitle.setVisibility(View.INVISIBLE);
            txtParentList.setVisibility(View.INVISIBLE);
            btnEditLeaders.setVisibility(View.INVISIBLE);
            btnEditMembers.setVisibility(View.INVISIBLE);
            btnLeaveGroup.setVisibility(View.INVISIBLE);

            if(currentUser.getUserApproval().equals("unapproved")){
                txtTitle.setText(R.string.waitingApproval);
                txtLeaderTitle.setText(R.string.speakLeader);
            }else{
                txtTitle.setText(R.string.deniedApproval);
                txtLeaderTitle.setText(R.string.speakLeader2);
            }
        }else {
            if (currentUser.getUserRole().equals("Leader")) {
                btnEditLeaders.setVisibility(View.VISIBLE);
                btnEditMembers.setVisibility(View.VISIBLE);
                txtParentTitle.setVisibility(View.VISIBLE);
                txtParentList.setVisibility(View.VISIBLE);
            }
            Object[] userDetails = db.getUser(String.valueOf(currentUser.getUsername()));

            Object[] groupDetails = db.getGroupByID((String) userDetails[8]);
            ArrayList<ArrayList<Object>> groupMembers = db.getApprovedGroupMembers((String) userDetails[8]);
            txtTitle.setText((CharSequence) groupDetails[1]);
            String memberName, memberRole;
            for (int i = 0; i < groupMembers.size(); i++) {

                memberName = (String) groupMembers.get(i).get(0);
                memberRole = (String) groupMembers.get(i).get(1);
                switch (memberRole) {
                    case "Leader":
                        TextView txtLeader = new TextView(this);
                        txtLeader.setTextColor(Color.WHITE);
                        txtLeader.setTextSize(20f);
                        txtLeader.setText(" - " + memberName);
                        lytLeaderList.addView(txtLeader);
                        break;
                    case "Scout":
                        TextView txtScout = new TextView(this);
                        txtScout.setTextColor(Color.WHITE);
                        txtScout.setTextSize(20f);
                        txtScout.setText(" - " + memberName);
                        txtScout.setContentDescription((String) groupMembers.get(i).get(4));
                        txtScout.setClickable(true);
                        txtScout.setOnClickListener(editScoutMember(txtScout));
                        lytMemberList.addView(txtScout);
                        break;
                    case "Parent/Guardian":
                        txtParentList.append(" - " + memberName + "\n");
                        break;
                }
            }
        }
        btnEditLeaders.setOnClickListener(v -> {
            activity = new Intent(Groups_Activity.this, EditMembers_Activity.class);
            activity.putExtra("key", "Leaders");
            startActivity(activity);

        });
        btnEditMembers.setOnClickListener(v -> {
            activity = new Intent(Groups_Activity.this, EditMembers_Activity.class);
            activity.putExtra("key", "Members");
            startActivity(activity);
        });
        btnGroupBack.setOnClickListener(v -> {
            activity = new Intent(Groups_Activity.this, MainActivity.class);
            startActivity(activity);

        });
        btnLeaveGroup.setOnClickListener(v -> {
            AlertDialog alertDialog = new AlertDialog.Builder(Groups_Activity.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                    (dialog, which) -> {

                        Object[] userDetails = db.getUserByID(String.valueOf(currentUser.getUserID()));
                        userDetails[8] = "0"; //groupID
                        userDetails[9] = "unapproved"; //groupStatus
                        currentUser.setUserApproval("unapproved");
                        db.updateUserDetails(userDetails);
                        activity = new Intent(Groups_Activity.this, MainActivity.class);
                        startActivity(activity);
                        dialog.dismiss();
                    });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                    (dialog, which) -> dialog.dismiss());
            alertDialog.setMessage("Are you sure you wish to leave the group?");
            alertDialog.show();

        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private View.OnClickListener editScoutMember(final TextView textview){
        return v -> showUpdateScout((String) textview.getContentDescription());
    }
    private void showUpdateScout(String scoutID){
        DBHelper db = new DBHelper(this);
        Object[] scoutDetails = db.getUserByID(scoutID);
        String[] completion = db.getCompletion((String) scoutDetails[0], String.valueOf(91));
        txtScoutName.setText("Name: " + scoutDetails[3]);
        txtScoutName.setVisibility(View.VISIBLE);
        spnPosition.setVisibility(View.VISIBLE);
        btnUpdateDetails.setVisibility(View.VISIBLE);
        ArrayAdapter<CharSequence> adapterRoles = ArrayAdapter.createFromResource(
                this,
                R.array.scoutPosition,
                android.R.layout.simple_spinner_item
        );
        adapterRoles.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPosition.setAdapter(adapterRoles);

        if(completion[1]!= null){
            spnPosition.setSelection(3);
        }else{
            completion = db.getCompletion((String) scoutDetails[0], String.valueOf(90));

            if(completion[1]!= null){
                spnPosition.setSelection(2);
            }else{
                completion = db.getCompletion((String) scoutDetails[0], String.valueOf(89));

                if(completion[1]!= null){
                    spnPosition.setSelection(1);
                }else{
                    spnPosition.setSelection(0);
                }
            }
        }
        spnPosition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.WHITE);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnUpdateDetails.setOnClickListener(v -> {
            int position = spnPosition.getSelectedItemPosition();
            if(position == 1){
                db.addCompletion(new CompletionList(1, Integer.parseInt((String) scoutDetails[0]), 89));
            }else if(position == 2){
                db.addCompletion(new CompletionList(1, Integer.parseInt((String) scoutDetails[0]), 90));
            }else if(position == 3){
                db.addCompletion(new CompletionList(1, Integer.parseInt((String) scoutDetails[0]), 91));
            }
            txtScoutName.setText("");
            txtScoutName.setVisibility(View.INVISIBLE);
            spnPosition.setVisibility(View.INVISIBLE);
            btnUpdateDetails.setVisibility(View.INVISIBLE);

        });
    }
}
