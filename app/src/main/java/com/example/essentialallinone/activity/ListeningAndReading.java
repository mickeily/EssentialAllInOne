package com.example.essentialallinone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.essentialallinone.Data.Data;
import com.example.essentialallinone.Data.DataManager;
import com.example.essentialallinone.Essential;
import com.example.essentialallinone.MainActivity;
import com.example.essentialallinone.R;
import com.example.essentialallinone.controlador.Controlador;
import com.example.essentialallinone.utility.Const;
import com.example.essentialallinone.utility.Fecha;
import com.example.essentialallinone.utility.Reproductor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListeningAndReading extends AppCompatActivity {
    private List<Contenido> database;
    private List<Contenido> listadoCompleto;
    private int orden = 0;
    private int caraCruz = 0;
    private int avance=0;
    int round = 4;
    private MainActivity mainActivity = new MainActivity();
    private int tablaPosiciones[];
    private static int objetivo;
    private TextView center;
    private Random aleatorio = new Random();
    private int TAMAGNO_FUENTE_PALABRA = 42;
    private int TAMAGNO_FUENTE_RESPUESTA = 18;
    private boolean adelantar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening_and_reading);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        center = (TextView) findViewById(R.id.t_center);
        objetivo = mainActivity.getObjetivo();

        cargarData();
        inicializarTablaPosiciones();
        seleccionarElemento();
        proyectarElemento();

    }

    public void cargarData() {
        database = new ArrayList<>();
        database = Controlador.getFilteredDatabase();
    }

    private void inicializarTablaPosiciones() {
        tablaPosiciones = new int[database.size()];
    }

    // Este metodo selecciona un elemento para se procesado, el cual debe haber aparecido menor o igual
    // veces que el que tenga menor aparariciones
    private void seleccionarElemento() {
        orden = aleatorio.nextInt(tablaPosiciones.length);
        for (int i = 0; i < tablaPosiciones.length; i++) {
            if (tablaPosiciones[i] < tablaPosiciones[orden]) {
                orden = i;
            }
        }
    }

    private void proyectarElemento() {

        if (objetivo == 0)
        {
            if(avance<=round/2)
            {
                center.setText(database.get(orden).getWord());
                center.setTextSize(TAMAGNO_FUENTE_PALABRA);
            }
            else
            {
                center.setText(convertSentence(database.get(orden).getMeaning(),database.get(orden).getWord()));
                center.setTextSize(TAMAGNO_FUENTE_RESPUESTA);
            }

        } else
        {
            center.setText(database.get(orden).getWord());
            center.setTextSize(TAMAGNO_FUENTE_PALABRA);
        }

    }

    public void proyectarRespuestas(View view) {
        if (adelantar == false) {
            adelantar = true;
        }
        if (objetivo == 0) {
            if (avance <= round/2) {

                center.setText(database.get(orden).getMeaning() + "\n\n" + database.get(orden).getExample());
                center.setTextSize(TAMAGNO_FUENTE_RESPUESTA);
            } else {
                center.setText(database.get(orden).getWord());
                center.setTextSize(TAMAGNO_FUENTE_PALABRA);
            }
        } else {
            reproducir();
        }
    }

    public void avanzar(View view) {
        if (adelantar == true) {
            tablaPosiciones[orden]++;
            reiniciar();
            adelantar = false;
        }

    }

    public void reiniciar() {
        if (comprobarSiFinalizar() == false) {
            seleccionarElemento();
            setAvance();
            proyectarElemento();
        } else {
            guardar();
            this.finish();
        }
    }

    public boolean comprobarSiFinalizar() {
        boolean flag = true;
        if(MainActivity.getObjetivo()==1) round=2;

        for (int puntuacion : tablaPosiciones) {
            if (puntuacion < round) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public void reproducir() {

        String word = database.get(orden).getWord() + ".mp3";
        String wordD = database.get(orden).getWord() + "_D.mp3";
        String wordE = database.get(orden).getWord() + "_E.mp3";

        Reproductor.reproducir(word, this);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Reproductor.reproducir(wordD, this);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Reproductor.reproducir(wordE, this);
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void guardar() {
        Controlador.guardar(this, database, 1);
        this.finish();
    }


    public String convertSentence(String oracion, String palabra) {
        String word = "";
        String sentence = oracion.toLowerCase();
        word = sentence.replace(palabra, "______");
        return word;
    }

    public void setAvance()
    {
        int pocision =0;
        for(int i = 0; i<tablaPosiciones.length;i++)
        {
          if(tablaPosiciones[i]>pocision)
          {
              pocision = tablaPosiciones[i];
          }
        }
        avance=pocision;
    }
}
/*
    private void guardar()
    {

        if(objetivo==6)
        {
            for(Essential ess: listado)
            {

                ess.setStatusRead(1);
                ess.setStatusMultiChoise(2);
                ess.setDate(Fecha.getFehaHoy());
            }
        }
        else
        {
            for(Essential ess: listado)
            {

                ess.setStatusListen(1);
                ess.setStatusRead(2);
                ess.setDate(Fecha.getFehaHoy());
            }
        }

        DataManager.update(listado,this);
        this.finish();
    }

 */


