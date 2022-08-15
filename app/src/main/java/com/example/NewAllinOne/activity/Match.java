package com.example.NewAllinOne.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.NewAllinOne.Essential;
import com.example.NewAllinOne.controlador.Controlador;
import com.example.essentialallinone.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Match extends AppCompatActivity {
    private List<Contenido> database;
    private List<Contenido> listadoModificado = new ArrayList<>();
    private List<Essential> listadoCompleto = new ArrayList<>();
    private LinearLayout layoutContenido;
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
        database = new ArrayList<>();
        database = Controlador.getFilteredDatabase(this);
    }

    public void setArchivoModificado() {
        Random randomizer = new Random();
        while (listadoModificado.size() < database.size()) {
            Contenido random = database.get(randomizer.nextInt(database.size()));
            if (!listadoModificado.contains(random)) {
                listadoModificado.add(random);
            }
        }
    }

    public void proyectarContenido() {
        int contador = 0;
        String oracion="";
        while (contador < database.size()) {
            oracion = convertSentence(listadoModificado.get(contador).getDefiniton(),listadoModificado.get(contador).getWord());
            crearComponentes(oracion, database.get(contador).getWord());
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

        for(Contenido cont: listadoModificado)
        {
            String oracion = convertSentence(cont.getDefiniton(),cont.getWord());
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
        for(Contenido cont: listadoModificado)
        {
            if(def.equalsIgnoreCase(cont.getWord()))
            {
                orden = database.indexOf(cont);
                break;
            }
        }

    }

    public void comparar(View view)
    {
        int acomulador=0;
        int contador =0;
        while (contador<database.size())
        {
            if(!database.get(contador).equals(listadoModificado.get(contador)))
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
        Controlador.guardar(this, database,4);
        this.finish();
    }

    public String convertSentence(String oracion,String palabra)
    {
        String word ="";
        String sentence = oracion.toLowerCase();
        word = sentence.replace(palabra,"______");
        return word;
    }
}