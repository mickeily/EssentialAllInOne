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
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Data
{
    private static String databasePath = "/sdcard/DB/DB.csv";

    private  static Essential essential ;
    private  static List<Essential> listado;
    private static String cadena ="";

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

    public static void saveFile(List<Essential> lita ,String path, Activity activity)
    {
        Permission.checkPermissionWrite(activity);
        File datos = new File(path);
        try {
            datos.createNewFile();
            FileOutputStream fout = new FileOutputStream(datos);
            OutputStreamWriter mow = new OutputStreamWriter(fout);

            for(Essential ess: lita)
            {
                mow.append(concatener(ess)+"\n");
            }
            mow.close();
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String concatener(Essential ess)
    {
        cadena = ess.getOrder()+","+
                 ess.getStatusExample()+","+
                 ess.getStatusDefinition()+","+
                 ess.getStatusMultiChoise()+","+
                 ess.getStatusRead()+","+
                 ess.getStatusListen()+","+
                 ess.getStatusMatch()+","+
                 ess.getStatusHang()+","+
                 ess.getStatusActive()+","+
                 ess.getStatusPrincipal()+","+
                 ess.getPointPrincipal()+","+
                 ess.getDate()+","+
                 ess.getExample()+","+
                 ess.getMeaning()+","+
                 ess.getBook()+","+
                 ess.getUnit()+","+
                 ess.getWord()+","+
                 ess.getType();
        return cadena;
    }
}




