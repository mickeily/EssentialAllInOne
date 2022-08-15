package com.example.NewAllinOne.controlador;

import android.app.Activity;

import com.example.NewAllinOne.Data.Data;
import com.example.NewAllinOne.MainActivity;
import com.example.NewAllinOne.utility.Const;
import com.example.NewAllinOne.activity.Contenido;
import com.example.NewAllinOne.activity.Termino;
import com.example.NewAllinOne.utility.Fecha;

import java.util.ArrayList;
import java.util.List;

public class Controlador
{

    private static List<Contenido> database;
    private static List<Contenido> filteredDatabase;
    private static List<Termino> cuentos;
    private static String[] lista;
    //private static DataManager dataManager;
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

    // Este metodo devuleve una parte de la bd que cumpla con siertos requisitos, solo devulve 20
    // ejemplares
    public static void setFilteredDataBase(Activity activity)
    {
        int a =0;
        filteredDatabase = new ArrayList<>();
        if(database==null)
        {
            setDataBase(activity);
        }

        for(Contenido cont: database)
        {
            /*
                El metodo getHoras de la clase fecha recibe un String por parametros, la cual es la fecha
                del elemento en cuestion, este metodo devulve la cantidad de horas transcurrida desde la
                ultima modificacion de este elemento, entonces la condicion evalua si este valor es mayor
                o igual a la puntuacion del elemento multiplicado por 1.5 mas uno

                Ese medodo devulve 20 elementos que cumplan con el requisito
             */
            if(Fecha.getHoras(cont.getDate())>= ((cont.getPuntuation()*1.5)+1))
            {
                filteredDatabase.add(cont);
                if(filteredDatabase.size()==20)
                {
                    break;
                }
            }
        }
    }


    public static List<Contenido> getFilteredDatabase(Activity activity)
    {
        setFilteredDataBase(activity);
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

    public static void guardar(Activity activity, List<Contenido> db, int orden)
    {
        if(database.size()==0)
        {
            setDataBase(activity);
        }

        for(Contenido cont: db)
        {
            switch (orden)
            {
                case 0:
                {
                    database.get(cont.getOrder()).setStRead(1);
                    break;
                }
                case 1:
                {
                    database.get(cont.getOrder()).setStListen(1);
                    break;
                }
                case 2:
                {
                    database.get(cont.getOrder()).setStMultiChoice(1);
                    break;
                }
                case 3:
                {
                    database.get(cont.getOrder()).setStTranslationMc(1);
                    break;
                }
                case 4:
                {
                    database.get(cont.getOrder()).setStMatch(1);
                    break;
                }
                case 5:
                {
                    database.get(cont.getOrder()).setStTranslation(1);
                    break;
                }
                case 6:
                {
                    database.get(cont.getOrder()).setStExample(1);
                    break;
                }
                case 7:
                {
                    database.get(cont.getOrder()).setStDefinition(1);
                    break;
                }
                case 8:
                {
                    database.get(cont.getOrder()).setStComplete(1);
                    break;
                }
                case 9:
                {
                    database.get(cont.getOrder()).setStReadComplex(1);
                    break;
                }
                case 10:
                {
                    database.get(cont.getOrder()).setStHanged(1);
                    break;
                }
                case 11:
                {
                    database.get(cont.getOrder()).setStActivate(1);
                    break;
                }
                case 12:
                {
                    database.get(cont.getOrder()).setStRead(0);
                    database.get(cont.getOrder()).setStListen(0);
                    database.get(cont.getOrder()).setStMultiChoice(0);
                    database.get(cont.getOrder()).setStTranslationMc(0);
                    database.get(cont.getOrder()).setStMatch(0);
                    database.get(cont.getOrder()).setStTranslation(0);
                    database.get(cont.getOrder()).setStExample(0);
                    database.get(cont.getOrder()).setStDefinition(0);
                    database.get(cont.getOrder()).setStComplete(0);
                    database.get(cont.getOrder()).setStReadComplex(0);
                    database.get(cont.getOrder()).setStHanged(0);
                    database.get(cont.getOrder()).setStActivate(0);

                    database.get(cont.getOrder()).setDate(Fecha.getFehaHoy());
                    database.get(cont.getOrder()).setPuntuation(cont.getPuntuation());
                    database.get(cont.getOrder()).setFailed(cont.getFailed());
                    break;
                }
            }
        }
        Data.saveDatabase(database, Const.URL_DATABASE,activity);
        MainActivity.activarBotonCardinal();
        MainActivity.activarBotones();

    }
}
