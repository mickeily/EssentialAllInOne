package com.example.essentialallinone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.essentialallinone.Essential;
import com.example.essentialallinone.R;
import com.example.essentialallinone.controlador.Controlador;
import com.example.essentialallinone.utility.Const;
import com.example.essentialallinone.utility.Fecha;

import java.util.ArrayList;
import java.util.List;

public class Info extends AppCompatActivity {
    private static List<Essential> listado = new ArrayList<>();
    private static int[] estadoDeAvance;
    private static int[] estadoTodayMenu;
    private GridLayout avance;
    private GridLayout todayMenu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        avance = (GridLayout)findViewById(R.id.avance);
        todayMenu = (GridLayout)findViewById(R.id.todayMenu);
        cargar();
        inicializarEstadoDeAvance();
        inicializarEstadoTodayMenu();
        setEstadoDeAvance();
        avance();
        setEstadoTodayMenu();
        todayMenu();

    }

    private void cargar()
    {
        listado = Controlador.getListadoPrincipal(this);
    }

    public void inicializarEstadoDeAvance()
    {
        estadoDeAvance = new int[9];
    }
    public void inicializarEstadoTodayMenu()
    {
        estadoTodayMenu = new int[9];
    }

    public void setEstadoDeAvance()
    {
        for(Essential ess: listado)
        {
            switch (ess.getPointPrincipal())
            {
                case 0:
                {
                    if(ess.getStatusActive()==2)
                    {
                        estadoDeAvance[0]++;
                    }

                    break;
                }
                case 1:
                {
                    estadoDeAvance[1]++;
                    break;
                }
                case 2:
                {
                    estadoDeAvance[2]++;
                    break;
                }
                case 3:
                {
                    estadoDeAvance[3]++;
                    break;
                }
                case 4:
                {
                    estadoDeAvance[4]++;
                    break;
                }
                case 5:
                {
                    estadoDeAvance[5]++;
                    break;
                }
                case 6:
                {
                    estadoDeAvance[6]++;
                    break;
                }
                case 7:
                {
                    estadoDeAvance[7]++;
                    break;
                }
                case 8:
                {
                    estadoDeAvance[8]++;
                    break;
                }

            }

        }
        for(int est: estadoDeAvance)
        {
            int a =0;
        }
    }

    public void setEstadoTodayMenu()
    {
        int b =0;
        for(Essential ess: listado)
        {
            switch (ess.getPointPrincipal())
            {
                case 0: {
                    if (Fecha.getHoras(ess.getDate()) >= Const.UNODIA && ess.getStatusActive() == 2) {
                        estadoTodayMenu[1]++;
                    }
                }
                break;
                case 1:
                {
                    if (Fecha.getHoras(ess.getDate()) >= Const.TRESDIAS&& ess.getStatusActive() == 2) {
                        estadoTodayMenu[2]++;
                    }
                }
                break;
                case 2: {
                    if (Fecha.getHoras(ess.getDate()) >= Const.CINCODIAS && ess.getStatusActive() == 2) {
                        estadoTodayMenu[3]++;
                    }
                }
                break;
                case 3:
                {
                    if (Fecha.getHoras(ess.getDate()) >= Const.SIETEDIAS && ess.getStatusActive() == 2)
                    {
                        estadoTodayMenu[4]++;
                    }
                }

                case 4:
                {
                    if (Fecha.getHoras(ess.getDate()) >= Const.SIETEDIAS && ess.getStatusActive() == 2)
                    {
                        estadoTodayMenu[5]++;
                    }
                }
                case 5:
                {
                    if (Fecha.getHoras(ess.getDate()) >= Const.SIETEDIAS && ess.getStatusActive() == 2)
                    {
                        estadoTodayMenu[6]++;
                    }
                }
                case 6:
                {
                    if (Fecha.getHoras(ess.getDate()) >= Const.SIETEDIAS && ess.getStatusActive() == 2)
                    {
                        estadoTodayMenu[7]++;
                    }
                }
                case 7:
                {
                    if (Fecha.getHoras(ess.getDate()) >= Const.SIETEDIAS && ess.getStatusActive() == 2)
                    {
                        estadoTodayMenu[8]++;
                    }
                }
                break;
                default: {

                }
            }
        }
        int a =0;

    }


    public void avance()
    {
        TextView textView;
        String[] numeros = {"0","1","2","3","4","5","6","7","P"};
        for(String num: numeros)
        {
            textView = new TextView(this);
            textView.setText(num);
            textView.setPadding(0,0,56,0);
            avance.addView(textView);

        }

        for(int i=0;i<estadoDeAvance.length;i++)
        {
            textView = new TextView(this);
            textView.setText(estadoDeAvance[i]+"");
            textView.setPadding(0,24,56,0);
            avance.addView(textView);
        }


    }

    public void todayMenu()
    {
        TextView textView;
        String[] numeros = {"1","2","3","4","5","6","7","P"};
        for(String num: numeros)
        {
            textView = new TextView(this);
            textView.setText(num);
            textView.setPadding(0,0,56,0);
            todayMenu.addView(textView);

        }

        for(int i=1;i<estadoTodayMenu.length;i++)
        {
            textView = new TextView(this);
            textView.setText(estadoTodayMenu[i]+"");
            textView.setPadding(0,24,56,0);
            todayMenu.addView(textView);
        }

    }






}