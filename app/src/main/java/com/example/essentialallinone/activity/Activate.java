package com.example.essentialallinone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.essentialallinone.Data.Data;
import com.example.essentialallinone.Data.DataManager;
import com.example.essentialallinone.Essential;
import com.example.essentialallinone.R;
import com.example.essentialallinone.controlador.Controlador;
import com.example.essentialallinone.utility.Const;
import com.example.essentialallinone.utility.Fecha;
import com.example.essentialallinone.utility.Reproductor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Activate extends AppCompatActivity {
    private static List<Contenido> database;
    private static List<Essential> listadoCompleto = new ArrayList<>();
    EditText texto;
    TextView respuesta;
    TextView preg;
    private int objetivo;
    Random aleatorio = new Random();
    private int tablaPosiciones[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate);
        respuesta=(TextView)findViewById(R.id.respuesta);
        preg=(TextView)findViewById(R.id.preg);
        cargar();
        inicializarTablaPosiciones();
        reproducir();
        showAnswer();

    }

    private void cargar()
    {
        database = new ArrayList<>();
        database = Controlador.getFilteredDatabase();
    }

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

    public void reproducir()
    {
        String word= database.get(objetivo).getWord()+".mp3";
        Reproductor.reproducir(word,this);

    }
    public void comprobar(View view)
    {

       texto = (EditText) findViewById(R.id.texto_principal);

       if(texto.getText().toString().equalsIgnoreCase(""))
       {
          reproducir();
       }
       else
       {
           if(texto.getText().toString().equalsIgnoreCase(database.get(objetivo).getWord()))
           {
               texto.setText("");
               respuesta.setText("");
               actualizar();
               reiniciar();
           }
           else
           {
               texto.setText("");
               respuesta.setText(database.get(objetivo).getWord());
               reiniciar();

           }
       }
    }

    private void actualizar()
    {
        tablaPosiciones[objetivo]++;
    }

    private void guardar()
    {
        Controlador.guardar(this,database,1);
        this.finish();
    }

    private void reiniciar()
    {
        if(comprobarSiFinalizar()==false)
        {
            seleccionarElemento();
            reproducir();
            showAnswer();
        }
        else
        {
            guardar();
            //Toast.makeText(this,"Finish",Toast.LENGTH_LONG).show();
        }
    }

    public boolean comprobarSiFinalizar()
    {
        boolean flag = true;
        for(int puntuacion: tablaPosiciones)
        {
            if(puntuacion<4)
            {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public void showAnswer()
    {
       preg.setText(Hanged.convertSentence(database.get(objetivo).getMeaning(),database.get(objetivo).getWord()));
    }

/*
    private void guardar()
    {

        for(Essential ess: listado)
        {

            ess.setStatusActive(2);
            ess.setStatusHang(2);
            ess.setDate(Fecha.getFehaHoy());
        }
        DataManager.update(listado,this);
        this.finish();
    }

 */


}