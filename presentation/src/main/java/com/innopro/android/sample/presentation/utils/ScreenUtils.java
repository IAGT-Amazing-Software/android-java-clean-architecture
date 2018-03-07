package com.innopro.android.sample.presentation.utils;


import android.app.Activity;
import android.graphics.Point;
import android.view.Display;

public class ScreenUtils {

    public static int getHeightScreen(Activity activity){
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;
        return height;
    }
    public static int getWidthScreen(Activity activity){
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        return width;
    }
}
