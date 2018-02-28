package com.example.babur.chatapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity {

    EditText et_username, et_password;
    Button btn1;
    //firebase auth
    private FirebaseAuth mAuth;
    //progress dialog
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //firbase auth
        mAuth = FirebaseAuth.getInstance();
        // progress Dialog
        mProgress = new ProgressDialog(this);


        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn1 = (Button) findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = et_username.getText().toString();
                String password = et_password.getText().toString();

                if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)) {
                    mProgress.setTitle("Logging In");
                    mProgress.setMessage("Please wait while we check your credentials ");
                    mProgress.setCanceledOnTouchOutside(false);
                    mProgress.show();

                    loginuser(email, password);
                }

            }
        });
    }

    private void loginuser(String email, final String password) {

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    mProgress.dismiss();
                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    mProgress.hide();
                    if (!password.equals("")) {
                        boolean a = isValidPassword(password);
                        if (password.length() < 8 && !a) {
                            Toast.makeText(SignInActivity.this, "Password must contain min 8 character", Toast.LENGTH_SHORT).show();
                        }
                    }
                    Toast.makeText(SignInActivity.this, "Can't Sign in. Please check the form and try again.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void rclick(View s) {


        Intent i = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity(i);
    }

    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

}
   /* public void click(View r)
    {
        String u = et_username.getText().toString();
        String p = et_password.getText().toString();


        if (!u.equals(""))
        {
            if (!p.equals("")) {
                boolean a=isValidPassword(p);
                if (p.length() < 8 && !a ) {
                    Toast.makeText(this, "password must contain min 8 character", Toast.LENGTH_LONG).show();
                    System.out.println("Not Valid");
                } else {
                    System.out.println("Valid");
                    Intent i = new Intent(SignInActivity.this, MessageListActivity.class);
                    startActivity(i);
                }
            }

            else
            {
                Toast.makeText(this, "password can't be empty", Toast.LENGTH_LONG).show();
            }
        }
        else
        {

            Toast.makeText(this, "username can't be empty", Toast.LENGTH_LONG).show();
        }
    }
    */







