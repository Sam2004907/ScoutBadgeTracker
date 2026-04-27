package com.example.scoutbadgetrackerapp;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;

public class MainActivity extends Activity {
    Button btnLogin, btnBadges, btnGroups, btnUserAccount, btnViewEvidence, btnEvents;
    TextView txtTitle, txtBadgeOverview;
    Intent activity;
    PieChart badgeCompletionChart;
    private final String channelId = "i.apps.notifications"; // Unique channel ID for notifications

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper db = new DBHelper(this);

        //Check Permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED) {
            Log.d("Images Permission", "Already Granted");
        }else{
            String[] permissions = {Manifest.permission.READ_MEDIA_IMAGES};
            ActivityCompat.requestPermissions(this, permissions, 0);
            Log.d("Images Permission", "Granted");
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            Log.d("Notification Permission", "Already Granted");
        }else{
            String[] permissions = {Manifest.permission.POST_NOTIFICATIONS};
            ActivityCompat.requestPermissions(this, permissions, 0);
            Log.d("Notification Permission", "Granted");
        }
        if(currentUser.getUserRole().equals("Leader")){
            Object[] userDetails = db.getUser(String.valueOf(currentUser.getUsername()));
            ArrayList<ArrayList<Object>> groupMembers = db.getApprovedGroupMembers((String) userDetails[8]);

            int count=0;
            for(int i=0; i<groupMembers.size(); i++){
                if(groupMembers.get(i).get(1).equals("Scout")){
                    count += Integer.parseInt(db.getUserUnapprovedEvidenceCount((String) groupMembers.get(i).get(4)));
                }
            }
            if(count > 0){
                createNotificationChannel();
                sendNotification("Evidence To Approve", "There are "+count+" pieces evidence to approve.");
            }

        }

        btnLogin = findViewById(R.id.btnLogin);
        btnBadges = findViewById(R.id.btnBadges);
        btnGroups = findViewById(R.id.btnGroups);
        btnUserAccount = findViewById(R.id.btnUserAccount);
        btnViewEvidence = findViewById(R.id.btnViewEvidence);
        btnEvents = findViewById(R.id.btnEvents);
        badgeCompletionChart = findViewById(R.id.badgeCompletionChart);

        txtTitle = findViewById(R.id.txtTitle);
        txtBadgeOverview = findViewById(R.id.txtBadgeOverview);
        txtTitle.setText("Welcome "+currentUser.getUsername());


        setScoutPieChartData();

        if (currentUser.getUserRole().equals("Leader")) {
            btnViewEvidence.setVisibility(View.VISIBLE);
        }
        btnLogin.setOnClickListener(v -> {
            activity = new Intent(MainActivity.this, LogIn_Activity.class);
            startActivity(activity);

        });
        btnBadges.setOnClickListener(v -> {
            activity = new Intent(MainActivity.this, Badges_Activity.class);
            startActivity(activity);

        });
        btnGroups.setOnClickListener(v -> {
            activity = new Intent(MainActivity.this, Groups_Activity.class);
            startActivity(activity);

        });
        btnUserAccount.setOnClickListener(v -> {
            activity = new Intent(MainActivity.this, UserAccount_Activity.class);
            startActivity(activity);

        });
        btnViewEvidence.setOnClickListener(v -> {
            activity = new Intent(MainActivity.this, ViewEvidence_Activity.class);
            startActivity(activity);

        });
        btnEvents.setOnClickListener(v -> {
            activity = new Intent(MainActivity.this, Event_Activity.class);
            startActivity(activity);

        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void setScoutPieChartData(){
        DBHelper db = new DBHelper(this);
        int badgeTotal = 91;
        String completed = db.getUserCompletedBadges(String.valueOf(currentUser.getUserID()));
        String inProgress = db.getUserInprogressBadges(String.valueOf(currentUser.getUserID()));
        badgeTotal -= (Integer.parseInt(completed) + Integer.parseInt(inProgress));
        txtBadgeOverview.append(" "+completed+"/91");
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

    public void sendNotification(String title, String content) {
        // Intent that triggers when the notification is tapped
        Intent intent = new Intent(this, ViewEvidence_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.membership_award) // Notification icon
                .setContentTitle(title) // Title displayed in the notification
                .setContentText(content) // Text displayed in the notification
                .setContentIntent(pendingIntent) // Pending intent triggered when tapped
                .setAutoCancel(true) // Dismiss notification when tapped
                .setPriority(NotificationCompat.PRIORITY_HIGH); // Notification priority for better visibility

        // Display the notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        // Unique identifier for the notification
        int notificationId = 1234;
        notificationManager.notify(notificationId, builder.build());
    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Description for the notification channel
            String description = "Test notification";
            NotificationChannel notificationChannel = new NotificationChannel(
                    channelId,
                    description,
                    NotificationManager.IMPORTANCE_HIGH
            );
            notificationChannel.enableLights(true); // Turn on notification light
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.enableVibration(true); // Allow vibration for notifications

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
    }

}
