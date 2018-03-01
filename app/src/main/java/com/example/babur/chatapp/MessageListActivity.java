package com.example.babur.chatapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.babur.chatapp.Models.Message;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import static android.view.View.GONE;

public class MessageListActivity extends AppCompatActivity
{

  //  private FirebaseAnalytics mFirebaseAnalytics;
 RecyclerView mMessageRecycler;
 MessageListAdapter mMessageAdapter;
 private List <Message> mMessageList;
 Message message;
 EditText edittext_chatbox;
 ImageView button_chatbox_send;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);

       // Obtain the FirebaseAnalytics instance.
      //  mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        message=new Message();
        mMessageList=new ArrayList<>();
        mMessageRecycler = (RecyclerView) findViewById(R.id.reyclerview_message_list);
        edittext_chatbox=(EditText)findViewById(R.id.edittext_chatbox);
        button_chatbox_send=(ImageView) findViewById(R.id.button_chatbox_send);

        mMessageAdapter = new MessageListAdapter(this, mMessageList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mMessageRecycler.setLayoutManager( mLayoutManager);
        mMessageRecycler.setAdapter(mMessageAdapter);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        // toolbar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.mToolbar);
        mToolbar.setTitle("");
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

                startActivity(new Intent(MessageListActivity.this, SignInActivity.class));
            }
        });
    }

    //send button click for chat
    public void send(View view)
    {
            long time= System.currentTimeMillis();
           // getting text from chatbox and set it to setMessage
            String m = edittext_chatbox.getText().toString();
            //  message.setMessage(edittext_chatbox.getText().toString());
            mMessageList.add(0, new Message(m, true, time));
            //duplicating the same msg on receiver side
            mMessageList.add(0, new Message(m, false, time));
            mMessageAdapter.notifyDataSetChanged();
            edittext_chatbox.setText("");


    }
}
