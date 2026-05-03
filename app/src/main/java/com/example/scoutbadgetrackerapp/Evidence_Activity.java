package com.example.scoutbadgetrackerapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class Evidence_Activity extends Activity {
    //used to allow user to upload evidence to application.
    TextView txtFileName, txtFileType;
    ImageView imgEvidence;
    Button btnFile, btnUpload, btnEvidenceBack;
    Spinner spnReq;
    Intent activity;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evidence);
        DBHelper db = new DBHelper(this);
        Bundle extras = getIntent().getExtras();


        txtFileName = findViewById(R.id.txtFileName);
        txtFileType = findViewById(R.id.txtFileType);
        imgEvidence = findViewById(R.id.imgEvidence);
        btnFile = findViewById(R.id.btnFile);
        btnUpload = findViewById(R.id.btnUpload);
        spnReq = findViewById(R.id.spnReq);
        btnEvidenceBack = findViewById(R.id.btnEvidenceBack);

        btnFile.setEnabled(false);
        btnFile.setClickable(false);
        btnUpload.setEnabled(false);
        btnUpload.setClickable(false);

        ArrayList<ArrayList<String>> reqs = db.getBadgeReqs(extras.getString("key"));
        String[] badgeReqs = new String[reqs.size()+1];
        badgeReqs[0] = "Select a Requirement";
        for(int i = 0; i < reqs.size(); i++){
            badgeReqs[i+1]=reqs.get(i).get(1);//badge Requirements
        }
        //used to add badge requirements to requirement spinner based on selected badge.
        ArrayAdapter<String> adapterReqs = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                badgeReqs
        );
        adapterReqs.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnReq.setAdapter(adapterReqs);

        spnReq.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView)parentView.getChildAt(0)).setTextColor(Color.WHITE);
                if(position > 0){
                    btnFile.setEnabled(true);
                    btnFile.setClickable(true);
                }else{
                    btnFile.setEnabled(false);
                    btnFile.setClickable(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //open phone file explorer.
        btnFile.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            startActivityForResult(intent, 1);
            btnUpload.setEnabled(true);
            btnUpload.setClickable(true);
        });
        //send details to databse
        btnUpload.setOnClickListener(v -> {
            String FilePath = txtFileName.getText().toString();
            int reqID = Integer.parseInt(reqs.get(spnReq.getSelectedItemPosition() - 1).get(0));
            String type = txtFileType.getText().toString();
            db.addEvidence(new EvidenceList(type, FilePath, currentUser.getUserID(), Integer.parseInt(extras.getString("key")), reqID, "unapproved"));
            activity = new Intent(Evidence_Activity.this, SelectedBadges_Activity.class);
            activity.putExtra("key", extras.getString("desc"));
            startActivity(activity);
        });

        btnEvidenceBack.setOnClickListener(v -> {
            activity = new Intent(Evidence_Activity.this, SelectedBadges_Activity.class);
            activity.putExtra("key", extras.getString("desc"));
            startActivity(activity);

        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            Uri uri = data.getData();
            String selectedFilePath;
            try {
                selectedFilePath = RealFilePath.getPath(this, uri);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }

            txtFileName.setText(selectedFilePath);
            txtFileType.setText(getContentResolver().getType(uri));
            imgEvidence.setImageURI(uri);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
}