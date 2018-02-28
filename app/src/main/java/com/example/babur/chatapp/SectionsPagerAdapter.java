package com.example.babur.chatapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.babur.chatapp.Fragments.ChatFragment;
import com.example.babur.chatapp.Fragments.FriendsFragment;
import com.example.babur.chatapp.Fragments.RequestFragment;

/**
 * Created by babur on 28-02-2018.
 */

class SectionsPagerAdapter extends FragmentPagerAdapter
{

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
                RequestFragment requestFragment = new RequestFragment();
                return  requestFragment;

            case 1:
                ChatFragment chatFragment = new ChatFragment();
                return  chatFragment;

            case 2:
                FriendsFragment friendsFragment = new FriendsFragment();
                return  friendsFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

//    public CharSequence getPageTitle(int position)
//    {
//        switch (position)
//        {
//            case 0:
//                return "REQUESTS";
//            case 1:
//                return "CHATS";
//            case 2:
//                return "FRIENDS";
//            default:
//                return null;
//        }
//    }
}
