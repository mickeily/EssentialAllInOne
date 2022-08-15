package com.example.NewAllinOne.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public  class Permission
{
    private static final int MY_PERMISSION_REQUEST_READ_EXTERNAL =1;
    private static final int MY_PERMISSION_REQUEST_WRITE_EXTERNAL =1;

    public static void checkReadPermission(Activity activity)
    {
        if(ContextCompat.checkSelfPermission(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(activity,Manifest.permission.READ_EXTERNAL_STORAGE))
            {

            }
            else
            {
                ActivityCompat.requestPermissions(activity,new String[]
                        {Manifest.permission.READ_EXTERNAL_STORAGE},MY_PERMISSION_REQUEST_READ_EXTERNAL);
            }
        }
    }

    public static void checkPermissionWrite(Activity activity)
    {
        if(ContextCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(activity,Manifest.permission.WRITE_EXTERNAL_STORAGE))
            {

            }
            else
            {
                ActivityCompat.requestPermissions(activity,new String[]
                        {Manifest.permission.WRITE_EXTERNAL_STORAGE},MY_PERMISSION_REQUEST_WRITE_EXTERNAL);
            }
        }

    }



}
