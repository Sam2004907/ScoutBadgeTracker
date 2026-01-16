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
import android.widget.Spinner;
import android.widget.TableLayout;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Badges_Activity extends Activity{
    ImageButton imgbtnBadge1, imgbtnBadge2, imgbtnBadge3, imgbtnBadge4;
    Intent activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBHelper db = new DBHelper(this);
        setContentView(R.layout.activity_badges);
        Spinner spnFilter = findViewById(R.id.spnFilter);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.badgeTypes,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnFilter.setAdapter(adapter);


        //Create Badge Grid View
        ArrayList<ArrayList<String>> badges = db.getAllBadges();
        TableLayout parentLayout = findViewById(R.id.layout);
        int imgNum = 0;
        int ROWS = badges.size() / 2;
        boolean oddBadgeSize = false;
        if((badges.size() % 2) != 0){
            oddBadgeSize = true;
        }
        int COLUMNS = 2;

        GridLayout gridLayout = new GridLayout(this);
        gridLayout.setLayoutParams(new ViewGroup.LayoutParams(
                GridLayout.LayoutParams.WRAP_CONTENT,
                GridLayout.LayoutParams.WRAP_CONTENT
        ));

        gridLayout.setRowCount(ROWS);
        gridLayout.setColumnCount(COLUMNS);

        Log.d("Rows", String.valueOf(ROWS));
        int padding = (int) (90*getResources().getDisplayMetrics().density + 0.5f);

        for (int i = 0; i < ROWS; i++) {
            Log.d("Current Row", String.valueOf(i));
            for (int j = 0; j < COLUMNS; j++) {
                Log.d("imgNum", String.valueOf(imgNum));
                ImageButton imgButton = new ImageButton(this);
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(350,350) ;
                imgButton.setLayoutParams(params);
                imgButton.setPadding(padding, padding, padding, padding);
                imgButton.setContentDescription(badges.get(imgNum).get(1));
                imgButton.setBackgroundResource(
                        getResources().getIdentifier(badges.get(imgNum).get(4), "drawable", getPackageName())
                );
                imgButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
                gridLayout.addView(imgButton);
                imgButton.setOnClickListener(getOnClickDoSomething(imgButton));
                imgNum += 1;
            }
        }
        if(oddBadgeSize){
            Log.d("imgNum", String.valueOf(imgNum));
            ImageButton imgButton = new ImageButton(this);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(350,350) ;
            imgButton.setLayoutParams(params);
            imgButton.setPadding(padding, padding, padding, padding);
            imgButton.setContentDescription(badges.get(imgNum).get(1));
            imgButton.setBackgroundResource(
                    getResources().getIdentifier(badges.get(imgNum).get(4), "drawable", getPackageName())
            );
            imgButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
            gridLayout.addView(imgButton);
            imgButton.setOnClickListener(getOnClickDoSomething(imgButton));
        }
        parentLayout.addView(gridLayout);
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

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item is selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos).
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback.
    }
}
