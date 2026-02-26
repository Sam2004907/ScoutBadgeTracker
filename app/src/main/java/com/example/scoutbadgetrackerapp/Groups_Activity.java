package com.example.scoutbadgetrackerapp;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Groups_Activity extends Activity{
    TextView txtTitle, txtLeaderList, txtMemberList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        txtTitle = findViewById(R.id.txtTitle);
        txtLeaderList = findViewById(R.id.txtLeaderList);
        txtMemberList = findViewById(R.id.txtMemberList);
        DBHelper db = new DBHelper(this);
        Object[] userDetails = db.getUser(String.valueOf(currentUser.getUsername()));
        Object[] groupDetails = db.getGroup((String) userDetails[8]);
        ArrayList<ArrayList<Object>> groupMembers = db.getGroupMembers((String) userDetails[8]);
        txtTitle.setText((CharSequence) groupDetails[1]);
        String memberName, memberRole;
        for(int i = 0; i < groupMembers.size(); i++){
            Log.d("Member "+i, (String) groupMembers.get(i).get(0));
            memberName = (String) groupMembers.get(i).get(0);
            memberRole = (String) groupMembers.get(i).get(1);
            if(memberRole.equals("Leader")){
                txtLeaderList.append(memberName+ "\n");
            } else if (memberRole.equals("Scout")) {
                txtMemberList.append(memberName+ "\n");
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
