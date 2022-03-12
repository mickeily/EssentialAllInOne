package com.example.essentialallinone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.essentialallinone.Data.Data;
import com.example.essentialallinone.Essential;
import com.example.essentialallinone.R;
import com.example.essentialallinone.controlador.Controlador;
import com.example.essentialallinone.utility.Reproductor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Definition extends AppCompatActivity {
    private static List<Essential> listado = new ArrayList<>();
    private static List<Essential> listadoCompleto = new ArrayList<>();
    private List<List<String>> listaAleatoria;
    private List<List<String>> listaOrganizada;
    private ArrayList<String> widgets;
    private int orden =0;
    private  Essential essential= new Essential();
    private String path = "/sdcard/DB/DB.csv";

    LinearLayout inferior;
    LinearLayout superior;
    TextView textViewInferior;
    TextView textViewSuperior;
    TextView v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition);
        superior =(LinearLayout) findViewById(R.id.superior);
        inferior =(LinearLayout) findViewById(R.id.inferior);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        cargarData();
        cargarListaEjemplos();
        desplegarEx();
    }


    public void cargarData()
    {
        listado = Controlador.moduloDefinition(this);
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
            ejemplo = ess.getMeaning();
            listaAleatoria.add(crearListaNumeros(ejemplo));
        }
        for(Essential ess: listado)
        {
            frase= ess.getMeaning().split(" ");
            listaOrganizada.add(Arrays.asList(frase));
        }
    }

    public void desplegarEx()
    {
        View v = new View(this);
        reproducir();
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
        if(superior.getChildCount()==0)
        {
            widgets = new ArrayList<>();
            List<String> lista = listaOrganizada.get(orden);
            int contador =0;
            boolean flag =false;
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
                Toast.makeText(this, "Don't Match", Toast.LENGTH_SHORT).show();
            }
            else
            {
                reniniar();
            }
        }
        else
        {
            reproducir();
        }
    }

    public void reniniar()
    {
        if(orden>= listado.size()-1)
        {

            guardar();
            Toast.makeText(this, "Saved file", Toast.LENGTH_SHORT).show();
        }
        else
        {
            orden++;
            inferior.removeAllViews();
            superior.removeAllViews();
            desplegarEx();
        }

    }

    private void reproducir()
    {
        Reproductor.reproducir(listado.get(orden).getWord()+"_D.mp3",this);
    }

    public void prueba(View v)
    {
        reproducir();
    }

    private void guardar()
    {
        listadoCompleto = Controlador.getListadoPrincipal(this);
        for(Essential ess: listado)
        {
            listadoCompleto.get(ess.getOrder()).setStatusDefinition(1);
            listadoCompleto.get(ess.getOrder()).setStatusExample(2);
        }
        Data.saveFile(listadoCompleto,path,this);
    }
}
