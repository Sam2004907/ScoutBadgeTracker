package com.example.scoutbadgetrackerapp;

import static java.lang.reflect.Array.set;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;

public class EditEvent_Activity extends Activity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{
    Button btnStartDate, btnEndDate, btnAddEvent, btnUpdateEvent, btnDeleteEvent, btnEditEventBack;
    TextView txtStartDate, txtEndDate;
    EditText etxtEventname, etxtLocation;
    int day, month, year, hour, minute;
    int tempDay, tempMonth, tempYear, tempHour, tempMinute;
    int startDay, startMonth, startYear, startHour, startMinute;
    int endDay, endMonth, endYear, endHour, endMinute;
    String startOrEnd, eventID;
    String[] eventDetails;
    Intent activity;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editevent);
        btnStartDate = findViewById(R.id.btnStartDate);
        btnEndDate = findViewById(R.id.btnEndDate);
        txtStartDate = findViewById(R.id.txtStartDate);
        txtEndDate = findViewById(R.id.txtEndDate);
        btnAddEvent = findViewById(R.id.btnAddEvent);
        etxtEventname = findViewById(R.id.etxtEventname);
        etxtLocation = findViewById(R.id.etxtLocation);
        btnUpdateEvent = findViewById(R.id.btnUpdateEvent);
        btnDeleteEvent = findViewById(R.id.btnDeleteEvent);
        btnEditEventBack = findViewById(R.id.btnEditEventBack);

        DBHelper db = new DBHelper(this);

        Bundle extras = getIntent().getExtras();
        eventID = extras.getString("key");
        if(!(eventID.equals("new"))){
            eventDetails = db.getEventByID(eventID);
            btnUpdateEvent.setEnabled(true);
            btnUpdateEvent.setVisibility(View.VISIBLE);
            btnDeleteEvent.setEnabled(true);
            btnDeleteEvent.setVisibility(View.VISIBLE);

            etxtEventname.setText(eventDetails[1]);
            etxtLocation.setText(eventDetails[4]);
            txtStartDate.setText(eventDetails[2]);
            txtEndDate.setText(eventDetails[3]);
        }else{
            btnAddEvent.setEnabled(true);
            btnAddEvent.setVisibility(View.VISIBLE);
        }

        btnStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtStartDate.setText("");
                startOrEnd = "start";
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditEvent_Activity.this, EditEvent_Activity.this,year, month,day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                datePickerDialog.show();
            }
        });
        btnEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtEndDate.setText("");
                startOrEnd = "end";
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditEvent_Activity.this, EditEvent_Activity.this,year, month,day);
                Calendar mCalendar = Calendar.getInstance();
                mCalendar.set(startYear, startMonth-1, startDay);
                datePickerDialog.getDatePicker().setMinDate(mCalendar.getTimeInMillis()-1000);
                datePickerDialog.show();
            }
        });
        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName = String.valueOf(etxtEventname.getText());
                String location = String.valueOf(etxtLocation.getText());
                if(eventName.equals("") || location.equals("") || startDay == 0 || endDay == 0){
                    AlertDialog alertDialog = new AlertDialog.Builder(EditEvent_Activity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Please fill out all of the event details including a start and end date.");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }else{
                    String startDate = convertToStringDate(startYear, startMonth, startDay, startHour, startMinute);
                    String endDate = convertToStringDate(endYear, endMonth, endDay, endHour, endMinute);
                    Object[] userDetails = db.getUser(String.valueOf(currentUser.getUsername()));
                    String groupID = (String) userDetails[8];
                    db.addEvent(new EventList(eventName, startDate, endDate, location, Integer.parseInt(groupID)));

                    activity = new Intent(EditEvent_Activity.this, Event_Activity.class);
                    startActivity(activity);

                }

            }
        });
        btnEditEventBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity = new Intent(EditEvent_Activity.this, Event_Activity.class);
                startActivity(activity);

            }
        });
        btnDeleteEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                db.deleteEvent(eventID);
                activity = new Intent(EditEvent_Activity.this, Event_Activity.class);
                startActivity(activity);

            }
        });
        btnUpdateEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String eventName = String.valueOf(etxtEventname.getText());
                String location = String.valueOf(etxtLocation.getText());
                if(eventName.equals("") || location.equals("") || startDay == 0 || endDay == 0) {
                    AlertDialog alertDialog = new AlertDialog.Builder(EditEvent_Activity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Please fill out all of the event details including a start and end date.");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }else {
                    if (startDay != 0) {
                        eventDetails[2] = convertToStringDate(startYear, startMonth, startDay, startHour, startMinute);
                    }
                    if (endDay != 0) {
                        eventDetails[3] = convertToStringDate(endYear, endMonth, endDay, endHour, endMinute);
                    }
                    eventDetails[1] = String.valueOf(etxtEventname.getText());
                    eventDetails[4] = String.valueOf(etxtLocation.getText());
                    db.updateEventDetails(eventDetails);
                    activity = new Intent(EditEvent_Activity.this, Event_Activity.class);
                    startActivity(activity);
                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        tempYear = year;
        tempDay = dayOfMonth;
        tempMonth = month;
        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(EditEvent_Activity.this, EditEvent_Activity.this, hour, minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();
        if(startOrEnd.equals("start")){
            if(tempDay < 10){
                txtStartDate.append("0"+tempDay+"/");
            }else{
                txtStartDate.append(tempDay+"/");
            }
            tempMonth+=1;
            if(tempMonth < 10){
                txtStartDate.append("0"+tempMonth+"/");
            }else{
                txtStartDate.append(tempMonth+"/");
            }
            txtStartDate.append(tempYear+"\n");
            startDay = tempDay;
            startMonth = tempMonth;
            startYear = tempYear;
        }else{
            if(tempDay < 10){
                txtEndDate.append("0"+tempDay+"/");
            }else{
                txtEndDate.append(tempDay+"/");
            }
            tempMonth+=1;
            if(tempMonth < 10){
                txtEndDate.append("0"+tempMonth+"/");
            }else{
                txtEndDate.append(tempMonth+"/");
            }
            txtEndDate.append(tempYear+"\n");
            endDay = tempDay;
            endMonth = tempMonth;
            endYear = tempYear;
        }
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        tempHour = hourOfDay;
        tempMinute = minute;
        if(startOrEnd.equals("start")) {
            if (tempHour < 10) {
                txtStartDate.append("0" + tempHour + ":");
            } else {
                txtStartDate.append(tempHour + ":");
            }
            if (tempMinute < 10) {
                txtStartDate.append("0" + tempMinute + "\n");
            } else {
                txtStartDate.append(tempMinute + "\n");
            }
            startHour = tempHour;
            startMinute = tempMinute;
        }else{
            if (tempHour < 10) {
                txtEndDate.append("0" + tempHour + ":");
            } else {
                txtEndDate.append(tempHour + ":");
            }
            if (tempMinute < 10) {
                txtEndDate.append("0" + tempMinute + "\n");
            } else {
                txtEndDate.append(tempMinute + "\n");
            }
            endHour = tempHour;
            endMinute = tempMinute;
        }
    }

    private String convertToStringDate(int year, int month, int day, int hour, int minute){
        String returnDate = String.valueOf(year) + "-";
        if(month < 10){
            returnDate += "0"+month+"-";
        }else{
            returnDate += month+"-";
        }
        if(day < 10){
            returnDate += "0"+day;
        }else{
            returnDate += day;
        }
        returnDate += " ";
        if (hour < 10) {
            returnDate += "0" + hour + ":";
        } else {
            returnDate += hour + ":";
        }
        if (minute < 10) {
            returnDate += "0" + minute;
        } else {
            returnDate += minute;
        }
        returnDate += ":00.000";
        return returnDate;
    }
}
