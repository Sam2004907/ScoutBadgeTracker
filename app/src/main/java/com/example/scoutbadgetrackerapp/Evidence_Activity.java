package com.example.scoutbadgetrackerapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Evidence_Activity extends Activity {
    TextView txtFileName;
    ImageView imgEvidence;
    Button btnFile;
    String Fpath;

    public static final int GET_FROM_GALLERY = 3;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evidence);
        DBHelper db = new DBHelper(this);
        txtFileName = findViewById(R.id.txtFileName);
        imgEvidence = findViewById(R.id.imgEvidence);
        btnFile = findViewById(R.id.btnFile);

        btnFile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, 1);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            Log.d("select pivture", "passed her");
            Uri selectedMediaUri = data.getData();

            Fpath = selectedMediaUri.getPath();

            Log.d("Fpath", Fpath);
            txtFileName.setText(Fpath);
            imgEvidence.setImageURI(selectedMediaUri);

        }
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
}