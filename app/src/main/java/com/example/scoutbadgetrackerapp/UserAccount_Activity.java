package com.example.scoutbadgetrackerapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class UserAccount_Activity extends Activity {
    // displays user details and allows for them to be updated.
    EditText etxtPassword2, etxtEmail2, etxtPhone2;
    TextView txtName2, txtDOB2, txtRole2, txtGroupJoinDate, txtUsername2;
    Button btnAccountBack, btnUpdateDetails, btnDeleteUser;
    Spinner spnGroup2;
    Intent activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useraccount);

        txtUsername2 = findViewById(R.id.txtUsername2);
        etxtPassword2 = findViewById(R.id.etxtPassword2);
        txtName2 = findViewById(R.id.txtName2);
        txtDOB2 = findViewById(R.id.txtDOB2);
        etxtEmail2 = findViewById(R.id.etxtEmail2);
        etxtPhone2 = findViewById(R.id.etxtPhone2);
        txtRole2 = findViewById(R.id.txtRole2);
        spnGroup2 = findViewById(R.id.spnGroup2);
        txtGroupJoinDate = findViewById(R.id.txtGroupJoinDate);

        btnAccountBack = findViewById(R.id.btnAccountBack);
        btnUpdateDetails = findViewById(R.id.btnUpdateDetails);
        btnDeleteUser = findViewById(R.id.btnDeleteUser);

        setGridSize();

        DBHelper db = new DBHelper(this);
        Object[] userDetails = db.getUserByID(String.valueOf(currentUser.getUserID()));

        txtUsername2.setText((CharSequence) userDetails[1]);
        etxtPassword2.setHint("Change Password");
        txtName2.setText((CharSequence) userDetails[3]);
        txtDOB2.setText((CharSequence) userDetails[4]);
        etxtEmail2.setText((CharSequence) userDetails[5]);
        etxtPhone2.setText((CharSequence) userDetails[6]);
        txtRole2.setText((CharSequence) userDetails[7]);
        txtGroupJoinDate.setText((CharSequence) userDetails[9]);

        ArrayList<ArrayList<String>> groups = db.getAllGroups();
        String[] groupNames = new String[groups.size()+1];
        groupNames[0] = "Select a Group";
        for(int i = 0; i < groups.size(); i++){
            groupNames[i+1]=groups.get(i).get(1);//groupNames
        }
        ArrayAdapter<String> adapterGroups = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                groupNames
        );
        adapterGroups.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnGroup2.setAdapter(adapterGroups);

        if(((String) userDetails[8]).equals("0") || ((String) userDetails[9]).equals("Denied")){
            spnGroup2.setClickable(true);
            spnGroup2.setEnabled(true);
            spnGroup2.setSelection(0);
        }else{
            spnGroup2.setEnabled(false);
            spnGroup2.setClickable(false);
            spnGroup2.setSelection(Integer.parseInt((String) userDetails[8]));
        }
        spnGroup2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.WHITE);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnAccountBack.setOnClickListener(v -> {
            activity = new Intent(UserAccount_Activity.this, MainActivity.class);
            startActivity(activity);

        });
        btnUpdateDetails.setOnClickListener(v -> {
            //checks all details have been entered before upload.
            if(etxtEmail2.getText().length() == 0 || etxtPhone2.getText().length() == 0){
                AlertDialog alertDialog = new AlertDialog.Builder(UserAccount_Activity.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Please fill in all your details.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        (dialog, which) -> dialog.dismiss());
                alertDialog.show();
            }else {
                if (!(String.valueOf(etxtPassword2.getText()).equals(""))) {
                    userDetails[2] = encrypt.encode(String.valueOf(etxtPassword2.getText()));
                }
                userDetails[5] = String.valueOf(etxtEmail2.getText());
                userDetails[6] = String.valueOf(etxtPhone2.getText());
                if (((String) userDetails[8]).equals("0") || ((String) userDetails[9]).equals("Denied")) {
                    if (spnGroup2.getSelectedItemPosition() != 0) {
                        Object[] group = db.getGroupByName(spnGroup2.getSelectedItem().toString());
                        userDetails[8] = (String) group[0];
                        userDetails[9] = "unapproved";
                    }
                }
                boolean successful = false;
                try {
                    db.updateUserDetails(userDetails);
                    successful = true;
                } catch (Exception e) {
                    Log.e("tag", e.getMessage());
                }
                AlertDialog alertDialog = new AlertDialog.Builder(UserAccount_Activity.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        (dialog, which) -> dialog.dismiss());
                if (successful) {
                    alertDialog.setMessage("Details Updated");
                    spnGroup2.setEnabled(false);
                    spnGroup2.setClickable(false);
                } else {
                    alertDialog.setMessage("An Error has Occurred.\n Your Details have not been updated");
                }
                etxtPassword2.setText("");
                alertDialog.show();
            }
        });
        btnDeleteUser.setOnClickListener(v -> {
            AlertDialog alertDialog = new AlertDialog.Builder(UserAccount_Activity.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                    (dialog, which) -> {

                        db.deleteUser((String) userDetails[0]);
                        activity = new Intent(UserAccount_Activity.this, LogIn_Activity.class);
                        startActivity(activity);
                        dialog.dismiss();
                    });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                    (dialog, which) -> dialog.dismiss());
            alertDialog.setMessage("Are you sure you wish to delete your account.\n This Action Cannot Be Undone");
            alertDialog.show();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void setGridSize(){
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int screenWidth = size.x;
        int halfScreenWidth = (int)(screenWidth *0.5);
        txtUsername2.setWidth(halfScreenWidth);
        etxtPassword2.setWidth(halfScreenWidth);
        txtName2.setWidth(halfScreenWidth);
        txtDOB2.setWidth(halfScreenWidth);
        etxtEmail2.setWidth(halfScreenWidth);
        etxtPhone2.setWidth(halfScreenWidth);
        txtRole2.setWidth(halfScreenWidth);
        spnGroup2.setDropDownWidth(halfScreenWidth);
        txtGroupJoinDate.setWidth(halfScreenWidth);
    }

}
