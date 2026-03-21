package com.example.scoutbadgetrackerapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SignUp_Activity extends Activity {
    EditText etxtUsername, etxtPassword, etxtName, etxtDOB, etxtEmail, etxtPhone;
    Spinner spnRole, spnGroup;
    Button btnSignUp;
    Intent activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DBHelper db = new DBHelper(this);
        setContentView(R.layout.activity_signup);
        etxtUsername = findViewById(R.id.etxtUsername);
        etxtPassword = findViewById(R.id.etxtPassword);
        etxtName = findViewById(R.id.etxtName);
        etxtDOB = findViewById(R.id.etxtDOB);
        etxtEmail = findViewById(R.id.etxtEmail);
        etxtPhone = findViewById(R.id.etxtPhone);
        spnRole = findViewById(R.id.spnRole);
        spnGroup = findViewById(R.id.spnGroup);
        btnSignUp = findViewById(R.id.btnSignUp);

        ArrayAdapter<CharSequence> adapterRoles = ArrayAdapter.createFromResource(
                this,
                R.array.userRoles,
                android.R.layout.simple_spinner_item
        );
        adapterRoles.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnRole.setAdapter(adapterRoles);
        spnRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.WHITE);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
        spnGroup.setAdapter(adapterGroups);

        spnGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.WHITE);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("Role position", String.valueOf(spnRole.getSelectedItemPosition()));
                if(spnRole.getSelectedItemPosition() == 0 || spnGroup.getSelectedItemPosition() == 0){
                    AlertDialog alertDialog = new AlertDialog.Builder(SignUp_Activity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Please Select a Role and Group.");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                }else {
                    String role, groupID;
                    Object[] group = db.getGroupByName(spnGroup.getSelectedItem().toString());
                    role = spnRole.getSelectedItem().toString();
                    groupID = (String) group[0];
                    Log.d("groupID", groupID);
                    db.addUser(new UserList(
                            etxtUsername.getText().toString(),
                            etxtPassword.getText().toString(),
                            etxtName.getText().toString(),
                            etxtDOB.getText().toString(),
                            etxtEmail.getText().toString(),
                            etxtPhone.getText().toString(),
                            role,
                            Integer.parseInt(groupID)
                    ));
                    activity = new Intent(SignUp_Activity.this, LogIn_Activity.class);
                    startActivity(activity);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
