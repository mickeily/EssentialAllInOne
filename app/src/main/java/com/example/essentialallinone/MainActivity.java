package com.example.essentialallinone;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import com.example.essentialallinone.activity.Activate;
import com.example.essentialallinone.activity.Cardinal;
import com.example.essentialallinone.activity.Complete;
import com.example.essentialallinone.activity.Info;
import com.example.essentialallinone.activity.Termino;
import com.example.essentialallinone.activity.Definition;
import com.example.essentialallinone.activity.Example;
import com.example.essentialallinone.activity.Hanged;
import com.example.essentialallinone.activity.ListeningAndReading;
import com.example.essentialallinone.activity.Match;
import com.example.essentialallinone.activity.MultiChoise;
import com.example.essentialallinone.utility.Selector;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static int objetivo=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        takePermission();
        //metodoDirecto();
        //prueba(new View(this));
        //complete();

    }
    public void metodoDirecto()
    {
        Intent activate = new Intent(this, Activate.class);
        startActivity(activate);

    }
    public void estadistic(View view)
    {
        Intent info = new Intent(this, Info.class);
        startActivity(info);
    }
    public void iniciar(View ve)
    {
        objetivo = Selector.examinar(this);
        switch (objetivo)
        {
            case 0:
            case 9:
            {
                Intent example = new Intent(this, Example.class);
                startActivity(example);
                break;
            }
            case 8:
            {
                Intent complete = new Intent(this, Complete.class);
                startActivity(complete);
                break;
            }
            case 7:
            {
                Intent multichoise = new Intent(this, MultiChoise.class);
                startActivity(multichoise);
                break;
            }
            case 5:
            case 6:
            {
                Intent listeningAndReading = new Intent(this, ListeningAndReading.class);
                startActivity(listeningAndReading);
                break;
            }
            case 4:
            {
                Intent match = new Intent(this, Match.class);
                startActivity(match);
                break;
            }
            case 3:
            {
                Intent hanged = new Intent(this, Hanged.class);
                startActivity(hanged);
                break;
            }

            case 2:
            {
                Intent activate = new Intent(this, Activate.class);
                startActivity(activate);
                break;
            }
            case 1:
            {
                Intent cardinal = new Intent(this, Cardinal.class);
                startActivity(cardinal);
                break;
            }
            default:
            {

            }
        }
    }

    public  static int getObjetivo()
    {
        return objetivo;
    }

    public void takePermissions()
    {
        if (isPermissionsGranted())
        {
            //Toast.makeText(this,"Permission Already Granted",Toast.LENGTH_LONG).show();
        }
        else
        {
            takePermission();

        }
    }

    private boolean isPermissionsGranted()
    {
        if(Build.VERSION.SDK_INT==Build.VERSION_CODES.R)
        {
            return Environment.isExternalStorageManager();
        }
        else
        {
            int readExternalStoragePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            return  readExternalStoragePermission== PackageManager.PERMISSION_GRANTED;
        }
    }

    private void takePermission()
    {
        if(Build.VERSION.SDK_INT==Build.VERSION_CODES.R)
        {
            try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(String.format("package:%s",getApplicationContext().getOpPackageName())));
                startActivityForResult(intent,100);
            }catch (Exception e)
            {
                Intent intent = new Intent();
                intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivityForResult(intent,100);
            }
        }else
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},101);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode== RESULT_OK)
        {
            if(requestCode==100)
            {
                if(Build.VERSION.SDK_INT==Build.VERSION_CODES.R)
                {
                    if(Environment.isExternalStorageManager())
                    {
                        //Toast.makeText(this,"Permission Granted in android 11",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        takePermission();
                    }
                }
            }
        }
    }

    @Override public void onRequestPermissionsResult(int requestCode, @Nullable String[] permission, @Nullable int[] grantedResults)
    {
        super.onRequestPermissionsResult(requestCode,permission,grantedResults);
        if(grantedResults.length>0)
        {
            if (requestCode==101)
            {
                boolean readExternalStorage = grantedResults[0] == PackageManager.PERMISSION_GRANTED;
                if(readExternalStorage)
                {
                    //Toast.makeText(this,"Read Permission is Granted in android 10 or below",Toast.LENGTH_LONG).show();
                }
                else
                {
                    takePermission();
                }
            }

        }

    }





}