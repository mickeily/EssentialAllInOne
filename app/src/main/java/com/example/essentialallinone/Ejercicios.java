package com.example.essentialallinone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.essentialallinone.activity.Contenido;
import com.example.essentialallinone.controlador.Controlador;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class Ejercicios extends AppCompatActivity {
    private static List<Contenido> database;
    String libro = "";
    String unidad = "";
    CheckBox textView;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicios);
        linearLayout = (LinearLayout) findViewById(R.id.lista_unidades);
        cargar();
        desplegarUnidades();
    }

    private void cargar()
    {

        database = new ArrayList<>();
        database = Controlador.getDatabase(this);
    }

    private void desplegarUnidades()
    {
        int libros =6;
        int unidades =30;
        String texto ="";

        for(int i =1; i<=libros;i++)
        {
            for(int j=1; j<= unidades;j++)
            {
                crearCheckBox("B" +i+"  U " + j);
            }
        }
    }

    public void crearCheckBox(String texto)
    {
        textView = new CheckBox(this);
        textView.setText(texto);
        linearLayout.addView(textView);
    }

     
}