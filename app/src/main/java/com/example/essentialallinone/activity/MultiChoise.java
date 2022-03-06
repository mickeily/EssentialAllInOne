package com.example.essentialallinone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.essentialallinone.Data.Data;
import com.example.essentialallinone.Essential;
import com.example.essentialallinone.R;
import com.example.essentialallinone.controlador.Controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultiChoise extends AppCompatActivity {
    private static List<Essential> listado = new ArrayList<>();
    private List<Essential> preguntasUsadas = new ArrayList<>();
    private List<Essential> listaRespuestas = new ArrayList<>();
    private static List<Essential> listadoCompleto = new ArrayList<>();
    private String path = "/sdcard/DB/DB.csv";
    private Essential preguntaEnUso = new Essential();
    private Random aleatorio = new Random();
    private  int orden=0;

    TextView pregunta;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_choise);
        pregunta= (TextView)findViewById(R.id.pregunta);
        radioGroup=(RadioGroup)findViewById(R.id.radio_group);
        cargar();
        seleccionarPregunta();
        proyectarPregunta();
        seleccionarRespuestas();
        proyectarRespuestas();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb =(RadioButton) findViewById(checkedId);
                comprobar(rb.getText().toString());
            }
        });
    }

    private void cargar()
    {
        listado = Controlador.ModuloMultiChoise(this);
    }

    private void seleccionarPregunta()
    {
        preguntaEnUso=null;

        while (preguntaEnUso==null)
        {
            orden = aleatorio.nextInt(listado.size());
            if(!preguntasUsadas.contains(listado.get(orden)))
            {
                preguntaEnUso = listado.get(orden);
            }
        }
    }

    private void proyectarPregunta()
    {
        pregunta.setText(convertSentence(preguntaEnUso.getMeaning(),preguntaEnUso.getWord()));
    }

    private void seleccionarRespuestas()
    {
        int numero =0;
        listaRespuestas.clear();
        listaRespuestas.add(preguntaEnUso);
        while (listaRespuestas.size()<3)
        {
            numero = aleatorio.nextInt(listado.size());
            if(!listaRespuestas.contains(listado.get(numero)))
            {
              listaRespuestas.add(listado.get(numero));
            }
        }
    }

    private void proyectarRespuestas()
    {
        List<Integer> numeros = listaDeNumeros();
        String palabra ="";
        for(Integer num:numeros)
        {
           crearRadioButton(listaRespuestas.get(num).getWord());
        }

    }

    private void crearRadioButton(String texto)
    {
        RadioButton  radio= new RadioButton(this);
        radio.setText(texto);
        radioGroup.addView(radio);
    }

    private List<Integer> listaDeNumeros()
    {
        int numero =0;
        List<Integer> numeros = new ArrayList<>();

        while (numeros.size()<3)
        {
            numero = aleatorio.nextInt(3);
            if(!numeros.contains(numero))
            {
              numeros.add(numero);
            }
        }
        return numeros;
    }

    private void comprobar(String texto)
    {
        if(texto.equalsIgnoreCase(preguntaEnUso.getWord()))
        {
            preguntasUsadas.add(preguntaEnUso);
            if(preguntasUsadas.size()<listado.size())
            {
               reiniciar();
            }
            else
            {
                guardar();
                Toast.makeText(this, "Saved file", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
           reiniciar();
        }

    }

    private void reiniciar()
    {
        radioGroup.removeAllViews();
        seleccionarPregunta();
        proyectarPregunta();
        seleccionarRespuestas();
        proyectarRespuestas();

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
        listadoCompleto = Controlador.getListadoPrincipal(this);
        for(Essential ess: listado)
        {
            listadoCompleto.get(ess.getOrder()).setStatusMultiChoise(1);
            listadoCompleto.get(ess.getOrder()).setStatusDefinition(2);
        }
        Data.saveFile(listadoCompleto,path,this);
    }
}