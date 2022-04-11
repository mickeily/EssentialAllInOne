package com.example.essentialallinone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.essentialallinone.Data.Data;
import com.example.essentialallinone.Data.DataManager;
import com.example.essentialallinone.Essential;
import com.example.essentialallinone.R;
import com.example.essentialallinone.controlador.Controlador;
import com.example.essentialallinone.utility.Const;
import com.example.essentialallinone.utility.Fecha;
import com.example.essentialallinone.utility.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultiChoise extends AppCompatActivity {
    private List<Essential> listado = new ArrayList<>();
    private List<Essential> listaRespuestas = new ArrayList<>();
    private List<Essential> listadoCompleto = new ArrayList<>();
    private Random aleatorio = new Random();
    private int objetivo =0;
    private int tablaPosiciones[];
    TextView respuestaCorrecta;


    TextView pregunta;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_choise);
        pregunta= (TextView)findViewById(R.id.pregunta);
        radioGroup=(RadioGroup)findViewById(R.id.radio_group);
        respuestaCorrecta= (TextView)findViewById(R.id.resp_correcta);
        cargar();
        inicializarTablaPosiciones();
        seleccionarElemento();
        seleccionarOpsiones();
        desplegarPregunta();
        desplegarOpciones();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb =(RadioButton) findViewById(checkedId);
                comprobar(rb.getText().toString());
            }
        });
    }

    //El metodo cargar carga la informacion correspondiente a este modulo
    private void cargar()
    {
        listado = Controlador.moduloMultiChoise(this);
    }

    //Este metodo crea la tabla de posiciones del tamano de listado de elementos a procesar
    private void inicializarTablaPosiciones()
    {
        tablaPosiciones = new int[listado.size()];
    }

    // Este metodo selecciona un elemento para se procesado, el cual debe haber aparecido menor o igual
    // veces que el que tenga menor aparariciones
    private void seleccionarElemento()
    {
        objetivo = aleatorio.nextInt(tablaPosiciones.length);
        for(int i=0;i<tablaPosiciones.length;i++)
        {
            if(tablaPosiciones[i]<tablaPosiciones[objetivo])
            {
                objetivo=i;
            }
        }
    }

    //Este metodo selecciona las opsiones que se seran despleagas incluyendo la respuesta correpta
    public void seleccionarOpsiones()
    {
        listaRespuestas.clear();
        int obj = 0;
        listaRespuestas.add(listado.get(objetivo));
        while (listaRespuestas.size()<Const.CANTIDAD_RESPUESTAS)
        {
            obj = aleatorio.nextInt(listado.size());
            if(!listaRespuestas.contains(listado.get(obj)))
            {
               listaRespuestas.add(listado.get(obj));
            }
        }
    }

    private void desplegarPregunta()
    {
        pregunta.setText(convertSentence(listado.get(objetivo).getMeaning(),listado.get(objetivo).getWord()));
        pregunta.setTextSize(18);
    }

    private void desplegarOpciones()
    {
        List<Integer> opciones = Utility.listaNumerica(listaRespuestas.size());
        for(Integer posicion: opciones)
        {
            crearRadioButton(listaRespuestas.get(posicion).getWord());
        }
    }

    private void crearRadioButton(String texto)
    {
        TextView caja = new TextView(this);
        RadioButton  radio= new RadioButton(this);
        radio.setText(texto);
        radio.setTextSize(20);
        radio.setPadding(0,0,0,0);
        radioGroup.addView(radio);
        radioGroup.addView(caja);
    }

    private void comprobar(String texto)
    {
        if(texto.equalsIgnoreCase(listado.get(objetivo).getWord()))
        {
            respuestaCorrecta.setText("");
            respuestaCorrecta.setTextSize(18);
            tablaPosiciones[objetivo]++;
            reiniciar();

        }
        else
        {
            respuestaCorrecta.setText(listado.get(objetivo).getMeaning());
            respuestaCorrecta.setTextSize(18);
           reiniciar();
        }
    }

    private void reiniciar()
    {
        radioGroup.removeAllViews();
        if(comprobarSiFinalizar()==false)
        {
            seleccionarElemento();
            seleccionarOpsiones();
            desplegarPregunta();
            desplegarOpciones();
        }
        else
        {
            guardar();
            this.finish();
        }

    }
    public String convertSentence(String oracion,String palabra)
    {
        String word ="";
        String sentence = oracion.toLowerCase();
        word = sentence.replace(palabra,"______");
        return word;
    }
    private void guardar2()
    {
        listadoCompleto = Controlador.getListadoPrincipal(this);
        for(Essential ess: listado)
        {
            listadoCompleto.get(ess.getOrder()).setStatusMultiChoise(1);
            listadoCompleto.get(ess.getOrder()).setStatusComplete(2);
        }
        Data.saveFile(listadoCompleto, Const.URL_DATABASE,this);
    }

    public boolean comprobarSiFinalizar()
    {
        boolean flag = true;
        for(int puntuacion: tablaPosiciones)
        {
            if(puntuacion<Const.ROUNDS)
            {
                flag = false;
                break;
            }
        }
        return flag;
    }

    private void guardar()
    {

        for(Essential ess: listado)
        {

            ess.setStatusMultiChoise(1);
            ess.setStatusComplete(2);
            ess.setDate(Fecha.getFehaHoy());
        }
        DataManager.update(listado,this);
        this.finish();
    }
}