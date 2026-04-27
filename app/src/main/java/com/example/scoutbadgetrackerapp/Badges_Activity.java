package com.example.scoutbadgetrackerapp;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Badges_Activity extends Activity{
    Intent activity;
    Button btnBadgesBack;
    private int progressStatus = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBHelper db = new DBHelper(this);
        setContentView(R.layout.activity_badges);
        Spinner spnFilter = findViewById(R.id.spnFilter);
        ScrollView parentLayout = findViewById(R.id.srvBadges);
        btnBadgesBack = findViewById(R.id.btnBadgesBack);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.badgeTypes,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnFilter.setAdapter(adapter);

        spnFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView)parentView.getChildAt(0)).setTextColor(Color.WHITE);
                ArrayList<ArrayList<String>> badges;
                parentLayout.removeAllViews();
                if(position==1) { //Activity
                    badges = db.getBadgeByType("activity");
                }else if(position==2){ //Challenge
                    badges = db.getBadgeByType("challenge");
                }else if(position==3){ //Core
                    badges = db.getBadgeByType("core");
                }else if(position==4){ //Staged
                    badges = db.getBadgeByType("staged");
                }else if(position==5){ //external
                    badges = db.getBadgeByType("external");
                }else{ //All Badges
                    badges = db.getAllBadges();

                }
                //Create Badge Grid View
                progressStatus=0;
                addBadgeView(badges, parentLayout);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
        btnBadgesBack.setOnClickListener(v -> {
            activity = new Intent(Badges_Activity.this, MainActivity.class);
            startActivity(activity);

        });

    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    View.OnClickListener getOnClickDoSomething(final ImageButton button)  {
        return v -> {
            activity = new Intent(Badges_Activity.this, SelectedBadges_Activity.class);
            activity.putExtra("key", (String) v.getContentDescription());
            startActivity(activity);
        };
    }
    private void addBadgeView(ArrayList<ArrayList<String>> badges, ScrollView parentLayout){
        Badges_Activity.this.runOnUiThread(() -> {
            while (progressStatus < 100) {
                try {
                    int imgNum = 0;
                    int ROWS = badges.size() / 2;
                    boolean oddBadgeSize = (badges.size() % 2) != 0;
                    int COLUMNS = 2;
                    GridLayout gridLayout = new GridLayout(Badges_Activity.this);
                    gridLayout.setLayoutParams(new ViewGroup.LayoutParams(
                            GridLayout.LayoutParams.WRAP_CONTENT,
                            GridLayout.LayoutParams.WRAP_CONTENT
                    ));
                    gridLayout.setPadding(50,10,50,10);

                    gridLayout.setRowCount(ROWS);
                    gridLayout.setColumnCount(COLUMNS);
                    int halfWidth = (parentLayout.getWidth()/2) - 100;

                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(halfWidth,halfWidth);

                    for (int i = 0; i < ROWS; i++) {

                        for (int j = 0; j < COLUMNS; j++) {
                            ImageButton imgButton = new ImageButton(Badges_Activity.this);
                            imgButton.setLayoutParams(params);
                            imgButton.setPaddingRelative(100,100,100,100);
                            imgButton.setContentDescription(badges.get(imgNum).get(1));
                            imgButton.setBackgroundResource(
                                    getResources().getIdentifier(badges.get(imgNum).get(3), "drawable", getPackageName())
                            );

                            DBHelper db = new DBHelper(Badges_Activity.this);
                            String[] completion = db.getCompletion(String.valueOf(currentUser.getUserID()), badges.get(imgNum).get(0));
                            if(completion[0]!=null) {
                                if ((Float.parseFloat(completion[1]) * 100) > 99) {
                                    RelativeLayout relLayout = new RelativeLayout(Badges_Activity.this);
                                    ImageView imgComplete = new ImageView(Badges_Activity.this);
                                    imgComplete.setBackgroundResource(R.drawable.accept_icon);
                                    imgComplete.setMaxHeight(30);
                                    imgComplete.setMaxWidth(30);
                                    relLayout.addView(imgButton);
                                    relLayout.addView(imgComplete);
                                    gridLayout.addView(relLayout);
                                } else {
                                    gridLayout.addView(imgButton);
                                }
                            }else{
                                gridLayout.addView(imgButton);
                            }
                            imgButton.setOnClickListener(getOnClickDoSomething(imgButton));
                            imgNum += 1;
                        }
                    }
                    if(oddBadgeSize){
                        ImageButton imgButton = new ImageButton(Badges_Activity.this);
                        imgButton.setLayoutParams(params);
                        imgButton.setPaddingRelative(100,100,100,100);
                        imgButton.setContentDescription(badges.get(imgNum).get(1));
                        imgButton.setBackgroundResource(
                                getResources().getIdentifier(badges.get(imgNum).get(3), "drawable", getPackageName())
                        );

                        DBHelper db = new DBHelper(Badges_Activity.this);
                        String[] completion = db.getCompletion(String.valueOf(currentUser.getUserID()), badges.get(imgNum).get(0));
                        if(completion[0]!=null) {
                            if ((Float.parseFloat(completion[1]) * 100) > 99) {
                                RelativeLayout relLayout = new RelativeLayout(Badges_Activity.this);
                                ImageView imgComplete = new ImageView(Badges_Activity.this);
                                imgComplete.setBackgroundResource(R.drawable.accept_icon);
                                imgComplete.setMaxHeight(30);
                                imgComplete.setMaxWidth(30);
                                relLayout.addView(imgButton);
                                relLayout.addView(imgComplete);
                                gridLayout.addView(relLayout);
                            } else {
                                gridLayout.addView(imgButton);
                            }
                        }else{
                            gridLayout.addView(imgButton);
                        }
                        imgButton.setOnClickListener(getOnClickDoSomething(imgButton));
                    }
                    parentLayout.addView(gridLayout);
                    progressStatus = 100;
                } catch (Exception e) {
                    Log.e("tag", e.getMessage());
                    progressStatus = 100;
                }
            }
        });
    }
}
