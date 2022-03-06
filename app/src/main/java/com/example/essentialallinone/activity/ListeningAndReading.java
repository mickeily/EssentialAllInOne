package com.example.essentialallinone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.essentialallinone.Data.Data;
import com.example.essentialallinone.Essential;
import com.example.essentialallinone.MainActivity;
import com.example.essentialallinone.R;
import com.example.essentialallinone.controlador.Controlador;
import com.example.essentialallinone.utility.Reproductor;

import java.util.ArrayList;
import java.util.List;

public class ListeningAndReading extends AppCompatActivity {
    private static List<Essential> listado = new ArrayList<>();
    private static List<Essential> listadoCompleto = new ArrayList<>();
    private int orden =0;
    MainActivity mainActivity =  new MainActivity();
    private static int objetivo;
    TextView center;
    private String path = "/sdcard/DB/DB.csv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening_and_reading);
        center= (TextView)findViewById(R.id.t_center);
        objetivo = mainActivity.getObjetivo();
        cargarData();
        seleccionar();

    }

    public void cargarData()
    {
        listado = Controlador.ModuloReadAndListen(this,objetivo);
    }
    private void seleccionar()
    {
        proyectarPalabra(42);
    }
    private void proyectarPalabra(int size)
    {
        center.setText(listado.get(orden).getWord());
        center.setTextSize(size);
    }

    public void proyectarDefinition(View view)
    {
        int a =0;
        if(objetivo==7)
        {
            int size =20;
            center.setText(listado.get(orden).getMeaning() +"\n\n"+ listado.get(orden).getExample());
            center.setTextSize(size);
        }
        else
        {
            reproducir();
        }

    }

    public void adelantar(View view)
    {
        if(orden<listado.size()-1)
        {
            orden++;
            seleccionar();
        }
        else
        {
            guardar();
            Toast.makeText(this, "Saved file", Toast.LENGTH_SHORT).show();
        }
    }

    public void reproducir()
    {
        String word= listado.get(orden).getWord()+".mp3";
        String wordD= listado.get(orden).getWord()+"_D.mp3";
        String wordE= listado.get(orden).getWord()+"_E.mp3";

        Reproductor.reproducir(word,this);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Reproductor.reproducir(wordD,this);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Reproductor.reproducir(wordE,this);
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    private void guardar()
    {
        listadoCompleto = Controlador.getListadoPrincipal(this);
        if(objetivo==7)
        {
            for(Essential ess: listado)
            {
                listadoCompleto.get(ess.getOrder()).setStatusRead(1);
                listadoCompleto.get(ess.getOrder()).setStatusMultiChoise(2);
            }
        }
        else
        {
            for(Essential ess: listado)
            {
                listadoCompleto.get(ess.getOrder()).setStatusListen(1);
                listadoCompleto.get(ess.getOrder()).setStatusRead(2);
            }
        }

        Data.saveFile(listadoCompleto,path,this);
    }


}