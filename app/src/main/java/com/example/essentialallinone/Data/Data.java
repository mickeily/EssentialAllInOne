package com.example.essentialallinone.Data;

import android.app.Activity;
import android.content.res.Resources;
import android.widget.Toast;

import com.example.essentialallinone.Essential;
import com.example.essentialallinone.R;
import com.example.essentialallinone.activity.Contenido;
import com.example.essentialallinone.activity.PalabraConjugada;
import com.example.essentialallinone.activity.Termino;
import com.example.essentialallinone.permission.Permission;
import com.example.essentialallinone.utility.Const;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Data
{

    private static Essential essential ;
    private static List<Essential> listado;
    private static List<Termino> cuentos;
    private static Permission permission = new Permission();


    public static List<Essential> readFile(Activity activity)
    {
        List<String> dbNoStatic= new ArrayList<>(); // este archivo no cambiara ninguno de sus valores
        List<String> dbStatic= new ArrayList<>();// este sera el archivo que cambiara sus valores

        String databasePath = Const.URL_DATABASE;

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
            Resources resources = activity.getResources();
            InputStream is = resources.openRawResource(R.raw.cuento);
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bf =  new BufferedReader(inputStreamReader);
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

    public static List<Essential> leerArchivo(Activity activity)
    {
        String[] cadena;
        List<Essential> lista =  new ArrayList<>();
        try {
            Resources resources = activity.getResources();
            InputStream is = resources.openRawResource(R.raw.db);
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bf =  new BufferedReader(inputStreamReader);
            String linea = "";

            while ((linea = bf.readLine()) !=null)
            {
                cadena = linea.split(",");
                lista.add(new Essential(cadena));
            }

        }catch (Exception e)
        {
            Toast.makeText(activity, ""+e, Toast.LENGTH_SHORT).show();

        }

        return lista;
    }

    public static List<PalabraConjugada> readFilePalabrasConjugadas(Activity activity)
    {
        List<PalabraConjugada> listado = new ArrayList<>();
        String[] arr;

        try{
            Resources resources = activity.getResources();
            InputStream is = resources.openRawResource(R.raw.palabrasconjuganas);
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bf =  new BufferedReader(inputStreamReader);
            String linea = "";
            while ((linea = bf.readLine()) !=null)
            {
               arr= linea.split(",");
               listado.add(new PalabraConjugada(arr));
            }
        }catch (Exception e)
        {
            Toast.makeText(activity,e+"",Toast.LENGTH_LONG).show();
        }
        return listado;
    }

    public static List<Contenido> createDataBase(Activity activity)
    {
        List<Contenido> listaContenido = new ArrayList<>();
        List<String[]> dbStatic= new ArrayList<>();
        List<String[]> dbNoStatic= new ArrayList<>();
        dbStatic = readStaticFile(activity);
        dbNoStatic = readNoStaticFile(activity);

        for(int i=0; i<dbStatic.size();i++)
        {
            listaContenido.add(new Contenido(dbStatic.get(i),dbNoStatic.get(i)));
        }

        int a =0;
        return listaContenido;
    }

    private static List<String[]> readStaticFile(Activity activity)
    {
        List<String[]> dbStatic= new ArrayList<>();
        String archivoTemp[];

        try{
            Resources resources = activity.getResources();
            InputStream is = resources.openRawResource(R.raw.bdestatica);
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bf =  new BufferedReader(inputStreamReader);
            String linea = "";
            while ((linea = bf.readLine()) !=null)
            {
                archivoTemp = linea.split(",");
                dbStatic.add(archivoTemp);
            }
            int a =0;
        }catch (Exception e)
        {
            Toast.makeText(activity,e+"",Toast.LENGTH_LONG).show();
        }
        return dbStatic;


    }

    private static List<String[]> readNoStaticFile(Activity activity)
    {
        List<String[]> dbNoStatic= new ArrayList<>();// este sera el archivo que cambiara sus valores
        String databasePath = Const.URL_DATABASE;
        String archivoTemp[];

        try{
            FileReader fileReader = new FileReader(databasePath);
            BufferedReader bf = new BufferedReader(fileReader);
            String linea = "";
            while ((linea = bf.readLine()) !=null)
            {
                archivoTemp = linea.split(",");
                dbNoStatic.add(archivoTemp);
            }
        }catch (Exception e)
        {
            Toast.makeText(activity,e+"",Toast.LENGTH_LONG).show();
        }
        return dbNoStatic;

    }

    public static void saveDatabase(List<Contenido> lita ,String path, Activity activity)
    {
        Permission.checkPermissionWrite(activity);
        File datos = new File(path);
        try {
            datos.createNewFile();
            FileOutputStream fout = new FileOutputStream(datos);
            OutputStreamWriter mow = new OutputStreamWriter(fout);

            for(Contenido cont: lita)
            {
                mow.append(cont.toString()+"\n");
            }
            mow.close();
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




