package com.example.scoutbadgetrackerapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.List;
import java.util.Objects;

public class SelectedBadges_Activity extends Activity{
    ImageView imgBadge;
    TextView txtTitle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBHelper db = new DBHelper(this);
        setContentView(R.layout.activity_selectedbadge);
        imgBadge = findViewById(R.id.imgBadge);
        txtTitle = findViewById(R.id.txtTitle);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String badgeName = extras.getString("key");
            //The key argument here must match that used in the other activity
            Log.d("Badge Name", badgeName);

            String[] badge = db.getBadge(badgeName);
            Log.d("Database Request", badge.toString());
            String iconName = badge[3];
            imgBadge.setImageResource(
                    getResources().getIdentifier(iconName, "drawable", getPackageName())
            );
            txtTitle.setText(badgeName);


//            if(Objects.equals(value, "activity_sc_activitycenterservice")){
//                imgBadge.setImageResource(R.drawable.activity_sc_activitycenterservice);
//                txtTitle.setText("Activity Center Service");
//            }else if(Objects.equals(value, "activity_sc_air_or_sea_nav")){
//
//            }else if(Objects.equals(value, "activity_sc_air_researcher")){
//                imgBadge.setImageResource(R.drawable.activity_sc_air_researcher);
//                txtTitle.setText("Air Researcher");
//            }else if(Objects.equals(value, "activity_sc_air_spotter")){
//                imgBadge.setImageResource(R.drawable.activity_sc_air_spotter);
//                txtTitle.setText("Air Spotter");
//            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
