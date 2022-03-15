package com.example.essentialallinone.Data;

import android.app.Activity;
import android.widget.Toast;

import com.example.essentialallinone.Essential;
import com.example.essentialallinone.activity.Termino;
import com.example.essentialallinone.permission.Permission;
import com.example.essentialallinone.utility.Const;

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

    private static Essential essential ;
    private static List<Essential> listado;
    private static List<Termino> cuentos;


    public static List<Essential> readFile(Activity activity,String databasePath)
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
    public static List<Termino> readFileCuento(Activity activity, String cuentoPath)
    {
        Permission.checkReadPermission(activity);

        String archivoTemp[]= new String[200];
        cuentos = new ArrayList<>();
        try{
            FileReader fileReader = new FileReader(cuentoPath);
            BufferedReader bf = new BufferedReader(fileReader);
            String linea = "";
            while ((linea = bf.readLine()) !=null)
            {
                cuentos.add(new Termino(linea.split(Const.SEPARADOR)));
            }
        }catch (Exception e)
        {
            Toast.makeText(activity,e+"",Toast.LENGTH_LONG).show();
        }
        return cuentos;
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
                mow.append(ess.toString()+"\n");
            }
            mow.close();
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




