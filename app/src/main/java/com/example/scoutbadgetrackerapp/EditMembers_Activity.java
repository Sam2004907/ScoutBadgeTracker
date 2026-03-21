package com.example.scoutbadgetrackerapp;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class EditMembers_Activity extends Activity{
    TextView txtEditTitle, txtGroupName;
    Button btnApprove, btnDeny, btnMemberBack;
    GridLayout grdLayout;
    Intent activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editmembers);
        grdLayout = findViewById(R.id.grdLayout);
        txtEditTitle = findViewById(R.id.txtEditTitle);
        txtGroupName = findViewById(R.id.txtGroupName);
        btnApprove = findViewById(R.id.btnApprove);
        btnDeny = findViewById(R.id.btnDeny);
        btnMemberBack = findViewById(R.id.btnMemberBack);
        String editMembers = getIntent().getExtras().getString("key");
        txtEditTitle.setText("Edit "+editMembers);

        DBHelper db = new DBHelper(this);
        Object[] userDetails = db.getUser(String.valueOf(currentUser.getUsername()));
        Log.d("groupNum", (String) userDetails[8]);
        Object[] groupDetails = db.getGroupByID((String) userDetails[8]);
        ArrayList<ArrayList<Object>> unapprovedMembers = db.getUnapprovedGroupMembers((String) userDetails[8]);
        txtGroupName.setText((CharSequence) groupDetails[1]);
        Log.d("GroupName", (String) groupDetails[1]);
        String memberName, memberRole, memberID;
        grdLayout.setRowCount(unapprovedMembers.size());
        grdLayout.setColumnCount(2);

        for(int i = 0; i < unapprovedMembers.size(); i++){
            Log.d("Unapproved Member "+i, (String) unapprovedMembers.get(i).get(0));
            memberName = (String) unapprovedMembers.get(i).get(0);
            memberRole = (String) unapprovedMembers.get(i).get(1);
            memberID = (String) unapprovedMembers.get(i).get(4);
            CheckBox checkBox = new CheckBox(this);

            TextView txtID = new TextView(this);

            if(memberRole.equals("Leader") && editMembers.equals("Leaders")) {
                grdLayout.addView(addCheckBox(checkBox, memberName, memberRole));
                txtID.setText(memberID);
                txtID.setVisibility(View.INVISIBLE);
                grdLayout.addView(txtID);
            } else if ((memberRole.equals("Scout")||memberRole.equals("Parent/Guardian")) && editMembers.equals("Members")) {
                grdLayout.addView(addCheckBox(checkBox, memberName, memberRole));
                txtID.setText(memberID);
                txtID.setVisibility(View.INVISIBLE);
                grdLayout.addView(txtID);
            }
        }

        btnApprove.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                String currentDateString = dateFormat.format(new Date());
                updateStatus(db, currentDateString);
                activity = new Intent(EditMembers_Activity.this, Groups_Activity.class);
                startActivity(activity);
            }
        });
        btnDeny.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateStatus(db, "Denied");
                activity = new Intent(EditMembers_Activity.this, Groups_Activity.class);
                startActivity(activity);
            }
        });
        btnMemberBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity = new Intent(EditMembers_Activity.this, Groups_Activity.class);
                startActivity(activity);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    private CheckBox addCheckBox(CheckBox cb, String memberName, String memberRole){
        CheckBox checkBox = new CheckBox(this);
        checkBox.setTextSize(20);
        checkBox.setText("Name: " + memberName + " Role: " + memberRole);
        checkBox.setTextColor(Color.WHITE);
        checkBox.setDrawingCacheBackgroundColor(Color.WHITE);
        checkBox.setOnClickListener(getOnClickDoSomething(checkBox));
        return checkBox;
    }
    View.OnClickListener getOnClickDoSomething(final CheckBox checkBox)  {
        return new View.OnClickListener() {
            public void onClick(View v) {
                btnApprove.setEnabled(true);
                btnDeny.setEnabled(true);
            }
        };
    }
    private void updateStatus(DBHelper db, String status){
        for(int i=0; i < grdLayout.getChildCount(); i++){
            View child = grdLayout.getChildAt(i);
            if(child instanceof CheckBox) {
                if(((CheckBox) child).isChecked()){
                    Log.d("Information", (String) ((CheckBox) child).getText());
                    i +=1;
                    child = grdLayout.getChildAt(i);
                    if (child instanceof TextView) {
                        String groupMemberID = (String) ((TextView) child).getText();
                        db.updateGroupMemberStatus(groupMemberID, status);
                    }
                }
            }
        }
    }
}
