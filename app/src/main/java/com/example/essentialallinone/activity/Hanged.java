package com.example.essentialallinone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.essentialallinone.Data.Data;
import com.example.essentialallinone.Data.DataManager;
import com.example.essentialallinone.Essential;
import com.example.essentialallinone.R;
import com.example.essentialallinone.controlador.Controlador;
import com.example.essentialallinone.utility.Const;
import com.example.essentialallinone.utility.Fecha;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hanged extends AppCompatActivity {
    private  List<Contenido> database;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hanged);
        prueba= (TextView)findViewById(R.id.porciento);
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
        database = Controlador.getFilteredDatabase();
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
        oracion.setText(convertSentence(database.get(objetivo).getMeaning(),database.get(objetivo).getWord()));
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
                reiniciar();
            }
        }

        if(!mascara.contains(gion))
        {
            actualizar();
            reiniciar();
        }
    }

    public void reiniciar()
    {
        if(comprobarSiFinalizar()==false)
        {
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
        Controlador.guardar(this,database,1);
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
            if(puntuacion<=3)
            {
                flag = false;
                break;
            }
        }
        return flag;
    }
/*
    private void guardar()
    {

        for(Essential ess: listado)
        {

            ess.setStatusHang(1);
            ess.setStatusMatch(2);
            ess.setDate(Fecha.getFehaHoy());
        }
        DataManager.update(listado,this);
        this.finish();
    }

 */
}