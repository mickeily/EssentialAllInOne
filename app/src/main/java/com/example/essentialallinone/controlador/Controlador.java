package com.example.essentialallinone.controlador;

import android.app.Activity;
import android.database.Cursor;
import android.widget.Toast;

import com.example.essentialallinone.Data.Data;
import com.example.essentialallinone.Data.DataManager;
import com.example.essentialallinone.Essential;
import com.example.essentialallinone.MainActivity;
import com.example.essentialallinone.activity.Contenido;
import com.example.essentialallinone.activity.Termino;
import com.example.essentialallinone.utility.Const;
import com.example.essentialallinone.utility.Fecha;

import java.util.ArrayList;
import java.util.List;

public class Controlador
{

    private static List<Contenido> database;
    private static List<Contenido> filteredDatabase;

    private static List<Essential> listadoPrincipal = new ArrayList<>();
    private static List<Essential> listadoEspecifico;
    private static List<Essential> listadoTemporal;
    private static List<Termino> cuentos;
    private static String[] lista;
    private static DataManager dataManager;
    private static int orden=0;

    private static void setDataBase(Activity activity)
    {
        database = new ArrayList<>();
        database = Data.createDataBase(activity);
    }

    public static List<Contenido> getDatabase(Activity activity)
    {
        setDataBase(activity);
        return database;
    }

    private static void cargar(Activity activity)
    {
        listadoPrincipal = Data.readFile(activity);
    }

    public static List<Essential> moduloEjemplo(Activity activity)
    {
        listadoEspecifico= new ArrayList<>();
        cargar(activity);

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
        cargar(activity);
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
        cargar(activity);
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
        cargar(activity);
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

        cargar(activity);
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

        cargar(activity);
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
        cargar(activity);
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

        cargar(activity);
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

        cargar(activity);

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
                case 2:
                case 3:
                case 4:
                {
                    if (Fecha.getHoras(ess.getDate()) >= Const.CINCODIAS && ess.getStatusActive() == 2) {
                        listadoEspecifico.add(ess);
                    }
                }
                break;
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
                case 8:
                case 9:
                case 10:
                {
                    if (Fecha.getHoras(ess.getDate()) >= Const.CATORCEDIAS && ess.getStatusActive() == 2)
                    {
                        listadoEspecifico.add(ess);
                    }
                }
                break;
                case 11:
                case 12:
                case 13:
                {
                    if (Fecha.getHoras(ess.getDate()) >= Const.VEINTIOCHODIAS && ess.getStatusActive() == 2)
                    {
                        listadoEspecifico.add(ess);
                    }
                }
                break;
                case 14:
                case 15:
                case 16:
                {
                    if (Fecha.getHoras(ess.getDate()) >= Const.CINCUENTAYSEISDIAS && ess.getStatusActive() == 2)
                    {
                        listadoEspecifico.add(ess);
                    }
                }
                break;
                case 17:

                {
                    if (Fecha.getHoras(ess.getDate()) >= Const.CIENTODOCEDIAS && ess.getStatusActive() == 2)
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
        cargar(activity);
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
        cargar(activity);
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

    public static List<Essential> ensamblarEssential(Cursor c)
    {
        listadoTemporal = new ArrayList<>();
        lista = new String[18];

        try{
            while (c.moveToNext())
            {
                lista[0]= c.getString(0);
                lista[1]= c.getString(1);
                lista[2]= c.getString(2);
                lista[3]= c.getString(3);
                lista[4]= c.getString(4);
                lista[5]= c.getString(5);
                lista[6]= c.getString(6);
                lista[7]= c.getString(7);
                lista[8]= c.getString(8);
                lista[9]= c.getString(9);
                lista[10]= c.getString(10);
                lista[11]= c.getString(11);
                lista[12]= c.getString(12);
                lista[13]= c.getString(13);
                lista[14]= c.getString(14);
                lista[15]= c.getString(15);
                lista[16]= c.getString(16);
                lista[17]= c.getString(17);

                listadoTemporal.add(new Essential(lista));
            }
        } catch (Exception e)
        {

        }
        int a =0;
        return  listadoTemporal;
    }

    public static void setFilteredDataBase(int status, Activity activity)
    {
        int a =0;
        filteredDatabase = new ArrayList<>();
        if(database==null)
        {
            setDataBase(activity);
        }
        if(status==9)
        {
            for(Contenido ess: database)
            {
                switch (ess.getPointPrincipal())
                {
                    case 0: {
                        if (Fecha.getHoras(ess.getDate()) >= Const.UNODIA && ess.getStatus()==9)
                        {
                            filteredDatabase.add(ess);
                        }
                    }
                    break;
                    case 1: {
                        if (Fecha.getHoras(ess.getDate()) >= Const.TRESDIAS && ess.getStatus()==9) {
                            filteredDatabase.add(ess);
                        }
                    }
                    break;
                    case 2:
                    case 3:
                    case 4:
                    {
                        if (Fecha.getHoras(ess.getDate()) >= Const.CINCODIAS && ess.getStatus()==9) {
                            filteredDatabase.add(ess);
                        }
                    }
                    break;
                    case 5:
                    case 6:
                    case 7:
                    {
                        if (Fecha.getHoras(ess.getDate()) >= Const.SIETEDIAS && ess.getStatus()==9)
                        {
                            filteredDatabase.add(ess);
                        }
                    }
                    break;
                    case 8:
                    case 9:
                    case 10:
                    {
                        if (Fecha.getHoras(ess.getDate()) >= Const.CATORCEDIAS && ess.getStatus()==9)
                        {
                            filteredDatabase.add(ess);
                        }
                    }
                    break;
                    case 11:
                    case 12:
                    case 13:
                    {
                        if (Fecha.getHoras(ess.getDate()) >= Const.VEINTIOCHODIAS && ess.getStatus()==9)
                        {
                            filteredDatabase.add(ess);
                        }
                    }
                    break;
                    case 14:
                    case 15:
                    case 16:
                    {
                        if (Fecha.getHoras(ess.getDate()) >= Const.CINCUENTAYSEISDIAS && ess.getStatus()==9)
                        {
                            filteredDatabase.add(ess);
                        }
                    }
                    break;
                    case 17:

                    {
                        if (Fecha.getHoras(ess.getDate()) >= Const.CIENTODOCEDIAS && ess.getStatus()==9 )
                        {
                            filteredDatabase.add(ess);
                        }
                    }
                    break;
                    default: {

                    }
                }
                if(filteredDatabase.size()>=Const.CANTIDADCARGAR&& ess.getStatus()==9)
                {
                    break;
                }
            }
        }
        else
        {
            for (Contenido cont: database)
            {
                if(cont.getStatus()==status)
                {
                    filteredDatabase.add(cont);
                }
                if(filteredDatabase.size()>=Const.CANTIDADCARGAR)
                {
                    break;
                }
            }
        }

    }

    public static List<Contenido> getFilteredDatabase()
    {
        return filteredDatabase;
    }

    public static List<Contenido> database(Activity activity)
    {
        if(database.size()>0)
        {
            return database;
        }
        else
        {
            setDataBase(activity);
            return database;
        }

    }

    public static void guardar(Activity activity, List<Contenido> db, int incremento)
    {
        if(database.size()==0)
        {
            setDataBase(activity);
        }

        for(Contenido cont: db)
        {
            database.get(cont.getOrder()).setStatus(cont.getStatus()+incremento);
            database.get(cont.getOrder()).setDate(Fecha.getFehaHoy());
            database.get(cont.getOrder()).setPointPrincipal(cont.getPointPrincipal());
        }
        Data.saveDatabase(database, Const.URL_DATABASE,activity);
    }



}
