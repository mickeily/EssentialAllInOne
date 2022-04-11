package com.example.essentialallinone.utility;

import android.app.Activity;


import com.example.essentialallinone.Data.Data;
import com.example.essentialallinone.Essential;
import com.example.essentialallinone.MainActivity;
import com.example.essentialallinone.controlador.Controlador;

import java.util.ArrayList;
import java.util.List;

public class Selector {

    private static List<Essential> listado = new ArrayList<>();
    private static int bandera = 0;

    private static void cargar(Activity activity) {
        listado = Controlador.getListadoPrincipal(activity);
    }

    public static int examinar(Activity activity)
    {
        bandera = 0;
        cargar(activity);


        for (Essential ess : listado)
        {
            examinarPrincipal(ess);
            long f = Fecha.getHoras(ess.getDate());
            if (bandera == 1) return bandera;

            else if (ess.getStatusHang() == 1)
            {
                bandera = 2;
                return bandera;
            } else if (ess.getStatusMatch() == 1 ) {
                bandera = 3;
                return bandera;
            } else if (ess.getStatusListen() == 1 ) {
                bandera = 4;
                return bandera;
            } else if (ess.getStatusRead() == 1 ) {
                bandera = 5;
                return bandera;
            } else if (ess.getStatusMultiChoise() == 1 ) {
                bandera = 6;
                return bandera;
            } else if (ess.getStatusComplete() == 1) {
                    bandera = 7;
                    return bandera;
            } else if (ess.getStatusDefinition() == 1) {
                bandera = 8;
                return bandera;
            }
            else if (ess.getStatusExample() == 1) {
                bandera = 9;
                return bandera;
            }
            else if (ess.getStatusExample() == 0 ) {
                bandera = 0;
                return bandera;
            }

        }
        return bandera;
    }
    private static int examinarPrincipal (Essential ess)
    {
        switch (ess.getPointPrincipal())
        {
            case 0: {
                if (Fecha.getHoras(ess.getDate()) >= Const.UNODIA && ess.getStatusActive() == 2) {
                    bandera = 1;
                }
            }
            break;
            case 1: {
                if (Fecha.getHoras(ess.getDate()) > Const.TRESDIAS && ess.getStatusActive() == 2) {
                    bandera = 1;
                }
            }
            break;
            case 2: {
                if (Fecha.getHoras(ess.getDate()) > Const.CINCODIAS && ess.getStatusActive() == 2) {
                    bandera = 1;
                }
            }
            break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            {
                if (Fecha.getHoras(ess.getDate()) > Const.SIETEDIAS && ess.getStatusActive() == 2) {
                    bandera = 1;
                }
            }
            break;
            default: {
                bandera = 0;
            }
        }

        return bandera;
    }
}


