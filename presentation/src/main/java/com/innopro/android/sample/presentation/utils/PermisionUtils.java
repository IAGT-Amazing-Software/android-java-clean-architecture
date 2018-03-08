package com.innopro.android.sample.presentation.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

public class PermisionUtils {

    private static final int REQUEST= 0;

    private static String[] PERMISSIONS = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.CAMERA
    };

    public static boolean checkPermisions(Activity activity) {
        boolean permisiongranted = false;
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            getPermisions(activity);
        }else{
            permisiongranted = true;
        }
        return permisiongranted;
    }

    public static void getPermisions(Activity activity) {
        ActivityCompat.requestPermissions(activity, PERMISSIONS, REQUEST);
    }

}