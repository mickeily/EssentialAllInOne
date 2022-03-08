package com.example.essentialallinone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.essentialallinone.Data.Data;
import com.example.essentialallinone.Essential;
import com.example.essentialallinone.R;
import com.example.essentialallinone.controlador.Controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Match extends AppCompatActivity {
    private static List<Essential> listado = new ArrayList<>();
    private static List<Essential> listadoModificado = new ArrayList<>();
    private static List<Essential> listadoCompleto = new ArrayList<>();
    private String path = "/sdcard/DB/DB.csv";
    LinearLayout layoutContenido;
    private int orden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        layoutContenido= (LinearLayout)findViewById(R.id.contenido);
        cargarData();
        setArchivoModificado();
        proyectarContenido();
    }

    public void cargarData()
    {
        listado = Controlador.ModuloMatch(this);
    }

    public void setArchivoModificado() {
        Random randomizer = new Random();
        while (listadoModificado.size() < listado.size()) {
            Essential random = listado.get(randomizer.nextInt(listado.size()));
            if (!listadoModificado.contains(random)) {
                listadoModificado.add(random);
            }
        }
    }

    public void proyectarContenido() {
        int contador = 0;
        String oracion="";
        while (contador < listado.size()) {
            oracion = convertSentence(listadoModificado.get(contador).getMeaning(),listadoModificado.get(contador).getWord());
            crearComponentes(oracion, listado.get(contador).getWord());
            contador++;
        }
    }

    public void crearComponentes(String definicion, String palabra) {
        try {

            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.removeAllViews();
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            Button txtDef = new Button(this);
            Button txtPal = new Button(this);
            txtPal.setText(palabra);
            txtPal.setAllCaps(false);
            txtDef.setText(definicion);
            txtDef.setAllCaps(false);
            linearLayout.addView(txtPal);
            linearLayout.addView(txtDef);
            layoutContenido.addView(linearLayout);

            txtDef.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button btn = (Button)v;
                    String texto = btn.getText().toString();
                    reubicarDefinicion(texto);
                }
            });

            txtPal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button btn = (Button)v;
                    String texto = btn.getText().toString();
                    buscarOrdenPalabra(texto);

                }
            });

        } catch (Exception e) {
            Toast.makeText(this, "" + e, Toast.LENGTH_LONG).show();
        }
    }

    public void reubicarDefinicion(String def)
    {

        for(Essential cont: listadoModificado)
        {
            String oracion = convertSentence(cont.getMeaning(),cont.getWord());
            if(def.equalsIgnoreCase(oracion))
            {
                listadoModificado.remove(cont);

                listadoModificado.add(orden,cont);
                break;
            }
        }

        layoutContenido.removeAllViews();
        proyectarContenido();
    }

    public void buscarOrdenPalabra(String def)
    {
        for(Essential cont: listadoModificado)
        {
            if(def.equalsIgnoreCase(cont.getWord()))
            {
                orden = listado.indexOf(cont);
                break;
            }
        }

    }

    public void comparar(View view)
    {
        int acomulador=0;
        int contador =0;
        while (contador<listado.size())
        {
            if(!listado.get(contador).equals(listadoModificado.get(contador)))
            {
                acomulador++;
            }
            contador++;
        }

        if(acomulador==0)
        {
            guardar();
            layoutContenido.removeAllViews();
            TextView textView = new TextView(this);
            textView.setText("Good Job!!!");
            textView.setTextSize(48);
            layoutContenido.addView(textView);
        }
        else
        {
            Toast.makeText(this,acomulador+" Are pendding",Toast.LENGTH_LONG).show();
        }
    }

    private void guardar()
    {
        listadoCompleto = Controlador.getListadoPrincipal(this);
        for(Essential ess: listado)
        {
            listadoCompleto.get(ess.getOrder()).setStatusMatch(1);
            listadoCompleto.get(ess.getOrder()).setStatusListen(2);
        }
        Data.saveFile(listadoCompleto,path,this);
    }

    public String convertSentence(String oracion,String palabra)
    {
        String word ="";
        String sentence = oracion.toLowerCase();
        word = sentence.replace(palabra,"______");
        return word;
    }
}