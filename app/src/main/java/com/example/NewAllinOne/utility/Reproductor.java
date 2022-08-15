package com.example.NewAllinOne.utility;
import android.app.Activity;

import com.example.NewAllinOne.permission.Permission;

public class Reproductor
{
    private static android.media.MediaPlayer mp;
    private  static String path;

   static public void reproducir(String word,Activity activity) {
       Permission.checkReadPermission(activity);
       path = Const.URL_AUDIOS+word;
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

    private static void destruir() {
        if (mp != null) {
            mp.release();
            mp = null;
        }
    }

    private static void playAudio(String path) {
        try {

            mp = new android.media.MediaPlayer();
            mp.setDataSource(path);
            mp.prepare();
            mp.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
