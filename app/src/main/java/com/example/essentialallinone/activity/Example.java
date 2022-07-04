package com.example.essentialallinone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
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
import com.example.essentialallinone.utility.Mapa;
import com.example.essentialallinone.utility.Reproductor;
import com.example.essentialallinone.utility.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Example extends AppCompatActivity {
    //Este atributo carga la lista de objeto que se van a procesar en este modulo
    private static List<Essential> listado = new ArrayList<>();
    //Este atributo carga la base de datos completa
    private static List<Essential> listadoCompleto = new ArrayList<>();
    private List<List<String>> listaAleatoria;
    private List<List<String>> listaOrganizada;
    //esta lista lleva el control de las veces que un elemento de ha utilizado
    private int tablaPosiciones[];
    List<String> listaPalabras;

    private List<PalabraConjugada> palabraConjugadas;
    private List<String> palabraConjugadasResumen;
    private int orden =0;
    private Random aleatorio = new Random();
    int objetivo =0;
    private  int round =2;
    private int cantidad=0;
    //private  Essential essential= new Essential();

    GridLayout inferior;
    GridLayout superior;
    TextView textViewInferior;
    TextView textViewSuperior;
    TextView faltante;
    TextView v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        superior =(GridLayout) findViewById(R.id.superior);
        inferior =(GridLayout) findViewById(R.id.inferior);
        faltante = (TextView)findViewById(R.id.faltante);
        cargarData();
        cargarPalabrasConjugadas();
        depurarPalabrasConjugada();

        setCantidad();
        inicializarTablaPosiciones();
        seleccionarElemento();
        descomponerFrase();
        desplegarTerminos();
        //cargarListaEjemplos();
        //desplegarEx();
    }
    //El metodo cargar carga la informacion correspondiente a este modulo
    private void cargarData()
    {
        int a =0;
        if(MainActivity.getObjetivo()==0)
        {
            listado = Controlador.moduloEjemplo(this);
        }
        else
        {
            listado = Controlador.moduloDefinition(this);
        }

    }

    private void cargarPalabrasConjugadas()
    {

        palabraConjugadas = new ArrayList<>();
        palabraConjugadas= Data.readFilePalabrasConjugadas(this);

    }
    public void depurarPalabrasConjugada()
    {
        String libro = "";
        String unidad = "";
        libro = listado.get(0).getBook();
        unidad = listado.get(0).getUnit();
        palabraConjugadasResumen = new ArrayList<>();

        for(PalabraConjugada pal: palabraConjugadas)
        {
            if(pal.getLibro().equalsIgnoreCase(libro) && pal.getUnidad().equalsIgnoreCase(unidad))
            {
                palabraConjugadasResumen.add(pal.getTermino());
            }
        }
    }

    private void setCantidad()
    {
        cantidad = listado.size()*round;
        desplegarCantidad(cantidad);

    }
    private void desplegarCantidad(int cantidad)
    {
        faltante.setText(cantidad+"");
    }

    //Este metodo crea la tabla de posiciones del tamano de listado de elementos a procesar
    private void inicializarTablaPosiciones()
    {
        tablaPosiciones = new int[listado.size()];
    }

    // Este metodo selecciona un elemento para se procesado, el cual debe haber aparecido menor o igual
    // veces que el que tenga menor aparariciones
    private void seleccionarElemento()
    {
        objetivo = aleatorio.nextInt(tablaPosiciones.length);
        for(int i=0;i<tablaPosiciones.length;i++)
        {
            if(tablaPosiciones[i]<tablaPosiciones[objetivo])
            {
                objetivo=i;
            }
        }
    }

    //Este metodo toma la oracion completa y la descompone en sus diferentes palabras
    private void descomponerFrase()
    {
        listaPalabras = new ArrayList<>();
        if(MainActivity.getObjetivo()==0)
        {
            listaPalabras = Arrays.asList(listado.get(objetivo).getExample().split(" "));
        }
        else
        {
            listaPalabras = Arrays.asList(listado.get(objetivo).getMeaning().split(" "));
        }

    }

    //Este metodo toma la lista de palabras y la despliega en forma de TextView en la pantalla,
    // sorteada de forma aleatoria con las posiciones de una lista
    private void desplegarTerminos()
    {
        reproducir();
        List<Integer> listaNumerica =Utility.listaNumerica(listaPalabras.size());

        for(Integer numero:listaNumerica)
        {
            crearTextViewSuperior(listaPalabras.get(numero));
        }
    }
    //Este metodo compara el texto que se organizo con la fuente original para ver sin coinciden
    public void comprobar(View view)
    {
        if(superior.getChildCount()>0)
        {
            reproducir();
        }
        else
        {
            int contador =0;
            List<String>widgets = new ArrayList<>();
            boolean flag = false;
            while (contador< inferior.getChildCount())
            {
                v = (TextView) (inferior.getChildAt(contador));
                widgets.add(v.getText().toString());
                contador++;
            }

            for(int i =0; i<widgets.size();i++)
            {
                if(!widgets.get(i).equals(listaPalabras.get(i)))
                {
                  flag = true;
                  break;
                }
            }
            if(flag==true)
            {
                Toast.makeText(this,"Don't Match",Toast.LENGTH_LONG).show();
            }
            else
            {
                cantidad--;
                desplegarCantidad(cantidad);
                tablaPosiciones[objetivo]++;

                inferior.removeAllViews();
                reiniciar();
            }
        }




    }

    //Este metodo comprueba si aun queda rondas que ejecutar, de eser asi reincia de nuevo
    public void reiniciar()
    {
        if(comprobarSiFinalizar()==false)
        {
            seleccionarElemento();
            descomponerFrase();
            desplegarTerminos();
        }
        else
        {
            guardar();
        }

    }

    //Este metodo comprueba si ya ha finaliado la cantidad de ejecuciones
    public boolean comprobarSiFinalizar()
    {
        boolean flag = true;
        for(int puntuacion: tablaPosiciones)
        {
            if(puntuacion<round)
            {
               flag = false;
               break;
            }
        }
        return flag;
    }
    //Este metodo crea una textView en el layaut superior
    public void crearTextViewSuperior(String s)
    {
        String pal = "";
        textViewSuperior = new TextView(this);
        textViewSuperior.setText(s);
        textViewSuperior.setPadding(16,16,16,4);
        textViewSuperior.setTextSize(Const.TAMAGNO_FUENTE);
        if(buscarTermino(s))
        {
            textViewSuperior.setTextSize(Const.TAMAGNO_FUENTE+4);
            textViewSuperior.setTextColor(Color.RED);
            textViewSuperior.setTypeface(null,Typeface.BOLD);
        }

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
    //Este metodo crea una textView en el layaut inferior
    public void crearTextViewInferior(String s)
    {
        textViewInferior = new TextView(this);
        textViewInferior.setText(s);
        textViewInferior.setPadding(16,4,16,4);
        textViewInferior.setTextSize(Const.TAMAGNO_FUENTE);
        if(buscarTermino(s))
        {
            textViewInferior.setTextSize(Const.TAMAGNO_FUENTE+4);
            textViewInferior.setTextColor(Color.RED);
            textViewInferior.setTypeface(null,Typeface.BOLD);
        }
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

    //Este metodo reproduce un audio especifico
    private void reproducir()
    {
        String palabra = listado.get(objetivo).getWord();
        if(MainActivity.getObjetivo()==0)
        {
            Reproductor.reproducir(listado.get(objetivo).getWord()+"_E.mp3",this);
        }
        else
        {
            Reproductor.reproducir(listado.get(objetivo).getWord()+"_D.mp3",this);
        }

    }
    //Este metodo registra los cambios en la base de datos


    private void guardar()
    {
        listadoCompleto = Controlador.getListadoPrincipal(this);
        for(Essential ess: listado)
        {
            if(MainActivity.getObjetivo()==0)
            {
                listadoCompleto.get(ess.getOrder()).setStatusExample(1);
                //listadoCompleto.get(ess.getOrder()).setStatusDefinition(1);
            }
            else
            {
                listadoCompleto.get(ess.getOrder()).setStatusExample(2);
                listadoCompleto.get(ess.getOrder()).setStatusDefinition(1);
            }

            listadoCompleto.get(ess.getOrder()).setDate(Fecha.getFehaHoy());

        }

        Data.saveFile(listadoCompleto, Const.URL_DATABASE,this);
        this.finish();
    }

    private boolean buscarTermino(String termino)
    {
        if(palabraConjugadasResumen.contains(termino.toLowerCase()))
        {
           return true ;
        }
        else {
            return false;
        }
    }
/*
    private void guardar()
    {

        for(Essential ess: listado)
        {
            if(MainActivity.getObjetivo()==0)
            {
                ess.setStatusExample(1);
            }
            else
            {
                ess.setStatusExample(2);
                ess.setStatusDefinition(1);
            }
            ess.setDate(Fecha.getFehaHoy());

        }
        DataManager.update(listado,this);
        this.finish();
    }

 */



}








