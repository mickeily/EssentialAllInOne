package com.example.essentialallinone.controlador;

import android.app.Activity;

import com.example.essentialallinone.Data.Data;
import com.example.essentialallinone.Essential;
import com.example.essentialallinone.MainActivity;
import com.example.essentialallinone.activity.Termino;
import com.example.essentialallinone.utility.Const;
import com.example.essentialallinone.utility.Fecha;

import java.util.ArrayList;
import java.util.List;

public class Controlador
{

    private static List<Essential> listadoPrincipal = new ArrayList<>();
    private static List<Essential> listadoEspecifico;
    private static List<Termino> cuentos;
    private static MainActivity mainActivity = new MainActivity();
    private static int orden=0;


    private static void cargar(Activity activity) {
        listadoPrincipal = Data.readFile(activity, Const.URL_DATABASE);
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
            if(listadoEspecifico.size()>=Const.CANTIDADCARGAR)
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
            if(listadoEspecifico.size()>=Const.CANTIDADCARGAR)
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
            if(listadoEspecifico.size()>=Const.CANTIDADCARGAR)
            {
                break;
            }
        }
        return listadoEspecifico;
    }

    public static List<Essential> moduloRead(Activity activity)
    {
        listadoEspecifico= new ArrayList<>();

        if(listadoPrincipal.isEmpty())
        {
            cargar(activity);
        }
        for(Essential ess: listadoPrincipal)
        {
            if(ess.getStatusRead()==0)
            {
                listadoEspecifico.add(ess);
            }
            if(listadoEspecifico.size()>=Const.CANTIDADCARGAR)
            {
                break;
            }
        }
        return listadoEspecifico;


    }
    public static List<Essential> moduloListen(Activity activity)
    {
        listadoEspecifico= new ArrayList<>();

        if(listadoPrincipal.isEmpty())
        {
            cargar(activity);
        }
        for(Essential ess: listadoPrincipal)
        {
            if(ess.getStatusListen()==0)
            {
                listadoEspecifico.add(ess);
            }
            if(listadoEspecifico.size()>=Const.CANTIDADCARGAR)
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
            if(listadoEspecifico.size()>=Const.CANTIDADCARGAR)
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
            if(listadoEspecifico.size()>=Const.CANTIDADCARGAR)
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
            if(ess.getStatusActive()==0 && ess.getStatusHang() == 1)
            {
                listadoEspecifico.add(ess);
            }
            if(listadoEspecifico.size()>=Const.CANTIDADCARGAR)
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
                    if (Fecha.getHoras(ess.getDate()) >= Const.UNODIA && ess.getStatusActive() == 2) {
                        listadoEspecifico.add(ess);
                    }
                }
                break;
                case 1: {
                    if (Fecha.getHoras(ess.getDate()) >= Const.TRESDIAS&& ess.getStatusActive() == 2) {
                        listadoEspecifico.add(ess);
                    }
                }
                break;
                case 2: {
                    if (Fecha.getHoras(ess.getDate()) >= Const.CINCODIAS && ess.getStatusActive() == 2) {
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
                    if (Fecha.getHoras(ess.getDate()) >= Const.SIETEDIAS && ess.getStatusActive() == 2)
                    {
                        listadoEspecifico.add(ess);
                    }
                }
                break;
                default: {

                }
            }
            if(listadoEspecifico.size()>=Const.CANTIDADCARGAR)
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
            if(listadoEspecifico.size()>=Const.CANTIDADCARGAR)
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
        cuentos = Data.readFileCuento(activity,Const.URL_CUENTO);
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
