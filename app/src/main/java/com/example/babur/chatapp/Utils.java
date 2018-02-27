package com.example.babur.chatapp;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;
import com.squareup.picasso.Picasso;
/**
 * Created by babur on 20-02-2018.
 */

public class Utils
{

     public static String formatDateTime(long currenttime,Context mcontext)

     {
     Date date = new Date(currenttime);
     DateFormat dateFormat = android.text.format.DateFormat.getTimeFormat(mcontext);
     return dateFormat.format(date);
     }


}
