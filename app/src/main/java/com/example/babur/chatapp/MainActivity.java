package com.example.babur.chatapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.babur.chatapp.Fragments.ChatFragment;
import com.example.babur.chatapp.Fragments.FriendsFragment;
import com.example.babur.chatapp.Fragments.RequestFragment;
import com.example.babur.chatapp.Models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    //firebase
    private FirebaseAuth mAuth;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private int[] tabIcons = {
            R.drawable.circle,
            R.drawable.circle,
            R.drawable.circle
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        // firebase
        mAuth = FirebaseAuth.getInstance();

        mViewPager = (ViewPager)findViewById(R.id.viewpager) ;
        setupViewPager(mViewPager);
        mTabLayout = (TabLayout)findViewById(R.id.tab);
        mTabLayout.setupWithViewPager(mViewPager);
        setupTabIcons();
    }

    //no need of title in fragments.
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new RequestFragment(),"");
        adapter.addFragment(new ChatFragment(),"");
        adapter.addFragment(new FriendsFragment(),"");
        viewPager.setAdapter(adapter);
    }

    //set icons on its own positions
    private void setupTabIcons() {
        mTabLayout.getTabAt(0).setIcon(tabIcons[0]);
        mTabLayout.getTabAt(1).setIcon(tabIcons[1]);
        mTabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    // Adapter for the viewpager using FragmentPagerAdapter
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null)
        {
           sendToStart();
        }
    }

    private void sendToStart()
    {
        Intent intent=new Intent(MainActivity.this,SignInActivity.class);
        startActivity(intent);
        finish();
    }

    // to fix menu bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu ,menu);
        return true;
    }

// menu icon
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId()== R.id.log_out_btn)
         {
             sendToStart();
            FirebaseAuth.getInstance().signOut();
         }

        if(item.getItemId()== R.id.acc_setting)
        {
            Intent intent = new Intent(MainActivity.this , SettingsActivity.class);
            startActivity(intent);
        }

        if(item.getItemId()== R.id.all_users)
        {
            Intent intent = new Intent(MainActivity.this , UsersActivity.class);
            startActivity(intent);
        }
        return true;
    }
}
