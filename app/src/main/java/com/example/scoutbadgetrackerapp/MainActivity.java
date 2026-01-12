package com.example.scoutbadgetrackerapp;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.List;

public class MainActivity extends Activity{
    Button btnLogin, btnBadges, btnGroups, btnSelectedBadges;
    Intent activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper db = new DBHelper(this);
        db.addBadge(new BadgeList(1,"Pioneering",null,null));
        db.addBadge(new BadgeList(2,"Air or Sea Navigation",null,null));
        List<BadgeList> badges = db.getAllBadges();
        Log.d("Badges", badges.toString());

        btnLogin = findViewById(R.id.btnLogin);
        btnBadges = findViewById(R.id.btnBadges);
        btnGroups = findViewById(R.id.btnGroups);
        btnSelectedBadges = findViewById(R.id.btnSelectedBadges);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the Supabutton");
                activity = new Intent(MainActivity.this, LogIn_Activity.class);
                startActivity(activity);

            }
        });
        btnBadges.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the Supabutton");
                activity = new Intent(MainActivity.this, Badges_Activity.class);
                startActivity(activity);

            }
        });
        btnGroups.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the Supabutton");
                activity = new Intent(MainActivity.this, Groups_Activity.class);
                startActivity(activity);

            }
        });
        btnSelectedBadges.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the Supabutton");
                activity = new Intent(MainActivity.this, SelectedBadges_Activity.class);
                startActivity(activity);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


}
