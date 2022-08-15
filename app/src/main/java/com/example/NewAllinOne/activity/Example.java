package com.example.NewAllinOne.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.NewAllinOne.Data.Data;
import com.example.NewAllinOne.Essential;
import com.example.NewAllinOne.controlador.Controlador;
import com.example.NewAllinOne.utility.Const;
import com.example.NewAllinOne.utility.Reproductor;
import com.example.NewAllinOne.utility.Utility;
import com.example.essentialallinone.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Example extends AppCompatActivity {
    //Este atributo carga la lista de objeto que se van a procesar en este modulo
    private static List<Contenido> database;
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
    private  int round =1;
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
    }

    //El metodo cargar carga la informacion correspondiente a este modulo
    private void cargarData()
    {
        database = new ArrayList<>();
        database = Controlador.getFilteredDatabase(this);
        int a =0;
    }

    private void cargarPalabrasConjugadas()
    {
        int a =0;
        palabraConjugadas = new ArrayList<>();
        palabraConjugadas= Data.readFilePalabrasConjugadas(this);
    }
    public void depurarPalabrasConjugada()
    {
        String libro = "";
        String unidad = "";
        libro = database.get(0).getBook();
        unidad = database.get(0).getUnit();
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
        cantidad = database.size()*round;
        desplegarCantidad(cantidad);

    }
    private void desplegarCantidad(int cantidad)
    {
        faltante.setText(cantidad+"");
    }

    //Este metodo crea la tabla de posiciones del tamano de listado de elementos a procesar
    private void inicializarTablaPosiciones()
    {
        tablaPosiciones = new int[database.size()];
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
        listaPalabras = Arrays.asList(database.get(objetivo).getExample().split(" "));
    }

    //Este metodo toma la lista de palabras y la despliega en forma de TextView en la pantalla,
    // sorteada de forma aleatoria con las posiciones de una lista
    private void desplegarTerminos()
    {
        reproducir();
        List<Integer> listaNumerica = Utility.listaNumerica(listaPalabras.size());

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
            textViewSuperior.setTypeface(null, Typeface.BOLD);
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
        String palabra = database.get(objetivo).getWord();
        Reproductor.reproducir(database.get(objetivo).getWord()+"_E.mp3",this);
        //Reproductor.reproducir(database.get(objetivo).getWord()+"_E.mp3",this);
    }
    //Este metodo registra los cambios en la base de datos

    private void guardar()
    {

        Controlador.guardar(this, database,6);
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

}