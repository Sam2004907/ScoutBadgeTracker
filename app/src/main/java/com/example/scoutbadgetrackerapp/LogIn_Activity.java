package com.example.scoutbadgetrackerapp;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class LogIn_Activity extends Activity {
    // used to display the log in interface.
    Button btnLogIn, btnSignUp;
    EditText etxtUsername, etxtPassword;
    TextView txtTimer;
    Intent activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int[] attempts = {3};
        setContentView(R.layout.activity_login);
        btnLogIn = findViewById(R.id.btnLogIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        etxtUsername = findViewById(R.id.etxtUsername);
        etxtPassword = findViewById(R.id.etxtPassword);
        txtTimer = findViewById(R.id.txtTimer);
        DBHelper db = new DBHelper(this);

        //used to add databse details, uncomment in pairs and relaunch app, once added recomment the lines.
//        addData.addCoreBadges(db);
//        addData.addChallengeBadges(db);
//        addData.addStagedBadges(db);
//        addData.addActivityBadges(db);
//        addData.addExternalBadges(db);
//        addData.addGroups(db);
//        addData.addCoreRequirements(db);
//        addData.addStagedRequirements(db);
//        addData.addActivityRequirements(db);
//        addData.addExternalRequirements(db);
//        addData.addUsers(db);
//        addData.updateUsersStatus(db);
//        addData.addEvents(db);

        AlertDialog alertDialog = new AlertDialog.Builder(LogIn_Activity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                (dialog, which) -> dialog.dismiss());
        btnLogIn.setOnClickListener(v -> {
            String givenUsername, givenPassword;
            givenUsername = String.valueOf(etxtUsername.getText());
            givenPassword = String.valueOf(etxtPassword.getText());
            //checks that a username and password has been entered
            if(givenUsername.equals("") || givenPassword.equals("")){
                alertDialog.setMessage("Please enter a username and password.");
                alertDialog.show();
            }else {
                attempts[0] -= 1;
                if(attempts[0] > 0){
                    Object[] user = db.getUser(givenUsername);
                    //checks if username and password are correct
                    if (givenUsername.equals(user[1]) && encrypt.encode(givenPassword).equals(user[2])) {
                        new currentUser((String) user[1], (String) user[3], (String) user[7], (String) user[9], Integer.parseInt((String) user[0]));
                        activity = new Intent(LogIn_Activity.this, MainActivity.class);
                        startActivity(activity);
                    } else {
                        //if not correct error message appears
                        alertDialog.setMessage("Incorrect Username and Password. You have " + attempts[0] + " remaining");
                        alertDialog.show();
                        etxtUsername.setText("");
                        etxtPassword.setText("");
                    }
                }else{
                    alertDialog.setMessage("You have no attempts remaining");
                    alertDialog.show();
                    btnLogIn.setEnabled(false);
                    txtTimer.setEnabled(true);
                    etxtUsername.setText("");
                    etxtPassword.setText("");

                    //Start disabled Log In button for 2 Minutes
                    new CountDownTimer(120000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            //update time left
                            txtTimer.setText("Time remaining until log in reset: " + (millisUntilFinished / 1000) + " Seconds");
                        }

                        public void onFinish() {
                            btnLogIn.setEnabled(true);
                            txtTimer.setText("");
                            txtTimer.setEnabled(false);
                            attempts[0] = 3;
                        }
                    }.start();
                }
            }
        });
        btnSignUp.setOnClickListener(v -> {
            activity = new Intent(LogIn_Activity.this, SignUp_Activity.class);
            startActivity(activity);

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
