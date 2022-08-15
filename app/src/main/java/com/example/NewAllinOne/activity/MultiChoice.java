package com.example.NewAllinOne.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.NewAllinOne.Essential;
import com.example.NewAllinOne.controlador.Controlador;
import com.example.NewAllinOne.utility.Const;
import com.example.NewAllinOne.utility.Utility;
import com.example.essentialallinone.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultiChoice extends AppCompatActivity {
    private List<Contenido> database;
    private List<Contenido> listaRespuestas = new ArrayList<>();
    private List<Essential> listadoCompleto = new ArrayList<>();
    private Random aleatorio = new Random();
    private int objetivo =0;
    private int tablaPosiciones[];
    TextView respuestaCorrecta;
    private  int round =1;
    TextView pregunta;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_choice);
        pregunta= (TextView)findViewById(R.id.pregunta);
        radioGroup=(RadioGroup)findViewById(R.id.radio_group);
        respuestaCorrecta= (TextView)findViewById(R.id.resp_correcta);
        cargar();

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
        database = new ArrayList<>();
        database = Controlador.getFilteredDatabase(this);

        if (database.size() >=3)
        {
            inicializarTablaPosiciones();
            seleccionarElemento();
            seleccionarOpsiones();
            desplegarPregunta();
            desplegarOpciones();
        }
        else
        {
            guardar();
        }
    }

    //Este metodo crea la tabla de posiciones del tamano de listado de elementos a procesar
    private void inicializarTablaPosiciones()
    {
        tablaPosiciones = new int[database.size()];
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
        listaRespuestas.add(database.get(objetivo));
        while (listaRespuestas.size()< Const.CANTIDAD_RESPUESTAS)
        {
            obj = aleatorio.nextInt(database.size());
            if(!listaRespuestas.contains(database.get(obj)))
            {
                listaRespuestas.add(database.get(obj));
            }
        }
    }

    private void desplegarPregunta()
    {
        pregunta.setText(convertSentence(database.get(objetivo).getDefiniton(),database.get(objetivo).getWord()));
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
        if(texto.equalsIgnoreCase(database.get(objetivo).getWord()))
        {
            respuestaCorrecta.setText("");
            respuestaCorrecta.setTextSize(18);
            tablaPosiciones[objetivo]++;
            reiniciar();

        }
        else
        {
            respuestaCorrecta.setText(database.get(objetivo).getDefiniton());
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
    private void guardar()
    {
        Controlador.guardar(this, database,2);
        this.finish();
    }

    public boolean comprobarSiFinalizar()
    {
        boolean flag = true;
        for(int puntuacion: tablaPosiciones)
        {
            if(puntuacion<round)
            {
                flag = false;
                break;
            }
        }
        return flag;
    }
}