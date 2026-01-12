package com.example.scoutbadgetrackerapp;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import androidx.annotation.Nullable;

public class Badges_Activity extends Activity{
    ImageButton imgbtnBadge1, imgbtnBadge2, imgbtnBadge3, imgbtnBadge4;
    Intent activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badges);
        imgbtnBadge1 = findViewById(R.id.imgbtnBadge1);
        imgbtnBadge2 = findViewById(R.id.imgbtnBadge2);
        imgbtnBadge3 = findViewById(R.id.imgbtnBadge3);
        imgbtnBadge4 = findViewById(R.id.imgbtnBadge4);
        imgbtnBadge1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", (String) imgbtnBadge1.getContentDescription());
                activity = new Intent(Badges_Activity.this, SelectedBadges_Activity.class);
                activity.putExtra("key", (String) imgbtnBadge1.getContentDescription());
                startActivity(activity);

            }
        });
        imgbtnBadge2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", (String) imgbtnBadge2.getContentDescription());
                activity = new Intent(Badges_Activity.this, SelectedBadges_Activity.class);
                activity.putExtra("key", (String) imgbtnBadge2.getContentDescription());
                startActivity(activity);

            }
        });
        imgbtnBadge3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", (String) imgbtnBadge3.getContentDescription());
                activity = new Intent(Badges_Activity.this, SelectedBadges_Activity.class);
                activity.putExtra("key", (String) imgbtnBadge3.getContentDescription());
                startActivity(activity);

            }
        });
        imgbtnBadge4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", (String) imgbtnBadge4.getContentDescription());
                activity = new Intent(Badges_Activity.this, SelectedBadges_Activity.class);
                activity.putExtra("key", (String) imgbtnBadge4.getContentDescription());
                startActivity(activity);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
