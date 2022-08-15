package com.example.NewAllinOne.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.NewAllinOne.Essential;
import com.example.NewAllinOne.controlador.Controlador;
import com.example.NewAllinOne.utility.Reproductor;
import com.example.essentialallinone.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Activate extends AppCompatActivity {
    private static List<Contenido> database;
    private EditText texto;
    private TextView respuesta;
    private TextView preg;
    private int objetivo;
    private Random aleatorio = new Random();
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
        database = Controlador.getFilteredDatabase(this);
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
        Controlador.guardar(this, database,11);
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
            if(puntuacion<2)
            {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public void showAnswer()
    {
        preg.setText(Hanged.convertSentence(database.get(objetivo).getDefiniton(),database.get(objetivo).getWord()));
    }
}