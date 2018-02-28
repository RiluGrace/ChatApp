package com.example.babur.chatapp;

import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity
{
    EditText et_name,et_numb,et_username,et_password;
    Button btn1;
   //firebase auth
    private FirebaseAuth mAuth;
    //progress dialog
    private ProgressDialog mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //firbase auth
        mAuth = FirebaseAuth.getInstance();
        // progress Dialog
        mProgress = new ProgressDialog(this);

        //android part
        et_name=findViewById(R.id.et_name);
        et_username=findViewById(R.id.et_username);
        et_password=findViewById(R.id.et_password);
        et_numb=findViewById(R.id.et_numb);
        btn1=(Button)findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                String name=et_name.getText().toString();
                String email=et_username.getText().toString();
                String password=et_password.getText().toString();
                String phno=et_numb.getText().toString();

                 if(!TextUtils.isEmpty(name)|| !TextUtils.isEmpty(email)|| !TextUtils.isEmpty(password)|| !TextUtils.isEmpty(phno))
                 {
                     mProgress.setTitle("Registering User");
                     mProgress.setMessage("Please wait while we create your account");
                     mProgress.setCanceledOnTouchOutside(false);
                     mProgress.show();

                     registeruser(name,email,password,phno);
                 }

            }
        });
    }

    private void registeruser(String name , String email , String password , String phno)
    {

       mAuth.createUserWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful())
               {

                   mProgress.dismiss();
                   Intent intent = new Intent(SignUpActivity.this , SignInActivity.class);
                   startActivity(intent);
                   finish();
               }
               else
               {
                   mProgress.hide();
                   Toast.makeText(SignUpActivity.this ,"Can't Sign in" ,Toast.LENGTH_SHORT);
               }
           }
       });
    }
}

