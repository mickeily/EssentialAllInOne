package com.example.NewAllinOne.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.NewAllinOne.Essential;
import com.example.NewAllinOne.controlador.Controlador;
import com.example.essentialallinone.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hanged extends AppCompatActivity {
    private List<Contenido> database;
    private  List<Essential> listadoCompleto = new ArrayList<>();
    private LinearLayout layoutGenerico;
    private Random aleatorio = new Random();
    private List<View> vistas = new ArrayList<>();
    private List<Character> palabraEnUso= new ArrayList<>();;
    private List<String> mascara =new ArrayList<>();
    private String espacio = "  ";
    private String gion = "_";
    private int contadorPulsaciones=0;
    private TextView textoTextView;
    private int objetivo=0;
    private int tablaPosiciones[];
    private TextView prueba;
    private TextView textVporciento;
    private  long porcentual;
    private  double malas;
    private double all;
    private int round =2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hanged);
        prueba= (TextView)findViewById(R.id.position);
        textVporciento= (TextView)findViewById(R.id.porciento);
        porcentual= 0;
        malas =0;
        all =0;
        cargar();
        inicializarTablaPosiciones();
        seleccionarElemento();
        desplegarRespuesta();
        crearMascara();
        proyectarMascara();
        crearPalabraEnUso();
    }

    private void cargar()
    {
        database = new ArrayList<>();
        database = Controlador.getFilteredDatabase(this);
    }

    //Este metodo crea la tabla de posiciones del tamano de listado de elementos a procesar
    private void inicializarTablaPosiciones()
    {
        tablaPosiciones = new int[database.size()];
    }


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

    public void desplegarRespuesta()
    {
        TextView oracion = (TextView)findViewById(R.id.text_oracion);
        oracion.setText(convertSentence(database.get(objetivo).getDefiniton(),database.get(objetivo).getWord()));
    }

    public void crearMascara()
    {
        int contador =0;
        mascara.clear();
        while (contador<database.get(objetivo).getWord().length())
        {
            mascara.add(gion);
            contador++;
        }
    }

    public void proyectarMascara()
    {
        TextView masc = (TextView)findViewById(R.id.masc);
        String palabra = "";
        for(String s: mascara)
        {
            palabra += s+espacio;
        }
        masc.setText(palabra.toLowerCase());
    }

    private void crearPalabraEnUso()
    {
        palabraEnUso.clear();
        for(int i =0; i<database.get(objetivo).getWord().length();i++)
        {
            palabraEnUso.add(database.get(objetivo).getWord().charAt(i)) ;
        }
    }

    public void evaluar(View view)
    {

        vistas.add(view);
        textoTextView = (TextView) view;
        textoTextView.setEnabled(false);
        int contador =0;
        while (contador<palabraEnUso.size())
        {
            if(palabraEnUso.get(contador).toString().equalsIgnoreCase(textoTextView.getText().toString()))
            {
                mascara.set(contador,textoTextView.getText().toString());

            }
            contador++;
        }
        proyectarMascara();

        if(!palabraEnUso.contains(textoTextView.getText().toString().toLowerCase().charAt(0)))
        {
            contadorPulsaciones++;
            if(contadorPulsaciones>=7)
            {
                malas++;
                all ++;
                reiniciar();
            }
        }


        if(!mascara.contains(gion))
        {
            all++;
            actualizar();
            reiniciar();
        }
    }

    public void reiniciar()
    {
        if(comprobarSiFinalizar()==false)
        {
            ajustarintentos();
            actualizarPorciento();
            contadorPulsaciones=0;
            activarVistas();
            seleccionarElemento();
            desplegarRespuesta();
            crearMascara();
            proyectarMascara();
            crearPalabraEnUso();
        }
        else
        {
            desactivarTeclado();
            guardar();
            //Toast.makeText(this,"Finish",Toast.LENGTH_LONG).show();
        }
    }

    public void actualizar()
    {
        String p = "";
        tablaPosiciones[objetivo]++;

        for(int num :tablaPosiciones)
        {
            p+=(num+"");
        }
        prueba.setText(p);



    }

    private void activarVistas()
    {
        for(View view: vistas)
        {
            view.setEnabled(true);
        }
        vistas.clear();
    }

    private void guardar()
    {
        Controlador.guardar(this, database,10);
        this.finish();
    }

    private void desactivarTeclado()
    {
        int contador =0;
        layoutGenerico = (LinearLayout)findViewById(R.id.q_to_p);
        while (contador<layoutGenerico.getChildCount())
        {
            layoutGenerico.getChildAt(contador).setEnabled(false);
            contador++;
        }
        contador=0;
        layoutGenerico = (LinearLayout)findViewById(R.id.a_to_l);
        while (contador<layoutGenerico.getChildCount())
        {
            layoutGenerico.getChildAt(contador).setEnabled(false);
            contador++;
        }
        contador=0;
        layoutGenerico = (LinearLayout)findViewById(R.id.z_to_m);
        while (contador<layoutGenerico.getChildCount())
        {
            layoutGenerico.getChildAt(contador).setEnabled(false);
            contador++;
        }
    }
    public static String convertSentence(String oracion,String palabra)
    {
        String word ="";
        String sentence = oracion.toLowerCase();
        word = sentence.replace(palabra,"______");
        return word;
    }

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

    public void actualizarPorciento()
    {
        double percent  = malas/all;

        porcentual = Math.round(100-(percent*100));
        textVporciento.setText(porcentual+"");
    }

    public int ajustarintentos()
    {
        int puntos =0;
        int retorno =0;
        for(int puntuacion: tablaPosiciones)
        {
            if(puntuacion>puntos)
            {
                puntos = puntuacion;
            }
        }

        retorno = 7-puntos;
        return  retorno;
    }
}