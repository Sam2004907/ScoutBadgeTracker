package com.example.scoutbadgetrackerapp;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Groups_Activity extends Activity{
    TextView txtTitle, txtLeaderList, txtMemberList;
    Button btnEditLeaders, btnEditMembers;
    Intent activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        txtTitle = findViewById(R.id.txtTitle);
        txtLeaderList = findViewById(R.id.txtLeaderList);
        txtMemberList = findViewById(R.id.txtMemberList);
        btnEditLeaders = findViewById(R.id.btnEditLeaders);
        btnEditMembers = findViewById(R.id.btnEditMembers);

        DBHelper db = new DBHelper(this);
        Object[] userDetails = db.getUser(String.valueOf(currentUser.getUsername()));
        Object[] groupDetails = db.getGroupByID((String) userDetails[8]);
        ArrayList<ArrayList<Object>> groupMembers = db.getApprovedGroupMembers((String) userDetails[8]);
        ArrayList<ArrayList<Object>> unapprovedMembers = db.getUnapprovedGroupMembers((String) userDetails[8]);
        txtTitle.setText((CharSequence) groupDetails[1]);
        String memberName, memberRole;
        for(int i = 0; i < groupMembers.size(); i++){
            Log.d("Approved Member "+i, (String) groupMembers.get(i).get(0));
            memberName = (String) groupMembers.get(i).get(0);
            memberRole = (String) groupMembers.get(i).get(1);
            if(memberRole.equals("Leader")){
                txtLeaderList.append(memberName+ "\n");
            } else if (memberRole.equals("Scout")) {
                txtMemberList.append(memberName+ "\n");
            }
        }
        for(int i = 0; i < unapprovedMembers.size(); i++){
            Log.d("Unapproved Member "+i, (String) unapprovedMembers.get(i).get(0));
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

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
