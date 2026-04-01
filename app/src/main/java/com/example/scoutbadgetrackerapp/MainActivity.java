package com.example.scoutbadgetrackerapp;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_MEDIA_IMAGES;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends Activity {
    Button btnLogin, btnBadges, btnGroups, btnUserAccount, btnViewEvidence, btnEvents;
    TextView txtTitle;
    Intent activity;
    Date stringDate;
    PieChart badgeCompletionChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED) {
            Log.d("Permission", "Already Granted");
        }else{
            String[] permissions = {Manifest.permission.READ_MEDIA_IMAGES};
            ActivityCompat.requestPermissions(this, permissions, 0);
            Log.d("Permission", "Granted");
        }

        DBHelper db = new DBHelper(this);

//        new currentUser("sam", "leader", 1);

        ArrayList<ArrayList<String>> badges = db.getAllBadges();
        badges.forEach(element -> Log.d("Badge", String.valueOf(element)));
//        Log.d("Badges", badges.toString());

        ArrayList<ArrayList<Object>> users = db.getAllUsers();
        users.forEach(element -> Log.d("User", String.valueOf(element)));
//        Log.d("Users", users.toString());
//        Log.d("Current User", String.valueOf(currentUser.getUserID()));

        btnLogin = findViewById(R.id.btnLogin);
        btnBadges = findViewById(R.id.btnBadges);
        btnGroups = findViewById(R.id.btnGroups);
        btnUserAccount = findViewById(R.id.btnUserAccount);
        btnViewEvidence = findViewById(R.id.btnViewEvidence);
        btnEvents = findViewById(R.id.btnEvents);
        badgeCompletionChart = findViewById(R.id.badgeCompletionChart);

        txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText("Welcome "+currentUser.getUsername());


        setScoutPieChartData();

        if (currentUser.getUserRole().equals("Leader")) {
            btnViewEvidence.setVisibility(View.VISIBLE);
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity = new Intent(MainActivity.this, LogIn_Activity.class);
                startActivity(activity);

            }
        });
        btnBadges.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ;
                activity = new Intent(MainActivity.this, Badges_Activity.class);
                startActivity(activity);

            }
        });
        btnGroups.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity = new Intent(MainActivity.this, Groups_Activity.class);
                startActivity(activity);

            }
        });
        btnUserAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity = new Intent(MainActivity.this, UserAccount_Activity.class);
                startActivity(activity);

            }
        });
        btnViewEvidence.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity = new Intent(MainActivity.this, ViewEvidence_Activity.class);
                startActivity(activity);

            }
        });
        btnEvents.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity = new Intent(MainActivity.this, Event_Activity.class);
                startActivity(activity);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void setScoutPieChartData(){
        DBHelper db = new DBHelper(this);
        int badgeTotal = 85;
        String completed = db.getUserCompletedBadges(String.valueOf(currentUser.getUserID()));
        String inProgress = db.getUserInprogressBadges(String.valueOf(currentUser.getUserID()));
        Log.d("completed", completed);
        Log.d("inProgress", inProgress);
        badgeTotal -= (Integer.parseInt(completed) + Integer.parseInt(inProgress));
        Log.d("badgeRemaining", String.valueOf(badgeTotal));
        badgeCompletionChart.addPieSlice(
                new PieModel(Float.parseFloat(completed), getColor(R.color.Scout_green))
        );
        badgeCompletionChart.addPieSlice(
                new PieModel(Float.parseFloat(inProgress), getColor(R.color.Scout_yellow))
        );
        badgeCompletionChart.addPieSlice(
                new PieModel(badgeTotal, getColor(R.color.Scout_red))
        );
        badgeCompletionChart.startAnimation();
    }

}
