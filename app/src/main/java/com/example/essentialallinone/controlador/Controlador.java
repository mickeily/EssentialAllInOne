package com.example.essentialallinone.controlador;

import android.app.Activity;

import com.example.essentialallinone.Data.Data;
import com.example.essentialallinone.Essential;
import com.example.essentialallinone.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class Controlador
{

    private static List<Essential> listadoPrincipal = new ArrayList<>();
    private static List<Essential> listadoEspecifico;
    private static MainActivity mainActivity = new MainActivity();

    private static void cargar(Activity activity) {
        listadoPrincipal = Data.readFile(activity);
    }

    public static List<Essential> ModuloEjemplo(Activity activity)
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
            if(listadoEspecifico.size()>=3)
            {
                break;
            }
        }
        return listadoEspecifico;
    }
    public static List<Essential> ModuloDefinition(Activity activity)
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
            if(listadoEspecifico.size()>=3)
            {
                break;
            }
        }
        return listadoEspecifico;
    }
    public static List<Essential> ModuloMultiChoise(Activity activity)
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
            if(listadoEspecifico.size()>=3)
            {
                break;
            }
        }
        return listadoEspecifico;
    }

    public static List<Essential> ModuloReadAndListen(Activity activity, int objetivo)
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
                if(listadoEspecifico.size()>=3)
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

    public static List<Essential> getListadoPrincipal( Activity activity)
    {
        if(listadoPrincipal.isEmpty())
        {
            cargar(activity);
        }

        return  listadoPrincipal;

    }
}
