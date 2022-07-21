package com.example.essentialallinone.utility;

import android.app.Activity;
import android.widget.Toast;


import com.example.essentialallinone.Data.Data;
import com.example.essentialallinone.Essential;
import com.example.essentialallinone.MainActivity;
import com.example.essentialallinone.activity.Contenido;
import com.example.essentialallinone.controlador.Controlador;

import java.util.ArrayList;
import java.util.List;

public class Selector {

    private static List<Essential> listado = new ArrayList<>();
    private static List<Contenido> listaContenido = new ArrayList<>();
    private static int bandera = 0;

    private static void cargar(Activity activity) {
        listado = Controlador.getListadoPrincipal(activity);
    }

    private static void cargarDB(Activity activity) {
        listaContenido = Controlador.getDatabase(activity);
    }

    public static int evaluar(Activity activity)
    {
        int bandera =-1;
        int contador =0;
        Contenido cont = new Contenido();
      cargarDB(activity);

int a =0;
      while (bandera==-1)
      {
          cont = listaContenido.get(contador);
          switch (cont.getStatus())
          {
              case 0:
              case 1:
              case 2:
              case 3:
              case 4:
              case 5:
              case 6:
              case 7:
              case 8:

              {
                  bandera= cont.getStatus();
                  break;
              }
              case 9:
              {
                  if(evaluarPrincipal(cont)==1)
                  {
                      bandera= cont.getStatus();
                  }
                break;
              }

          }
          contador++;
      }
      return bandera;
    }

    private static int evaluarPrincipal(Contenido contenido)
    {
        int CONTSTANTERETORNO=1;
        int bandera =-1;
        switch (contenido.getPointPrincipal())
        {
            case 0: {
                if (Fecha.getHoras(contenido.getDate()) >= Const.UNODIA) {
                    bandera = CONTSTANTERETORNO;
                }
            }
            break;
            case 1: {
                if (Fecha.getHoras(contenido.getDate()) >= Const.TRESDIAS) {
                    bandera = CONTSTANTERETORNO;
                }
            }
            break;
            case 2:
            case 3:
            case 4:
            {
                if (Fecha.getHoras(contenido.getDate()) >= Const.CINCODIAS) {
                    bandera = CONTSTANTERETORNO;
                }
            }
            break;

            case 5:
            case 6:
            case 7:
            {
                if (Fecha.getHoras(contenido.getDate()) >= Const.SIETEDIAS) {
                    bandera = CONTSTANTERETORNO;
                }
            }
            break;
            case 8:
            case 9:
            case 10:
            {
                if (Fecha.getHoras(contenido.getDate()) >= Const.CATORCEDIAS) {
                    bandera = CONTSTANTERETORNO;
                }
            }
            break;
            case 11:
            case 12:
            case 13:
            {
                if (Fecha.getHoras(contenido.getDate()) >= Const.VEINTIOCHODIAS) {
                    bandera = CONTSTANTERETORNO;
                }
            }
            break;
            case 14:
            case 15:
            case 16:
            {
                if (Fecha.getHoras(contenido.getDate()) >= Const.CINCUENTAYSEISDIAS) {
                    bandera = CONTSTANTERETORNO;
                }
            }
            break;
            case 17:
            {
                if (Fecha.getHoras(contenido.getDate()) >= Const.CIENTODOCEDIAS) {
                    bandera = CONTSTANTERETORNO;
                }
            }
            default: {
                bandera = -1;
            }
        }
        return bandera;
    }
}


