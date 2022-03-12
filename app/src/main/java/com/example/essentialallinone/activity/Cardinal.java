package com.example.essentialallinone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.essentialallinone.Data.Data;
import com.example.essentialallinone.Essential;
import com.example.essentialallinone.R;
import com.example.essentialallinone.controlador.Controlador;
import com.example.essentialallinone.utility.Fecha;
import com.example.essentialallinone.utility.Reproductor;

import java.util.ArrayList;
import java.util.List;

public class Cardinal extends AppCompatActivity
{
    private static List<Essential> listado = new ArrayList<>();
    private static List<Essential> listadoEnUso = new ArrayList<>();
    private static List<Essential> listadoCompleto = new ArrayList<>();
    private int orden=0;
    EditText texto;
    TextView respuesta;
    private String path = "/sdcard/DB/DB.csv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardinal);
        cargar();
        reproducir();
    }

    private void cargar()
    {
        listado = Controlador.moduloCardinal(this);
        int a =0;
    }

    public void reproducir()
    {
        String word= listado.get(orden).getWord()+".mp3";
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
            if(texto.getText().toString().equalsIgnoreCase(listado.get(orden).getWord()))
            {
                texto.setText("");
                respuesta.setText("");
                actualizar(1);
            }
            else
            {
                texto.setText("");
                respuesta.setText(listado.get(orden).getMeaning());
                actualizar(-1);
            }
        }
    }

    private void actualizar(int incremento) {
        if (incremento == 1) {
            listado.get(orden).setPointPrincipal(listado.get(orden).getPointPrincipal() + 1);
        } else {
            listado.get(orden).setStatusActive(0);
            if (listado.get(orden).getPointPrincipal() > 0) {
                listado.get(orden).setStatusComplete(listado.get(orden).getPointPrincipal() + 1);
            }
        }
        listado.get(orden).setDate(Fecha.getFehaHoy());

        if (orden < listado.size()-1)
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
    }
    private void guardar()
    {
        listadoCompleto = Controlador.getListadoPrincipal(this);
        for(Essential ess: listado)
        {
            listadoCompleto.get(ess.getOrder()).setDate(ess.getDate());
            listadoCompleto.get(ess.getOrder()).setPointPrincipal(ess.getPointPrincipal());
            listadoCompleto.get(ess.getOrder()).setStatusActive(ess.getStatusActive());
        }
        Data.saveFile(listadoCompleto,path,this);
    }

}