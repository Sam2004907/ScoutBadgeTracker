package com.example.scoutbadgetrackerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Event_Activity extends Activity implements CalendarAdapter.OnItemListener{
    Button btnEventBack, btnEditEvent;
    TextView txtDateTitle, txtEventList;
    Intent activity;

    static ArrayList<ArrayList<Object>> events = new ArrayList<ArrayList<Object>>();

    private TextView monthYearText, txtEventDetails;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    private static String month;
    String groupID;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        btnEventBack = findViewById(R.id.btnEventBack);
        txtDateTitle = findViewById(R.id.txtDateTitle);
        txtEventDetails = findViewById(R.id.txtEventDetails);
        btnEditEvent = findViewById(R.id.btnEditEvent);

        DBHelper db = new DBHelper(this);
        Object[] userDetails = db.getUser(String.valueOf(currentUser.getUsername()));
        groupID = (String) userDetails[8];
        events = db.getGroupEvents(groupID);

        initWidgets();
        selectedDate = LocalDate.now();
        month = String.valueOf(selectedDate).substring(0, 8);
        setMonthView();


        btnEventBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity = new Intent(Event_Activity.this, MainActivity.class);
                startActivity(activity);

            }
        });
        btnEditEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity = new Intent(Event_Activity.this, EditEvent_Activity.class);
                startActivity(activity);

            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initWidgets()
    {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
    }

    private void setMonthView()
    {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    private ArrayList<String> daysInMonthArray(LocalDate date)
    {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i = 1; i <= 42; i++)
        {
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek)
            {
                daysInMonthArray.add("");
            }
            else
            {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return  daysInMonthArray;
    }

    private String monthYearFromDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    public void previousMonthAction(View view)
    {
        selectedDate = selectedDate.minusMonths(1);
        month = String.valueOf(selectedDate).substring(0, 8);
        setMonthView();
    }

    public void nextMonthAction(View view)
    {
        selectedDate = selectedDate.plusMonths(1);
        month = String.valueOf(selectedDate).substring(0, 8);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, String dayText)
    {
        if(!dayText.equals(""))
        {
            txtEventDetails.setText("");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
            if(Integer.parseInt(dayText) < 10){
                dayText = 0 + dayText;
            }
            String searchDate = selectedDate.format(formatter) +"-"+dayText;

            DBHelper db = new DBHelper(this);
            ArrayList<ArrayList<Object>> dateEvents = db.getGroupEventsByDate(groupID, searchDate);
            for(int i=0; i<dateEvents.size(); i++){
                txtEventDetails.append("Event Name: "+(String) dateEvents.get(i).get(1)+"\n");
                txtEventDetails.append("Start Date: "+(String) dateEvents.get(i).get(2)+"\n");
                txtEventDetails.append("End Date: "+(String) dateEvents.get(i).get(3)+"\n");
                txtEventDetails.append("Location: "+(String) dateEvents.get(i).get(4)+"\n");
            }

        }
    }

    public static boolean checkDateEvent(String day){
        String checkDate = "";
        boolean dateEvent = false;
        if(day != "") {
            if (Integer.parseInt(day) < 10) {
                checkDate = month + "0" + day;
            } else {
                checkDate = month + day;
            }
            for(int i=0; i<events.size(); i++){
                String eventDate = (String) events.get(i).get(2);//start_date
                if(eventDate.contains(checkDate)){
                    dateEvent = true;
                }
            }
        }
        return dateEvent;
    }
}
