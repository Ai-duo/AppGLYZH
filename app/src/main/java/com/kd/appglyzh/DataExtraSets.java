package com.kd.appglyzh;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Typeface;

import androidx.databinding.BindingAdapter;

import java.util.Timer;
import java.util.TimerTask;

public class DataExtraSets {

    @BindingAdapter("setExtraTypeface")
    public static void setExtraTypeface(TextView textView,Typeface typeFace){
        Log.i("TAG","setExtraTypeface");
        if(textView!=null){
            textView.setTypeface(typeFace);
            textView.getPaint().setAntiAlias(false);
        }
    };
}
