package com.example.essentialallinone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.essentialallinone.Essential;
import com.example.essentialallinone.R;
import com.example.essentialallinone.controlador.Controlador;
import com.example.essentialallinone.utility.Const;
import com.example.essentialallinone.utility.Fecha;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Info extends AppCompatActivity {
    private static List<Essential> listado = new ArrayList<>();
    private static List<Essential> listadoCortado = new ArrayList<>();
    private static int[] dias = new int[18];
    private static int[] estadoDeAvance;
    private static long[] tiempoFaltante;
    private static int[] estadoTodayMenu;
    private LinearLayout avance;
    private LinearLayout periferico;
    private LinearLayout inCero;
    private LinearLayout todayMenu;
    private  TextView texto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        avance = (LinearLayout) findViewById(R.id.avance);
        periferico = (LinearLayout) findViewById(R.id.perifefico);
        inCero = (LinearLayout) findViewById(R.id.in_cero);
        todayMenu = (LinearLayout) findViewById(R.id.todayMenu);
        cargar();
        rellenarDias();
        inicializarEstadoDeAvance();
        inicializarEstadoTodayMenu();
        setEstadoDeAvance();
        avance();
        perifericos();
        setEstadoTodayMenu();
        todayMenu();
        inCero();
        calcularFechaTermino();

    }

    private void cargar()
    {
        listado = Controlador.getListadoPrincipal(this);
    }

    public void inicializarEstadoDeAvance()
    {
        estadoDeAvance = new int[19];
    }
    public void inicializarEstadoTodayMenu()
    {
        estadoTodayMenu = new int[19];
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
                case 9:
                {
                    estadoDeAvance[9]++;
                    break;
                }

                case 10:
                {
                    estadoDeAvance[10]++;
                    break;
                }
                case 11:
                {
                    estadoDeAvance[11]++;
                    break;
                }
                case 12:
                {
                    estadoDeAvance[12]++;
                    break;
                }
                case 13:
                {
                    estadoDeAvance[13]++;
                    break;
                }
                case 14:
                {
                    estadoDeAvance[14]++;
                    break;
                }
                case 15:
                {
                    estadoDeAvance[15]++;
                    break;
                }
                case 16:
                {
                    estadoDeAvance[16]++;
                    break;
                }
                case 17:
                {
                    estadoDeAvance[17]++;
                    break;
                }
                case 18:
                {
                    estadoDeAvance[18]++;
                    break;
                }
            }
        }

    }

    public void setEstadoTodayMenu()
    {
        int b =0;
        int inicio =1;
        tiempoFaltante= new long[18];
        for(Essential ess: listado)
        {
            switch (ess.getPointPrincipal())
            {
                case 0: {
                    if (Fecha.getHoras(ess.getDate()) >= Const.UNODIA && ess.getStatusActive() == 2) {
                        estadoTodayMenu[1]++;
                    }
                    break;
                }

                case 1:
                {
                    if (Fecha.getHoras(ess.getDate()) >= Const.TRESDIAS&& ess.getStatusActive() == 2) {
                        estadoTodayMenu[2]++;
                    }
                    break;
                }

                case 2: {
                    if (Fecha.getHoras(ess.getDate()) >= Const.CINCODIAS && ess.getStatusActive() == 2) {
                        estadoTodayMenu[3]++;
                    }
                    break;
                }

                case 3:
                {
                    if (Fecha.getHoras(ess.getDate()) >= Const.CINCODIAS && ess.getStatusActive() == 2)
                    {
                        estadoTodayMenu[4]++;
                    }
                    break;
                }

                case 4:
                {
                    if (Fecha.getHoras(ess.getDate()) >= Const.CINCODIAS && ess.getStatusActive() == 2)
                    {
                        estadoTodayMenu[5]++;
                    }
                    break;
                }

                case 5:
                {
                    if (Fecha.getHoras(ess.getDate()) >= Const.SIETEDIAS && ess.getStatusActive() == 2)
                    {
                        estadoTodayMenu[6]++;
                    }
                    break;
                }

                case 6:
                {
                    if (Fecha.getHoras(ess.getDate()) >= Const.SIETEDIAS && ess.getStatusActive() == 2)
                    {
                        estadoTodayMenu[7]++;
                    }
                    break;
                }

                case 7:
                {
                    if (Fecha.getHoras(ess.getDate()) >= Const.SIETEDIAS && ess.getStatusActive() == 2)
                    {
                        estadoTodayMenu[8]++;
                    }
                    break;
                }

                case 8:
                {
                    if (Fecha.getHoras(ess.getDate()) >= Const.CATORCEDIAS && ess.getStatusActive() == 2)
                    {
                        estadoTodayMenu[9]++;
                    }
                    break;
                }

                case 9:
                {
                    if (Fecha.getHoras(ess.getDate()) >= Const.CATORCEDIAS && ess.getStatusActive() == 2)
                    {
                        estadoTodayMenu[10]++;
                    }
                    break;
                }

                case 10:
                {
                    if (Fecha.getHoras(ess.getDate()) >= Const.CATORCEDIAS && ess.getStatusActive() == 2)
                    {
                        estadoTodayMenu[11]++;
                    }
                    break;
                }

                case 11:
                {
                    if (Fecha.getHoras(ess.getDate()) >= Const.VEINTIOCHODIAS && ess.getStatusActive() == 2)
                    {
                        estadoTodayMenu[12]++;
                    }
                    break;
                }

                case 12:
                {
                    if (Fecha.getHoras(ess.getDate()) >= Const.VEINTIOCHODIAS && ess.getStatusActive() == 2)
                    {
                        estadoTodayMenu[13]++;
                    }
                    break;
                }
                case 13:
                {
                    if (Fecha.getHoras(ess.getDate()) >= Const.VEINTIOCHODIAS && ess.getStatusActive() == 2)
                    {
                        estadoTodayMenu[14]++;
                    }
                    break;
                }

                case 14:
                {
                    if (Fecha.getHoras(ess.getDate()) >= Const.CINCUENTAYSEISDIAS && ess.getStatusActive() == 2)
                    {
                        estadoTodayMenu[15]++;
                    }
                    break;
                }

                case 15:
                {
                    if (Fecha.getHoras(ess.getDate()) >= Const.CINCUENTAYSEISDIAS && ess.getStatusActive() == 2)
                    {
                        estadoTodayMenu[16]++;
                    }
                    break;
                }

                case 16:
                {
                    if (Fecha.getHoras(ess.getDate()) >= Const.CINCUENTAYSEISDIAS && ess.getStatusActive() == 2)
                    {
                        estadoTodayMenu[17]++;
                    }
                    break;
                }

                case 17:
                {
                    if (Fecha.getHoras(ess.getDate()) >= Const.CIENTODOCEDIAS && ess.getStatusActive() == 2)
                    {
                        estadoTodayMenu[18]++;
                    }
                    break;
                }

                default: {

                }
            }
        }
        List<Essential> lista = new ArrayList<>();
        long tiempo=0;

        Fecha.FechaInterna fechaInterna= new Fecha.FechaInterna();

        for(int i =0; i<tiempoFaltante.length-1;i++)
        {
            lista = getListadoCortado(i);
            long minutos = 0;
            tiempo =50000000;

            for(int j = 0; j<lista.size();j++)
            {
                //f = lista.get(i).getDate();
                fechaInterna = Fecha.fechaFutura(dias[i],lista.get(j).getDate());
                minutos= Fecha.getMinutos(Fecha.getFehaHoy(),fechaInterna);

                if(tiempo>minutos)
                {
                    tiempo = minutos;
                }
            }
            if(tiempo!=50000000)
            {
                tiempoFaltante[i]= tiempo;
            }

        }
        int r =0;
    }

    public void avance()
    {
        TextView textView;

        for(int i=0;i<estadoDeAvance.length;i++) {

            texto = new TextView(this);
            textView = new TextView(this);
            textView.setText(i + "" + "\t\t\t" + estadoDeAvance[i] + "");
            textView.setPadding(0, 16, 0, 0);
            if (estadoDeAvance[i] > 0)
            {
                avance.addView(textView);
            }

        }

    }

    public void todayMenu()
    {
        int a =0;
        TextView textView;
        String time = "";
        String cadena ="";

        for(int i=1;i<estadoTodayMenu.length;i++)
        {

            time = calcularTiempo(tiempoFaltante[i-1]);
            textView = new TextView(this);
            cadena = i+"" +"\t\t" +estadoTodayMenu[i]+ "\t\t" +time;
            textView.setText(i+"" +"\t\t" +estadoTodayMenu[i]+ "\t\t" +time);
            textView.setPadding(0,16,0,0);
            if(estadoTodayMenu[i]>0 || (!time.equals("")))
            {
                todayMenu.addView(textView);
            }

        }

    }

    public void perifericos()
    {
        TextView textView;
        Periferica per = new Periferica();
        String[] posiciones = {"Ex","De","Cp","Mc","Rd","Lt","Mt","Hg","Ac"};
        int[] arreglo = new int[9];

        for(Essential ess: listado)
        {
            if(ess.getStatusExample()==1)
            {
                arreglo[0]++;
            }
            else if(ess.getStatusDefinition()==1)
            {
                arreglo[1]++;
            }
            else if(ess.getStatusComplete()==1)
            {
                arreglo[2]++;
            }

            else if(ess.getStatusMultiChoise()==1)
            {
                arreglo[3]++;
            }
            else if(ess.getStatusRead()==1)
            {
                arreglo[4]++;
            }
            else if(ess.getStatusListen()==1)
            {
                arreglo[5]++;
            }

            else if(ess.getStatusMatch()==1)
            {
                arreglo[6]++;
            }
            else if(ess.getStatusHang()==1)
            {
                arreglo[7]++;
            }
            else if(ess.getStatusActive()==2 && ess.getPointPrincipal()==0)
            {
                arreglo[8]++;
            }
        }

        for(int i =0; i< posiciones.length; i++)
        {
            if(arreglo[i]!=0)
            {
                textView = new TextView(this);
                textView.setText(posiciones[i] + "\t\t\t" +arreglo[i]);
                textView.setPadding(0,24,0,0);
                periferico.addView(textView);
            }

        }

    }

    private void inCero()
    {
        int total = 0;
        TextView textView;
        int[] lista = new int[7];
        for(Essential ess: listado)
        {
           if(ess.getStatusActive()!=0)
           {
               lista[Integer.parseInt(ess.getBook())] ++;
           }
        }

        for(int i =1; i<lista.length; i++)
        {
            if(lista[i]!=0)
            {
                textView = new TextView(this);
                textView.setText(i+"" +"\t\t\t" +lista[i]+"");
                textView.setPadding(0,16,0,0);
                inCero.addView(textView);
                total += lista[i];
            }

        }
        textView = new TextView(this);
        textView.setText("T" +"\t\t\t" +total+"");
        textView.setPadding(0,16,0,0);
        inCero.addView(textView);






    }

    public List<Essential> getListadoCortado(int estatus)
    {listadoCortado.clear();
        for(Essential ess: listado)
        {
           if(ess.getPointPrincipal()==estatus && ess.getStatusActive() !=0)
           {
               listadoCortado.add(ess);
           }
        }
        return listadoCortado;

    }

    public String calcularTiempo(long tiempo)
    {
        int dias=0;
        long t =0;
        int horas =0;
        int minutos =0;
        String time = "";
        if(tiempo>0)
        {
            dias = (int) Math.abs(tiempo/1440);
            if(dias>0)
            {
                tiempo = tiempo-(dias*1440);
            }

            horas = (int) Math.abs(tiempo/60);
            if(horas>0)
            {
                tiempo = tiempo-(horas*60);
            }

            minutos = (int)tiempo;
            time= dias+":"+horas+":"+minutos;
            return  time;

        }

        return  time;
    }
    private void rellenarDias()
    {
        dias[0]=1*24;
        dias[1]=3*24;
        dias[2]=5*24;
        dias[3]=5*24;
        dias[4]=5*24;
        dias[5]=7*24;
        dias[6]=7*24;
        dias[7]=7*24;
        dias[8]=14*24;
        dias[9]=14*24;
        dias[10]=14*24;
        dias[11]=28*24;
        dias[12]=28*24;
        dias[13]=28*24;
        dias[14]=56*24;
        dias[15]=56*24;
        dias[16]=56*24;
        dias[17]=112*24;
    }

    private void calcularFechaTermino()
    {
        TextView textEnd;
        textEnd = (TextView) findViewById(R.id.text_end);
        int j =0;
        String fechaInicio = "2022:3:11:22:0";
        long tiempoTranscurrido =0;
        double progresoDiario =0;
        double diasFaltantes =0;
        double faltante = 0;
        double progreso =0;
        tiempoTranscurrido= Fecha.getHoras(fechaInicio)/24;
        progreso = contarProgreso();
        progresoDiario = progreso/tiempoTranscurrido;
        faltante = 3600-progreso;
        diasFaltantes= faltante/progresoDiario;
        float horas =(float) (diasFaltantes*24);

        Fecha.FechaInterna fecha = new Fecha.FechaInterna();
        fecha = Fecha.fechaFutura(horas,Fecha.getFehaHoy());
        String fechaStr =fecha.getDay()+":" +(fecha.getMonth()+1)+":"+fecha.getYear()+"";
        textEnd.setText(fechaStr);

    }

    private int contarProgreso()
    {
        int acomulador =0;
        for(Essential ess: listado)
        {
           if(ess.getStatusActive()!=0)
           {
               acomulador++;
           }
        }
        return acomulador;
    }

    class Periferica
    {
        public Periferica() {
        }

        public  int example=0;
        public int definition=0;
        public int complete =0;
        public int read =0;
        public int listen =0;
        public int multichoise =0;
        public int match=0;
        public int hang =0;
        public int active =0;
    }
}
