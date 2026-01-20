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
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SelectedBadges_Activity extends Activity{
    ImageView imgBadge;
    TextView txtTitle, txtBadgeInfo;
    Button btnEvidence;
    Intent activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBHelper db = new DBHelper(this);
        setContentView(R.layout.activity_selectedbadge);
        imgBadge = findViewById(R.id.imgBadge);
        txtTitle = findViewById(R.id.txtTitle);
        txtBadgeInfo = findViewById(R.id.txtBadgeInfo);
        btnEvidence = findViewById(R.id.btnEvidence);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String badgeName = extras.getString("key");
            //The key argument here must match that used in the other activity
            Log.d("Badge Name", badgeName);

            String[] badge = db.getBadge(badgeName);
            Log.d("Database Request", badge.toString());
            String iconName = badge[4];
            imgBadge.setImageResource(
                    getResources().getIdentifier(iconName, "drawable", getPackageName())
            );
            txtTitle.setText(badgeName);
            ArrayList<String> requirements = db.getBadgeReqs(badge[0]);
            requirements.forEach(req -> txtBadgeInfo.append(req + ". \n"));

        }
        btnEvidence.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity = new Intent(SelectedBadges_Activity.this, Evidence_Activity.class);
                startActivity(activity);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
