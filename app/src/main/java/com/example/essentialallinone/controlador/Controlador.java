package com.example.essentialallinone.controlador;

import android.app.Activity;

import com.example.essentialallinone.Data.Data;
import com.example.essentialallinone.Essential;

import java.util.ArrayList;
import java.util.List;

public class Controlador
{

    private static List<Essential> listadoPrincipal = new ArrayList<>();
    private static List<Essential> listadoEspecifico;

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
            if(listadoEspecifico.size()>=20)
            {
                break;
            }
        }
        return listadoEspecifico;
    }
}
