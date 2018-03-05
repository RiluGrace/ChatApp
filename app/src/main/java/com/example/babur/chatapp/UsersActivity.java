package com.example.babur.chatapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.example.babur.chatapp.Models.Users;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class UsersActivity extends AppCompatActivity {


    private Toolbar mToolbar;
    private RecyclerView mUsersList;

    //firebase

    private DatabaseReference mUsersDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        mToolbar = (Toolbar)findViewById(R.id.mToolbar3);
        mToolbar.setTitle("Contacts");
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

                startActivity(new Intent(UsersActivity.this, SignInActivity.class));
            }
        });
// firebase
        mUsersDatabase =FirebaseDatabase.getInstance().getReference().child("Users");

        mUsersList =(RecyclerView)findViewById(R.id.userslist);
        mUsersList.setHasFixedSize(true);
        mUsersList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

//        FirebaseRecyclerAdapter<Users , UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Users, UsersViewHolder>
//                (Users.class , R.layout.users_single , UsersViewHolder.class , mUsersDatabase) {
//            @Override
//            protected void onBindViewHolder(@NonNull UsersViewHolder holder, int position, @NonNull Users model) {
//
//            }
//
//            @NonNull
//            @Override
//            public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                return null;
//            }
//        };
    }

    public  class UsersViewHolder extends RecyclerView.ViewHolder
    {
        View mView;

        public UsersViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
        }
    }
}
