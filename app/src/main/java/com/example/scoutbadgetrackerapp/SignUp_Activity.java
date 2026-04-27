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
    Spinner spnRole, spnGroup, spnCounty, spnDistrict;
    Button btnSignUp, btnSignUpBack;
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
        spnCounty = findViewById(R.id.spnCounty);
        spnDistrict = findViewById(R.id.spnDistrict);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUpBack = findViewById(R.id.btnSignUpBack);

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
        ArrayList<String> counties = db.getAllCounty();
        String[] countyNames = new String[counties.size()+1];
        countyNames[0] = "Select a County";
        for(int i=0; i < counties.size(); i++){
            countyNames[i+1] = counties.get(i);
        }
        ArrayAdapter<String> adapterCounty = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                countyNames
        );
        adapterCounty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCounty.setAdapter(adapterCounty);
        spnCounty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                if(position > 0) {
                    spnDistrict.setClickable(true);
                    spnDistrict.setVisibility(View.VISIBLE);

                    ArrayList<String> districts = db.getCountyDistricts(spnCounty.getSelectedItem().toString());
                    String[] districtNames = new String[districts.size() + 1];
                    districtNames[0] = "Select a District";
                    for (int x = 0; x < districts.size(); x++) {
                        districtNames[x + 1] = districts.get(x);
                    }
                    ArrayAdapter<String> adapterDistrict = new ArrayAdapter<>(
                            SignUp_Activity.this,
                            android.R.layout.simple_spinner_item,
                            districtNames
                    );
                    adapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnDistrict.setAdapter(adapterDistrict);
                }else{
                    spnDistrict.setClickable(false);
                    spnDistrict.setVisibility(View.INVISIBLE);
                    spnDistrict.setAdapter(null);
                    spnGroup.setAdapter(null);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spnDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                if(position>0) {
                    spnGroup.setClickable(true);
                    spnGroup.setVisibility(View.VISIBLE);

                    ArrayList<ArrayList<String>> groups = db.getDistrictGroups(spnDistrict.getSelectedItem().toString());
                    String[] groupNames = new String[groups.size()+1];
                    groupNames[0] = "Select a Group";
                    for(int i = 0; i < groups.size(); i++){
                        groupNames[i+1]=groups.get(i).get(1);//groupNames
                    }
                    ArrayAdapter<String> adapterGroups = new ArrayAdapter<>(
                            SignUp_Activity.this,
                            android.R.layout.simple_spinner_item,
                            groupNames
                    );
                    adapterGroups.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnGroup.setAdapter(adapterGroups);
                }else{
                    spnGroup.setClickable(false);
                    spnGroup.setVisibility(View.INVISIBLE);
                    spnGroup.setAdapter(null);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
                if(spnRole.getSelectedItemPosition() == 0 || spnCounty.getSelectedItemPosition() == 0 || spnDistrict.getSelectedItemPosition() == 0 || spnGroup.getSelectedItemPosition() == 0){
                    alertBox("Please Select a Role and Group.");

                } else if (etxtUsername.getText().length() == 0 ||
                        etxtPassword.getText().length() == 0 ||
                        etxtName.getText().length() == 0||
                        etxtDOB.getText().length() == 0 ||
                        etxtEmail.getText().length() == 0 ||
                        etxtPhone.getText().length() == 0) {
                    alertBox("Please fill in all your details.");


                } else {
                    Object[] usernameCheck = db.getUser(etxtUsername.getText().toString());
                    Log.d("check", String.valueOf(usernameCheck.length));
                    if((String)usernameCheck[1] != null){
                        alertBox("Username already exists.\n" +
                                "Please enter a unique username.");

                    }else{
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
            }
        });

        btnSignUpBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity = new Intent(SignUp_Activity.this, LogIn_Activity.class);
                startActivity(activity);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void alertBox(String text){
        AlertDialog alertDialog = new AlertDialog.Builder(SignUp_Activity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(text);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
