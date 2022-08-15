package com.example.NewAllinOne.utility;

import android.app.Activity;


import com.example.NewAllinOne.Essential;
import com.example.NewAllinOne.activity.Contenido;
import com.example.NewAllinOne.controlador.Controlador;

import java.util.ArrayList;
import java.util.List;

public class Selector {


    private static List<Contenido> listaContenido = new ArrayList<>();
    private static int bandera = 0;



    private static void cargarDB(Activity activity) {
        listaContenido = Controlador.getDatabase(activity);
    }


    public static int evaluar(Activity activity)
    {



        return 0;
    }

}


