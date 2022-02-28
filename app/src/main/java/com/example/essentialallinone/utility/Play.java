package com.example.essentialallinone.utility;
import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Play
{
    MediaPlayer mp;
    private static final int MY_PERMISSION_REQUEST_READ_EXTERNAL =1;

    public void reproducir(String path,Activity activity) {
        this.checkPermission(activity);
        try
        {
            if(mp == null)
            {
                playAudio(path);
            }
            else
            {
                destruir();
                playAudio(path);

            }
        }catch (Exception e)
        {
            e.getMessage();
        }


    }

    public void destruir() {
        if (mp != null) {
            mp.release();
            mp = null;
        }
    }

    public void playAudio(String path) {
        try {

            mp = new MediaPlayer();
            mp.setDataSource(path);
            mp.prepare();
            mp.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void checkPermission(Activity activity)
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

    public int getDuration()
    {
       return mp.getDuration();
    }
}
