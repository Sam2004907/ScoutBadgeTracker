package com.example.scoutbadgetrackerapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SelectedBadges_Activity extends Activity{
    ImageView imgBadge;
    TextView txtTitle, txtBadgeInfo;
    Button btnEvidence;
    ProgressBar pgbCompletion;
    Intent activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBHelper db = new DBHelper(this);
        String badgeID = "";
        setContentView(R.layout.activity_selectedbadge);
        imgBadge = findViewById(R.id.imgBadge);
        txtTitle = findViewById(R.id.txtTitle);
        txtBadgeInfo = findViewById(R.id.txtBadgeInfo);
        btnEvidence = findViewById(R.id.btnEvidence);
        pgbCompletion = findViewById(R.id.pgbCompletion);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String badgeName = extras.getString("key");
            //The key argument here must match that used in the other activity
            Log.d("Badge Name", badgeName);
            String[] badge = db.getBadgeByName(badgeName);
            Log.d("Database Request", badge.toString());
            badgeID = badge[0];
            String iconName = badge[3];
            imgBadge.setImageResource(
                    getResources().getIdentifier(iconName, "drawable", getPackageName())
            );
            txtTitle.setText(badgeName);
            ArrayList<ArrayList<String>> requirements = db.getBadgeReqs(badge[0]);
            int reqNumEvidence = 0;
            for(int i=0; i<requirements.size(); i++){
                txtBadgeInfo.append(requirements.get(i).get(1) + ". \n");
                reqNumEvidence += Integer.parseInt(requirements.get(i).get(2));
            }
            Log.d("reqNumEvidence", String.valueOf(reqNumEvidence));
            int badgeEvidence = db.getUserBadgeEvidence(String.valueOf(currentUser.getUserID()), badgeID).size();
            Log.d("badgeEvidence", String.valueOf(badgeEvidence));
            pgbCompletion.setMax(reqNumEvidence);
            pgbCompletion.setProgress(badgeEvidence);

        }
        String finalBadgeID = badgeID;
        btnEvidence.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity = new Intent(SelectedBadges_Activity.this, Evidence_Activity.class);
                activity.putExtra("key", finalBadgeID);
                activity.putExtra("desc", extras.getString("key"));
                startActivity(activity);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
