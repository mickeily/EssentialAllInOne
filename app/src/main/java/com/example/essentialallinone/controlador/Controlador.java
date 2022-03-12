package com.example.essentialallinone.controlador;

import android.app.Activity;

import com.example.essentialallinone.Data.Data;
import com.example.essentialallinone.Essential;
import com.example.essentialallinone.MainActivity;
import com.example.essentialallinone.activity.Termino;
import com.example.essentialallinone.utility.Fecha;

import java.util.ArrayList;
import java.util.List;

public class Controlador
{

    private static List<Essential> listadoPrincipal = new ArrayList<>();
    private static List<Essential> listadoEspecifico;
    private static List<Termino> cuentos;
    private static MainActivity mainActivity = new MainActivity();
    private static String databasePath = "/sdcard/DB/DB.csv";
    private static String cuentoPath = "/sdcard/DB/Todas.csv";

    private static void cargar(Activity activity) {
        listadoPrincipal = Data.readFile(activity,databasePath);
    }

    public static List<Essential> moduloEjemplo(Activity activity)
    {
        listadoEspecifico= new ArrayList<>();
        if(listadoPrincipal.isEmpty())
        {
            cargar(activity);
        }

        for(Essential ess: listadoPrincipal)
        {
            if(ess.getStatusExample()==0)
            {
                listadoEspecifico.add(ess);
            }
            if(listadoEspecifico.size()>=MainActivity.CANTIDADCAGAR)
            {
                break;
            }
        }
        return listadoEspecifico;
    }
    public static List<Essential> moduloDefinition(Activity activity)
    {
        listadoEspecifico= new ArrayList<>();
        if(listadoPrincipal.isEmpty())
        {
            cargar(activity);
        }

        for(Essential ess: listadoPrincipal)
        {
            if(ess.getStatusDefinition()==0)
            {
                listadoEspecifico.add(ess);
            }
            if(listadoEspecifico.size()>=MainActivity.CANTIDADCAGAR)
            {
                break;
            }
        }
        return listadoEspecifico;
    }
    public static List<Essential> moduloMultiChoise(Activity activity)
    {
        listadoEspecifico= new ArrayList<>();
        if(listadoPrincipal.isEmpty())
        {
            cargar(activity);
        }

        for(Essential ess: listadoPrincipal)
        {
            if(ess.getStatusMultiChoise()==0)
            {
                listadoEspecifico.add(ess);
            }
            if(listadoEspecifico.size()>=MainActivity.CANTIDADCAGAR)
            {
                break;
            }
        }
        return listadoEspecifico;
    }

    public static List<Essential> moduloReadAndListen(Activity activity, int objetivo)
    {
        listadoEspecifico= new ArrayList<>();

        if(listadoPrincipal.isEmpty())
        {
            cargar(activity);
        }
        if(objetivo==7)
        {
            for(Essential ess: listadoPrincipal)
            {
                if(ess.getStatusRead()==0)
                {
                    listadoEspecifico.add(ess);
                }
                if(listadoEspecifico.size()>=MainActivity.CANTIDADCAGAR)
                {
                    break;
                }
            }
        }
        else
        {
            for(Essential ess: listadoPrincipal)
            {
                if(ess.getStatusListen()==0)
                {
                    listadoEspecifico.add(ess);
                }
                if(listadoEspecifico.size()>=3)
                {
                    break;
                }
            }
        }

        for(Essential ess: listadoPrincipal)
        {
            if(ess.getStatusMultiChoise()==0)
            {
                listadoEspecifico.add(ess);
            }
            if(listadoEspecifico.size()>=3)
            {
                break;
            }
        }
        return listadoEspecifico;
    }

    public static List<Essential> moduloMatch(Activity activity)
    {
        listadoEspecifico= new ArrayList<>();

        if(listadoPrincipal.isEmpty())
        {
            cargar(activity);
        }

        for(Essential ess: listadoPrincipal)
        {
            if(ess.getStatusMatch()==0)
            {
                listadoEspecifico.add(ess);
            }
            if(listadoEspecifico.size()>=MainActivity.CANTIDADCAGAR)
            {
                break;
            }
        }
        return listadoEspecifico;
    }
    public static List<Essential> moduloHang(Activity activity)
    {
        listadoEspecifico= new ArrayList<>();

        if(listadoPrincipal.isEmpty())
        {
            cargar(activity);
        }

        for(Essential ess: listadoPrincipal)
        {
            if(ess.getStatusHang()==0)
            {
                listadoEspecifico.add(ess);
            }
            if(listadoEspecifico.size()>=MainActivity.CANTIDADCAGAR)
            {
                break;
            }
        }
        return listadoEspecifico;
    }
    public static List<Essential> moduloActive(Activity activity)
    {
        listadoEspecifico= new ArrayList<>();

        if(listadoPrincipal.isEmpty())
        {
            cargar(activity);
        }

        for(Essential ess: listadoPrincipal)
        {
            if(ess.getStatusActive()==0)
            {
                listadoEspecifico.add(ess);
            }
            if(listadoEspecifico.size()>=MainActivity.CANTIDADCAGAR)
            {
                break;
            }
        }
        return listadoEspecifico;
    }

    public static List<Essential> moduloCardinal(Activity activity)
    {
        listadoEspecifico= new ArrayList<>();

        if(listadoPrincipal.isEmpty())
        {
            cargar(activity);
        }

        for(Essential ess: listadoPrincipal)
        {
            switch (ess.getPointPrincipal())
            {
                case 0: {
                    if (Fecha.getHoras(ess.getDate()) > 1 && ess.getStatusActive() == 2) {
                        listadoEspecifico.add(ess);
                    }
                }
                break;
                case 1: {
                    if (Fecha.getHoras(ess.getDate()) > 72 && ess.getStatusActive() == 2) {
                        listadoEspecifico.add(ess);
                    }
                }
                break;
                case 2: {
                    if (Fecha.getHoras(ess.getDate()) > 120 && ess.getStatusActive() == 2) {
                        listadoEspecifico.add(ess);
                    }
                }
                break;
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                {
                    if (Fecha.getHoras(ess.getDate()) > 168 && ess.getStatusActive() == 2)
                    {
                        listadoEspecifico.add(ess);
                    }
                }
                break;
                default: {

                }
            }
            if(listadoEspecifico.size()>=MainActivity.CANTIDADCAGAR)
            {
                break;
            }
        }

        return listadoEspecifico;
    }

    public static List<Essential> moduloComplete(Activity activity)
    {
        listadoEspecifico= new ArrayList<>();

        if(listadoPrincipal.isEmpty())
        {
            cargar(activity);
        }

        for(Essential ess: listadoPrincipal)
        {
            if(ess.getStatusComplete()==0)
            {
                listadoEspecifico.add(ess);
            }
            if(listadoEspecifico.size()>=MainActivity.CANTIDADCAGAR)
            {
                break;
            }
        }
        return listadoEspecifico;
    }
    public static List<Essential> getListadoPrincipal( Activity activity)
    {
        if(listadoPrincipal.isEmpty())
        {
            cargar(activity);
        }

        return  listadoPrincipal;

    }

    public static List<Termino> cargarCuentos(Activity activity, String libro, String unidad)
    {
        cuentos = new ArrayList<>();
        List<Termino> lista = new ArrayList<>();
        cuentos = Data.readFileCuento(activity,cuentoPath);
        for(Termino c: cuentos)
        {
            if(c.getLibro().equals(libro) && c.getUnidad().equals(unidad))
            {
                lista.add(c);
            }
        }
        return  lista;

    }



}
