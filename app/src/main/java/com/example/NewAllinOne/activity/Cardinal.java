package com.example.NewAllinOne.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.NewAllinOne.Essential;
import com.example.NewAllinOne.controlador.Controlador;
import com.example.NewAllinOne.utility.Fecha;
import com.example.NewAllinOne.utility.Reproductor;
import com.example.essentialallinone.R;

import java.util.ArrayList;
import java.util.List;

public class Cardinal extends AppCompatActivity {
    private List<Contenido> database;
    private int orden=0;
    EditText texto;
    TextView preg2;
    TextView respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardinal);
        preg2 = (TextView)findViewById(R.id.preg2);
        cargar();
        reproducir();
        showAnswer();
    }

    private void cargar()
    {

        database = new ArrayList<>();
        database = Controlador.getFilteredDatabase(this);
    }

    public void reproducir()
    {
        String word= database.get(orden).getWord()+".mp3";
        Reproductor.reproducir(word,this);

    }
    public void comprobar(View view)
    {
        respuesta=(TextView)findViewById(R.id.respuesta);
        texto = (EditText) findViewById(R.id.texto_principal);

        if(texto.getText().toString().equalsIgnoreCase(""))
        {
            reproducir();
        }
        else
        {
            if(texto.getText().toString().equalsIgnoreCase(database.get(orden).getWord()))
            {
                texto.setText("");
                respuesta.setText("");
                actualizar(1);
            }
            else
            {
                texto.setText("");
                respuesta.setText(database.get(orden).getDefiniton());
                actualizar(-1);
            }
        }
    }

    private void actualizar(int incremento) {

        database.get(orden).setPuntuation(database.get(orden).getPuntuation() + 1);

        if (incremento == -1)
        {
            database.get(orden).setFailed(database.get(orden).getFailed() + 1);
        }
        database.get(orden).setDate(Fecha.getFehaHoy());

        if (orden < database.size()-1)
        {
            reiniciar();
        }
        else
        {
            guardar();
        }
    }

    private void reiniciar()
    {
        orden++;
        reproducir();
        showAnswer();
    }
    private void guardar()
    {
        Controlador.guardar(this, database,12);
        this.finish();
    }

    private void showAnswer()
    {
        preg2.setText(Hanged.convertSentence(database.get(orden).getDefiniton(),database.get(orden).getWord()));
    }
}