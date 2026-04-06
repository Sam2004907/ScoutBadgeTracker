package com.example.scoutbadgetrackerapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Evidence_Activity extends Activity {
    TextView txtFileName, txtFileType;
    ImageView imgEvidence;
    Button btnFile, btnUpload;
    Spinner spnReq;
    String Fpath;
    Intent activity;

    public static final int GET_FROM_GALLERY = 3;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evidence);
        DBHelper db = new DBHelper(this);
        Bundle extras = getIntent().getExtras();
        Log.d("Given badgeID", extras.getString("key"));

        txtFileName = findViewById(R.id.txtFileName);
        txtFileType = findViewById(R.id.txtFileType);
        imgEvidence = findViewById(R.id.imgEvidence);
        btnFile = findViewById(R.id.btnFile);
        btnUpload = findViewById(R.id.btnUpload);
        spnReq = findViewById(R.id.spnReq);

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
        btnFile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, 1);
                btnUpload.setEnabled(true);
                btnUpload.setClickable(true);
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String FilePath = txtFileName.getText().toString();
                int reqID = Integer.parseInt(reqs.get(spnReq.getSelectedItemPosition() - 1).get(0));
                String type = txtFileType.getText().toString();
                db.addEvidence(new EvidenceList(type, FilePath, currentUser.getUserID(), Integer.parseInt(extras.getString("key")), reqID, "unapproved"));
                activity = new Intent(Evidence_Activity.this, SelectedBadges_Activity.class);
                activity.putExtra("key", extras.getString("desc"));
                startActivity(activity);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            Uri uri = data.getData();
            String selectedFilePath = null;
            try {
                selectedFilePath = RealFilePath.getPath(this, uri);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
            //Log.d("Fpath", selectedFilePath);
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