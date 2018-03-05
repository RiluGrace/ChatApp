package com.example.babur.chatapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StatusActivity extends AppCompatActivity
{

    private Toolbar mToolbar;
    private TextInputLayout mStatus;
    private Button btn;

    // firebase
    private DatabaseReference mStatusDatabase;
    private FirebaseUser  mCurrentUser;

    //progress
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        //firebase

        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        String current_uid = mCurrentUser.getUid();

        mStatusDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(current_uid);

        mToolbar = (Toolbar) findViewById(R.id.mToolbar2);
        mToolbar.setTitle(" Account Status");
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_arrow_left);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                startActivity(new Intent(StatusActivity.this, SettingsActivity.class));
            }
        });

// to putting the status
        String status_value = getIntent().getStringExtra("status_value");

        mStatus = (TextInputLayout) findViewById(R.id.status_id);
        btn = (Button) findViewById(R.id.btn);

        mStatus.getEditText().setText(status_value);
        // btn click for changing status
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //progress

                mProgress = new ProgressDialog(StatusActivity.this);
              mProgress.setTitle("Saving Status");
              mProgress.setMessage("please wait while we save the changes");
              mProgress.show();

             String status = mStatus.getEditText().getText().toString();
             mStatusDatabase.child("status").setValue(status).addOnCompleteListener(new OnCompleteListener<Void>() {
                 @Override
                 public void onComplete(@NonNull Task<Void> task)
                 {
                   if(task.isSuccessful())
                   {
                       mProgress.dismiss();
                   }
                   else
                   {
                       Toast.makeText(getApplicationContext(),"There was some error in saving changes !", Toast.LENGTH_SHORT).show();
                   }
                 }
             });
            }
        });
    }
}
