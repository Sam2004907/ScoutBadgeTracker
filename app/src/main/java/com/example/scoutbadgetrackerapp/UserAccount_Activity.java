package com.example.scoutbadgetrackerapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class UserAccount_Activity extends Activity {
    EditText etxtUsername2, etxtPassword2, etxtEmail2, etxtPhone2;
    TextView txtName2, txtDOB2, txtRole2, txtGroup2, txtGroupJoinDate;
    Button btnAccountBack, btnUpdateDetails;
    Intent activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useraccount);

        etxtUsername2 = findViewById(R.id.etxtUsername2);
        etxtPassword2 = findViewById(R.id.etxtPassword2);
        txtName2 = findViewById(R.id.txtName2);
        txtDOB2 = findViewById(R.id.txtDOB2);
        etxtEmail2 = findViewById(R.id.etxtEmail2);
        etxtPhone2 = findViewById(R.id.etxtPhone2);
        txtRole2 = findViewById(R.id.txtRole2);
        txtGroup2 = findViewById(R.id.txtGroup2);
        txtGroupJoinDate = findViewById(R.id.txtGroupJoinDate);
        btnAccountBack = findViewById(R.id.btnAccountBack);
        btnUpdateDetails = findViewById(R.id.btnUpdateDetails);

        DBHelper db = new DBHelper(this);
        Object[] userDetails = db.getUserByID(String.valueOf(currentUser.getUserID()));
        Object[] groupDetails = db.getGroupByID((String) userDetails[8]);

        etxtUsername2.setText((CharSequence) userDetails[1]);
        etxtPassword2.setHint("Change Password");
        txtName2.setText((CharSequence) userDetails[3]);
        txtDOB2.setText((CharSequence) userDetails[4]);
        etxtEmail2.setText((CharSequence) userDetails[5]);
        etxtPhone2.setText((CharSequence) userDetails[6]);
        txtRole2.setText((CharSequence) userDetails[7]);
        txtGroup2.setText((CharSequence) groupDetails[1]);
        txtGroupJoinDate.setText((CharSequence) userDetails[9]);

        btnAccountBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity = new Intent(UserAccount_Activity.this, MainActivity.class);
                startActivity(activity);

            }
        });
        btnUpdateDetails.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!(String.valueOf(etxtPassword2.getText()).equals(""))){
                    userDetails[2] = encrypt.encode(String.valueOf(etxtPassword2.getText()));
                }
                userDetails[1] = String.valueOf(etxtUsername2.getText());
                userDetails[5] = String.valueOf(etxtEmail2.getText());
                userDetails[6] = String.valueOf(etxtPhone2.getText());
                boolean successful = false;
                try{
                    db.updateUserDetails(userDetails);
                    successful = true;
                }catch (Exception e) {
                    Log.e("tag", e.getMessage());
                }
                AlertDialog alertDialog = new AlertDialog.Builder(UserAccount_Activity.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                if(successful){
                    alertDialog.setMessage("Details Updated");
                }else{
                    alertDialog.setMessage("An Error has Occurred.\n Your Details have not been updated");
                }
                etxtPassword2.setText("");
                alertDialog.show();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
