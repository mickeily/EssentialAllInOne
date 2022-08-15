package com.example.NewAllinOne.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utility
{
    private static Random aleatorio = new Random();

    //Este metodo crea una lista aleatoria de numeros de longitud recibida como parametro
    public static List<Integer> listaNumerica(int longitud)
    {
        int numero =0;
       List<Integer> listaNumerica =  new ArrayList<>();
       while (listaNumerica.size()<longitud)
       {
           numero = aleatorio.nextInt(longitud);
           if(!listaNumerica.contains(numero))
           {
               listaNumerica.add(numero);
           }
       }
       return listaNumerica;
    }
}
