package com.example.essentialallinone.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapa
{

    private static List<String> lista = new ArrayList<>();



    public static boolean cambiarPalabra(String s)
    {
        lista.add("arrives");
        lista.add("attacked");
        lista.add("hunted");
        lista.add("moments");
        lista.add("promised");
        lista.add("replied");

        if(lista.contains(s))
        {
            return true;
        }
        else
        {
            return false;
        }

    }







}
