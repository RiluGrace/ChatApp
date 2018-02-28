package com.example.babur.chatapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
{

    private FirebaseAuth mAuth;
    private ViewPager mViewPager;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mViewPager = (ViewPager)findViewById(R.id.viewpager) ;
        mTabLayout = (TabLayout)findViewById(R.id.tab);
        mTabLayout.setupWithViewPager(mViewPager);

//            mTabLayout.getTabAt(0).setIcon(R.drawable.circle);
//            mTabLayout.getTabAt(1).setIcon(R.drawable.circle);
//            mTabLayout.getTabAt(2).setIcon(R.drawable.circle);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(mSectionsPagerAdapter);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.mToolbar0);
        mToolbar.setTitle("Chat App");
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

                startActivity(new Intent(MainActivity.this, MessageListActivity.class));
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null)
        {
            Intent intent=new Intent(MainActivity.this,MessageListActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
