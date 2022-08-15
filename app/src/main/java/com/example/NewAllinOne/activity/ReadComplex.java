package com.example.NewAllinOne.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.NewAllinOne.MainActivity;
import com.example.NewAllinOne.controlador.Controlador;
import com.example.essentialallinone.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReadComplex extends AppCompatActivity {
    private List<Contenido> database;
    private int orden = 0;
    private int avance=0;
    int round = 1;
    private int tablaPosiciones[];
    private TextView center;
    private Random aleatorio = new Random();
    private int TAMAGNO_FUENTE_PALABRA = 42;
    private int TAMAGNO_FUENTE_RESPUESTA = 18;
    private boolean adelantar = false;
    private boolean respuesta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_complex);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        center = (TextView) findViewById(R.id.t_center);
        respuesta = true;
        cargarData();
        inicializarTablaPosiciones();
        seleccionarElemento();
        //proyectarRespuestas();
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

    public void proyectarElemento()
    {
        if(respuesta == true)
        {
            center.setText(convertSentence(database.get(orden).getDefiniton(),database.get(orden).getWord()));
            center.setTextSize(TAMAGNO_FUENTE_RESPUESTA);
            respuesta = !respuesta;
        }
        else
        {
            center.setText(database.get(orden).getWord());
            center.setTextSize(TAMAGNO_FUENTE_PALABRA);
            respuesta = !respuesta;
        }
    }

    public void avanzar(View view) {

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

    private void guardar() {
        Controlador.guardar(this, database,9);
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

    public String convertSentence(String oracion, String palabra) {
        String word = "";
        String sentence = oracion.toLowerCase();
        word = sentence.replace(palabra, "______");
        return word;
    }

    public void permisoAvanzar(View view)
    {
       adelantar =true;
       proyectarElemento();
    }
}