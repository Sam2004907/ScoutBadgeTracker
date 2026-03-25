package com.example.scoutbadgetrackerapp;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Groups_Activity extends Activity{
    TextView txtTitle, txtLeaderList, txtMemberList, txtParentTitle, txtParentList, txtMemberTitle, txtLeaderTitle;
    Button btnEditLeaders, btnEditMembers, btnGroupBack, btnLeaveGroup;
    Intent activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        txtTitle = findViewById(R.id.txtTitle);
        txtLeaderTitle = findViewById(R.id.txtLeaderTitle);
        txtLeaderList = findViewById(R.id.txtLeaderList);
        txtMemberTitle = findViewById(R.id.txtMemberTitle);
        txtMemberList = findViewById(R.id.txtMemberList);
        txtParentTitle = findViewById(R.id.txtParentTitle);
        txtParentList = findViewById(R.id.txtParentList);
        btnEditLeaders = findViewById(R.id.btnEditLeaders);
        btnEditMembers = findViewById(R.id.btnEditMembers);
        btnGroupBack = findViewById(R.id.btnGroupBack);
        btnLeaveGroup = findViewById(R.id.btnLeaveGroup);

        DBHelper db = new DBHelper(this);

        if(currentUser.getUserApproval().equals("unapproved") || currentUser.getUserApproval().equals("Denied")){
            txtLeaderList.setVisibility(View.INVISIBLE);
            txtMemberTitle.setVisibility(View.INVISIBLE);
            txtMemberList.setVisibility(View.INVISIBLE);
            txtParentTitle.setVisibility(View.INVISIBLE);
            txtParentList.setVisibility(View.INVISIBLE);
            btnEditLeaders.setVisibility(View.INVISIBLE);
            btnEditMembers.setVisibility(View.INVISIBLE);
            btnLeaveGroup.setVisibility(View.INVISIBLE);

            if(currentUser.getUserApproval().equals("unapproved")){
                txtTitle.setText("You are currently waiting for approval.");
                txtLeaderTitle.setText("Please speak with a leader at the group to be added.");
            }else{
                txtTitle.setText("You have been denied from joining.");
                txtLeaderTitle.setText("If you believe this to be wrong speak to a leader at the group.");
            }
        }else {
            if (currentUser.getUserRole().equals("Leader")) {
                btnEditLeaders.setVisibility(View.VISIBLE);
                btnEditMembers.setVisibility(View.VISIBLE);
                txtParentTitle.setVisibility(View.VISIBLE);
                txtParentList.setVisibility(View.VISIBLE);
            }
            Object[] userDetails = db.getUser(String.valueOf(currentUser.getUsername()));
            Log.d("group", (String) userDetails[8]);
            Object[] groupDetails = db.getGroupByID((String) userDetails[8]);
            ArrayList<ArrayList<Object>> groupMembers = db.getApprovedGroupMembers((String) userDetails[8]);
            txtTitle.setText((CharSequence) groupDetails[1]);
            String memberName, memberRole;
            for (int i = 0; i < groupMembers.size(); i++) {
                Log.d("Approved Member " + i, (String) groupMembers.get(i).get(0));
                memberName = (String) groupMembers.get(i).get(0);
                memberRole = (String) groupMembers.get(i).get(1);
                if (memberRole.equals("Leader")) {
                    txtLeaderList.append(" - " + memberName + "\n");
                } else if (memberRole.equals("Scout")) {
                    txtMemberList.append(" - " + memberName + "\n");
                } else if (memberRole.equals("Parent/Guardian")) {
                    txtParentList.append(" - " + memberName + "\n");
                }
            }
        }
        btnEditLeaders.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity = new Intent(Groups_Activity.this, EditMembers_Activity.class);
                activity.putExtra("key", "Leaders");
                startActivity(activity);

            }
        });
        btnEditMembers.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity = new Intent(Groups_Activity.this, EditMembers_Activity.class);
                activity.putExtra("key", "Members");
                startActivity(activity);
            }
        });
        btnGroupBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity = new Intent(Groups_Activity.this, MainActivity.class);
                startActivity(activity);

            }
        });
        btnLeaveGroup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(Groups_Activity.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("Delete User", "Yes");
                                Object[] userDetails = db.getUserByID(String.valueOf(currentUser.getUserID()));
                                userDetails[8] = "0"; //groupID
                                userDetails[9] = "unapproved"; //groupStatus
                                currentUser.setUserApproval("unapproved");
                                db.updateUserDetails(userDetails);
                                activity = new Intent(Groups_Activity.this, MainActivity.class);
                                startActivity(activity);
                                dialog.dismiss();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.setMessage("Are you sure you wish to leave the group?");
                alertDialog.show();

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
