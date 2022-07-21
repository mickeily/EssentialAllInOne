package com.example.essentialallinone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.essentialallinone.Data.Data;
import com.example.essentialallinone.Data.DataManager;
import com.example.essentialallinone.Essential;
import com.example.essentialallinone.MainActivity;
import com.example.essentialallinone.R;
import com.example.essentialallinone.controlador.Controlador;
import com.example.essentialallinone.utility.Const;
import com.example.essentialallinone.utility.Fecha;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Complete extends AppCompatActivity {
    private  List<Contenido> database = new ArrayList<>();
    private  List<Essential> listadoCompleto = new ArrayList<>();

    private  List<Termino> listaTerminos = new ArrayList<>();
    private  List<Termino> listaTerminosEsenciales = new ArrayList<>();
    private  List<Termino> listaAlteradaTerminos = new ArrayList<>();

    private Random aleatorio = new Random();

    private TextView texto;
    private LinearLayout linarLayoutPalabras ;
    private ScrollView scrollPalabras;

    private String mascara="_______";
    private String libro="";
    private String unidad ="";

    private int objetivo =0;
    private int estatus =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);
        linarLayoutPalabras = (LinearLayout) findViewById(R.id.palabras);
        scrollPalabras = (ScrollView)findViewById(R.id.scroll_palabras);
        texto = (TextView) findViewById(R.id.texto);
        cargarEssential();
        setLibroUnidad();
        setListaTerminos();
        iniciar();

    }

    public void cargarEssential()
    {

        database = new ArrayList<>();
        database = Controlador.getFilteredDatabase();
    }
    public void setLibroUnidad()
    {
       libro = database.get(0).getBook();
       unidad = database.get(0).getUnit();

    }
    private void setListaTerminos()
    {
        listaTerminos= Controlador.cargarCuentos(this,libro,unidad);
    }

    public void iniciar()
    {
        if(estatus==0)
        {
            scrollPalabras.setVisibility(View.GONE);
            desplegarListaTerminos(listaTerminos);
        }
        else if(estatus==1|| estatus==2||estatus==3)
        {
            scrollPalabras.setVisibility(View.VISIBLE);
            setListaTerminosEsenciales();
            setListaAlteradaTerminos();
            desplegarListaTerminosEsenciales();
            desplegarListaTerminos(listaAlteradaTerminos);
        }
    }

    private void desplegarListaTerminos(List<Termino> terminos)
    {

        String cadena = "";

        for(Termino termino: terminos)
        {
            if(termino.getPalabra().endsWith(".")||termino.getPalabra().endsWith("?") )
            {
                cadena+= termino.getPalabra()+"\n\n";
            }
            else
            {
                cadena+= termino.getPalabra()+" ";
            }
        }
        texto.setText(cadena);
        texto.setTextSize(16);
    }

    private void setListaTerminosEsenciales()
    {
        listaTerminosEsenciales.clear();
        if(estatus==1)
        {
            for(Termino termino: listaTerminos)
            {
                if(termino.getTipo().equals("1"))
                {
                    listaTerminosEsenciales.add(termino);
                }
            }
        }
        else if(estatus==2|| estatus==3)
        {
            int cantidadSeleccionada=0;
            if(estatus==2)
            {
                cantidadSeleccionada = (listaTerminos.size() * Const.PRIMERA_PORCIENTO_PALABRAS_SELECCIONAR) / 100;
            }

            else if(estatus==3) {
                cantidadSeleccionada = (listaTerminos.size() * Const.SEGUNDA_PORCIENTO_PALABRAS_SELECCIONAR) / 100;
            }

            while (listaTerminosEsenciales.size()<cantidadSeleccionada)
            {
                objetivo = aleatorio.nextInt(listaTerminos.size() - 2) + 1;
                if(!listaTerminosEsenciales.contains(listaTerminos.get(objetivo)))
                {
                  listaTerminosEsenciales.add(listaTerminos.get(objetivo));
                }
            }
        }
    }

    private void  setListaAlteradaTerminos()
    {
        listaAlteradaTerminos= Controlador.cargarCuentos(this,libro,unidad);

        for(int i=0;i<listaAlteradaTerminos.size();i++) {
            if (listaTerminosEsenciales.contains(listaTerminos.get(i))) {
                listaAlteradaTerminos.get(i).setPalabra(mascara);
            }
        }
    }

    public void desplegarListaTerminosEsenciales()
    {
        linarLayoutPalabras.removeAllViews();
        List<Integer> numerica = new ArrayList<>();
        numerica = listaNumerica(listaTerminosEsenciales.size());

        for(int i=0; i<listaTerminosEsenciales.size();i++)
        {
            for(int j=0; j<listaTerminosEsenciales.size() && i != j;j++)
            {
                if(listaTerminosEsenciales.get(i).getPalabra().compareToIgnoreCase(listaTerminosEsenciales.get(j).getPalabra())<0)
                {
                    Termino aux = listaTerminosEsenciales.get(i);
                    listaTerminosEsenciales.set(i,listaTerminosEsenciales.get(j));

                    listaTerminosEsenciales.set(j,aux);
                }
            }

        }
        for(Termino termino: listaTerminosEsenciales)
        {
            crearTextView(termino.getPalabra());
        }

    }
    private List<Integer> listaNumerica(int cantidad)
    {
        List<Integer> numerica = new ArrayList<>();

        while (numerica.size()<cantidad)
        {
            objetivo = aleatorio.nextInt(cantidad);
            if(!numerica.contains(objetivo))
            {
                numerica.add(objetivo);
            }

        }
        return numerica;
    }

    public void buscarMascara(String texto)
    {
        int contador =0;
        while (listaAlteradaTerminos.get(contador)!=null)
        {
            if(listaAlteradaTerminos.get(contador).getPalabra().equalsIgnoreCase(mascara))
            {
                listaAlteradaTerminos.get(contador).setPalabra(texto);
                break;
            }
            contador++;
        }
    }

    public boolean comprobar()
    {
        boolean flag=false;
        listaTerminosEsenciales.clear();
        for(int i=0;i<listaAlteradaTerminos.size();i++)
        {
            if(!listaAlteradaTerminos.get(i).getPalabra().equalsIgnoreCase(listaTerminos.get(i).getPalabra()))
            {
                listaAlteradaTerminos.get(i).setPalabra(mascara);
                listaTerminosEsenciales.add(listaTerminos.get(i));
                flag=true;
            }
        }

       return flag;
    }

    public void crearTextView(String texto)
    {
        TextView textView;
        textView = (TextView) new TextView(this);
        textView.setText(texto);
        textView.setPadding(16,24,0,0);
        textView.setTextSize(20);
        linarLayoutPalabras.addView(textView);

        textView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TextView tv = (TextView) v;
                String texto = tv.getText().toString();
                buscarMascara(texto);
                desplegarListaTerminos(listaAlteradaTerminos);

                tv.setVisibility(View.GONE);
            }
        });
    }

    public void check(View view)
    {
        if(estatus==0)
        {
            estatus++;
            iniciar();
        }
        else if(estatus==1|| estatus==2|| estatus==3)
        {
            if(revisarMascara()==false)
            {
                boolean flag = comprobar();
                if(flag)
                {
                    desplegarListaTerminos(listaAlteradaTerminos);
                    desplegarListaTerminosEsenciales();
                }
                else
                {
                    estatus++;
                    iniciar();
                }
            }
            else
            {
                Toast.makeText(this,"You are not done yet",Toast.LENGTH_LONG).show();

            }

        }
        else
        {
            guardar();
        }
    }

    public boolean revisarMascara()
    {
        boolean flag = false;
        for(Termino termino: listaAlteradaTerminos)
        {
            if(termino.getPalabra().equalsIgnoreCase(mascara))
            {
                flag= true;
                break;
            }
        }
        return flag;
    }

    private void guardar()
    {
        Controlador.guardar(this,database,1);
        this.finish();
    }
/*
    private void guardar()
    {

        for(Essential ess: listado)
        {

            ess.setStatusComplete(1);
            ess.setStatusDefinition(2);
            ess.setDate(Fecha.getFehaHoy());
        }
        DataManager.update(listado,this);
        this.finish();
    }

 */
}