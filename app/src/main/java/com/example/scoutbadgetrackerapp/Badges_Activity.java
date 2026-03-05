package com.example.scoutbadgetrackerapp;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Badges_Activity extends Activity{
    Intent activity;
    private int progressStatus = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBHelper db = new DBHelper(this);
        setContentView(R.layout.activity_badges);
        Spinner spnFilter = findViewById(R.id.spnFilter);
        ScrollView parentLayout = findViewById(R.id.srvBadges);

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
                ArrayList<ArrayList<String>> badges = new ArrayList<ArrayList<String>>();
                if(position==1) { //Activity
                    Log.d("Activity Filter", (String) spnFilter.getSelectedItem());
                    parentLayout.removeAllViews();
                    badges = db.getBadgeByType("activity");
                }else if(position==2){ //Challenge
                    Log.d("Challenge Filter", (String) spnFilter.getSelectedItem());
                    parentLayout.removeAllViews();
                    badges = db.getBadgeByType("challenge");
                }else if(position==3){ //Core
                    Log.d("Core Filter", (String) spnFilter.getSelectedItem());
                    parentLayout.removeAllViews();
                    badges = db.getBadgeByType("core");
                }else if(position==4){ //Staged
                    Log.d("Staged Filter", (String) spnFilter.getSelectedItem());
                    parentLayout.removeAllViews();
                    badges = db.getBadgeByType("staged");
                }else{ //All Badges
                    Log.d("All Filter", (String) spnFilter.getSelectedItem());
                    parentLayout.removeAllViews();
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






    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    View.OnClickListener getOnClickDoSomething(final ImageButton button)  {
        return new View.OnClickListener() {
            public void onClick(View v) {
                activity = new Intent(Badges_Activity.this, SelectedBadges_Activity.class);
                activity.putExtra("key", (String) v.getContentDescription());
                startActivity(activity);
            }
        };
    }
    private void addBadgeView(ArrayList<ArrayList<String>> badges, ScrollView parentLayout){
        Badges_Activity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100) {
                    try {
                        int imgNum = 0;
                        int ROWS = badges.size() / 2;
                        boolean oddBadgeSize = false;
                        if((badges.size() % 2) != 0){
                            oddBadgeSize = true;
                        }
                        int COLUMNS = 2;
                        GridLayout gridLayout = new GridLayout(Badges_Activity.this);
                        gridLayout.setLayoutParams(new ViewGroup.LayoutParams(
                                GridLayout.LayoutParams.WRAP_CONTENT,
                                GridLayout.LayoutParams.WRAP_CONTENT
                        ));

                        gridLayout.setRowCount(ROWS);
                        gridLayout.setColumnCount(COLUMNS);

                        Log.d("Rows", String.valueOf(ROWS));
                        int padding = (int) (90*getResources().getDisplayMetrics().density + 0.5f);

                        for (int i = 0; i < ROWS; i++) {

                            for (int j = 0; j < COLUMNS; j++) {
                                ImageButton imgButton = new ImageButton(Badges_Activity.this);
                                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(350,350) ;
                                imgButton.setLayoutParams(params);
                                imgButton.setPadding(padding, padding, padding, padding);
                                imgButton.setContentDescription(badges.get(imgNum).get(1));
                                imgButton.setBackgroundResource(
                                        getResources().getIdentifier(badges.get(imgNum).get(3), "drawable", getPackageName())
                                );
                                imgButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
                                gridLayout.addView(imgButton);
                                imgButton.setOnClickListener(getOnClickDoSomething(imgButton));
                                imgNum += 1;
                            }
                        }
                        if(oddBadgeSize){

                            ImageButton imgButton = new ImageButton(Badges_Activity.this);
                            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(350,350) ;
                            imgButton.setLayoutParams(params);
                            imgButton.setPadding(padding, padding, padding, padding);
                            imgButton.setContentDescription(badges.get(imgNum).get(1));
                            imgButton.setBackgroundResource(
                                    getResources().getIdentifier(badges.get(imgNum).get(3), "drawable", getPackageName())
                            );
                            imgButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            gridLayout.addView(imgButton);
                            imgButton.setOnClickListener(getOnClickDoSomething(imgButton));
                        }
                        parentLayout.addView(gridLayout);
                        progressStatus = 100;
                    } catch (Exception e) {
                        Log.e("tag", e.getMessage());
                    }
                }
            }
        });
    }
}
