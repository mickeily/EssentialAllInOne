package com.example.NewAllinOne.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.NewAllinOne.MainActivity;
import com.example.NewAllinOne.controlador.Controlador;
import com.example.NewAllinOne.utility.Reproductor;
import com.example.essentialallinone.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Listen extends AppCompatActivity {
    private List<Contenido> database;
    private List<Contenido> listadoCompleto;
    private int orden = 0;
    private int caraCruz = 0;
    private int avance=0;
    int round = 1;
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
        setContentView(R.layout.activity_listen);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        center = (TextView) findViewById(R.id.t_center);
        cargarData();
        inicializarTablaPosiciones();
        seleccionarElemento();
        proyectarElemento();
    }

    public void cargarData() {
        database = new ArrayList<>();
        database = Controlador.getFilteredDatabase(this);
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

    private void proyectarElemento()
    {
        center.setText(database.get(orden).getWord());
        center.setTextSize(TAMAGNO_FUENTE_PALABRA);
    }

    public void proyectarRespuestas(View view) {
        adelantar = true;
        reproducir();
    }

    public void avanzar(View view) {
        int a =0;
        if (adelantar == true) {
            tablaPosiciones[orden]++;
            reiniciar();
            adelantar = !adelantar;
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
        Controlador.guardar(this, database,1);
        this.finish();
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