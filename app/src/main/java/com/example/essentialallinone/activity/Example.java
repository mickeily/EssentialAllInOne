package com.example.essentialallinone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.essentialallinone.Essential;
import com.example.essentialallinone.R;
import com.example.essentialallinone.controlador.Controlador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Example extends AppCompatActivity {
    private static List<Essential> listado = new ArrayList<>();
    private List<List<String>> listaAleatoria;
    private List<List<String>> listaOrganizada;
    private ArrayList<String> widgets;
    private int orden =0;

    LinearLayout inferior;
    LinearLayout superior;
    TextView textViewInferior;
    TextView textViewSuperior;
    TextView v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        superior =(LinearLayout) findViewById(R.id.superior);
        inferior =(LinearLayout) findViewById(R.id.inferior);
        cargarData();
        cargarListaEjemplos();
        desplegarEx();
    }

    public void cargarData()
    {
        int a =0;
        listado = Controlador.ModuloEjemplo(this);
    }

    public List<String> crearListaNumeros(String s)
    {
        Random aleatorio = new Random();
        int num;
        List<Integer> numeros = new ArrayList<>();
        List<String> listaPalabras = new ArrayList<>();
        String ej[]= s.split(" ");
        while (numeros.size() < ej.length)
        {
            num = aleatorio.nextInt(ej.length);
            if(!numeros.contains(num))
            {
              numeros.add(num);
            }
        }
        for(Integer nume: numeros)
        {
            listaPalabras.add(ej[nume]);
        }
        return  listaPalabras;
    }

    public void cargarListaEjemplos()
    {
        String ejemplo = "";
        String frase[];
        listaAleatoria= new ArrayList<>();
        listaOrganizada= new ArrayList<>();
        for(Essential ess: listado)
        {
            ejemplo = ess.getExample();
            listaAleatoria.add(crearListaNumeros(ejemplo));
        }
        for(Essential ess: listado)
        {
          frase= ess.getExample().split(" ");
          listaOrganizada.add(Arrays.asList(frase));
        }
    }

    public void desplegarEx()
    {

        List<String> lista = listaAleatoria.get(orden);
        for(String s: lista)
        {
            crearTextViewSuperior(s);
        }
    }

    public void crearTextViewSuperior(String s)
    {
        textViewSuperior = new TextView(this);
        textViewSuperior.setText(s);
        textViewSuperior.setPadding(16,4,16,4);
        textViewSuperior.setTextSize(14);
        superior.addView(textViewSuperior);

        textViewSuperior.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TextView tv = (TextView) v;
                String texto = tv.getText().toString();
                crearTextViewInferior(texto);
                superior.removeView(v);
            }
        });

    }

    public void crearTextViewInferior(String s)
    {
        textViewInferior = new TextView(this);
        textViewInferior.setText(s);
        textViewInferior.setPadding(16,4,16,4);
        textViewInferior.setTextSize(14);
        inferior.addView(textViewInferior);
        textViewInferior.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TextView tv = (TextView) v;
                String texto = tv.getText().toString();
                crearTextViewSuperior(texto);
                inferior.removeView(v);
            }
        });

    }

    public void comparar(View view)
    {
        int a =0;
        widgets = new ArrayList<>();
        List<String> lista = listaOrganizada.get(orden);
        int contador =0;
        boolean flag =false;
        if(superior.getChildCount()==0)
        {
            while (contador< inferior.getChildCount())
            {
                v = (TextView) (inferior.getChildAt(contador));
                widgets.add(v.getText().toString());
                contador++;
            }
            contador=0;
            while (contador<widgets.size())
            {
                if(widgets.get(contador).equals(lista.get(contador)))
                {
                    flag = true;
                }
                else
                {
                    flag = false;
                    break;
                }
                contador ++;
            }

            if(flag==false)
            {
                Toast.makeText(this, "No son iguales", Toast.LENGTH_SHORT).show();
            }
            else
            {
                reniniar();
            }
        }
        else
        {
            Toast.makeText(this, "You haven't finished yet", Toast.LENGTH_SHORT).show();
        }
    }

    public void reniniar()
    {
        orden++;
        inferior.removeAllViews();
        superior.removeAllViews();
        desplegarEx();
    }
}








