package com.example.essentialallinone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.essentialallinone.Data.Data;
import com.example.essentialallinone.Data.DataManager;
import com.example.essentialallinone.Essential;
import com.example.essentialallinone.R;
import com.example.essentialallinone.controlador.Controlador;
import com.example.essentialallinone.utility.Const;
import com.example.essentialallinone.utility.Fecha;
import com.example.essentialallinone.utility.Reproductor;

import java.util.ArrayList;
import java.util.List;

public class Cardinal extends AppCompatActivity
{
    private  List<Contenido> database;
    private static List<Essential> listadoEnUso = new ArrayList<>();
    private static List<Essential> listadoCompleto = new ArrayList<>();
    private int orden=0;

    EditText texto;
    TextView preg2;
    TextView respuesta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardinal);
        preg2 = (TextView)findViewById(R.id.preg2);
        cargar();
        reproducir();
        showAnswer();


    }

    private void cargar()
    {

        database = new ArrayList<>();
        database = Controlador.getFilteredDatabase();
    }

    public void reproducir()
    {
        String word= database.get(orden).getWord()+".mp3";
        Reproductor.reproducir(word,this);

    }
    public void comprobar(View view)
    {
        respuesta=(TextView)findViewById(R.id.respuesta);
        texto = (EditText) findViewById(R.id.texto_principal);

        if(texto.getText().toString().equalsIgnoreCase(""))
        {
            reproducir();
        }
        else
        {
            if(texto.getText().toString().equalsIgnoreCase(database.get(orden).getWord()))
            {
                texto.setText("");
                respuesta.setText("");
                actualizar(1);
            }
            else
            {
                texto.setText("");
                respuesta.setText(database.get(orden).getMeaning());
                actualizar(-1);
            }
        }
    }

    private void actualizar(int incremento) {
        int a =0;
        if (incremento == 1)
        {
            database.get(orden).setPointPrincipal(database.get(orden).getPointPrincipal() + 1);
        }
        else
        {
            database.get(orden).setStatus(database.get(orden).getStatus()-1);

            database.get(orden).setPointPrincipal(database.get(orden).getPointPrincipal() / 2);

        }
        database.get(orden).setDate(Fecha.getFehaHoy());

        if (orden < database.size()-1)
        {
            reiniciar();
        }
        else
        {
            guardar();
        }
    }

    private void reiniciar()
    {
        orden++;
        reproducir();
        showAnswer();
    }
    private void guardar()
    {
        Controlador.guardar(this,database,0);
        this.finish();
    }

    private void showAnswer()
    {
        preg2.setText(Hanged.convertSentence(database.get(orden).getMeaning(),database.get(orden).getWord()));
    }


/*
    private void guardar()
    {
        DataManager.update(listado,this);
        this.finish();
    }
    */



}