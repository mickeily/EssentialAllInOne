package com.example.essentialallinone.Data;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.essentialallinone.Essential;
import com.example.essentialallinone.permission.Permission;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Data
{
    private static String databasePath = "/sdcard/DB/DB.csv";

    private  static Essential essential ;
    private  static List<Essential> listado;

    public static List<Essential> readFile(Activity activity)
    {
        Permission.checkReadPermission(activity);

        String archivoTemp[]= new String[30];
        listado = new ArrayList<>();
        try{
            FileReader fileReader = new FileReader(databasePath);
            BufferedReader bf = new BufferedReader(fileReader);
            String linea = "";
            while ((linea = bf.readLine()) !=null)
            {
                archivoTemp = linea.split(",");
                essential = new Essential(archivoTemp);
                listado.add(essential);
            }
        }catch (Exception e)
        {
            Toast.makeText(activity,e+"",Toast.LENGTH_LONG).show();
        }
        return listado;
    }
}




