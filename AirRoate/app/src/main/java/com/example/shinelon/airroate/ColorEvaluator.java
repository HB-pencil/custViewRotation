package com.example.shinelon.airroate;

import android.animation.TypeEvaluator;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Shinelon on 2017/11/25.
 */

public class ColorEvaluator implements TypeEvaluator<String>{

    @Override
    public String evaluate(float fraction, String startValue, String endValue) {
        String sRed = startValue.substring(1,3);
        String sGreen = startValue.substring(3,5);
        String sBlue = startValue.substring(5,7);
        String eRed = endValue.substring(1,3);
        String eGreen = endValue.substring(3,5);
        String eBlue = endValue.substring(5,7);
        Log.e("动画值打印原始rgb", sRed + " " + sGreen + " " + sBlue + " "
        + eRed + " " + eGreen + " " +eBlue );

        int r =  Integer.parseInt(eRed,16) - Integer.parseInt(sRed,16);
        int g = Integer.parseInt(eGreen,16) - Integer.parseInt(sGreen,16);
        int b = Integer.parseInt(eBlue,16) - Integer.parseInt(sBlue,16);
        Log.e("动画值打印差值rgb", "evaluate: "+ r + "  " + g + "  " + b);

        int currentR =  (int)(fraction*r + Integer.parseInt(sRed,16));
        int currentG = (int)(fraction*g + Integer.parseInt(sGreen,16));
        int currentB = (int)(fraction*b + Integer.parseInt(sBlue,16));
        Log.e("动画值打印currentB", currentB+"");

        String rRed = getHexString(currentR);
        String rGreen = getHexString(currentG);
        String rBlue = getHexString(currentB);



        Log.e("动画值打印RGB", "evaluate: "+ rRed + "  " + rGreen + "  " + rBlue + " " +fraction);
        return "#"+rRed+rGreen+rBlue;
    }

    public String getHexString(int value){
         String string = Integer.toHexString(value);
         if (string.length() == 1){
             string = "0"+string;
         }
         return string;
    }
}
