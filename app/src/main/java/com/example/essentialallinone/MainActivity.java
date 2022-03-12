package com.example.essentialallinone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.essentialallinone.activity.Activate;
import com.example.essentialallinone.activity.Cardinal;
import com.example.essentialallinone.activity.Complete;
import com.example.essentialallinone.activity.Termino;
import com.example.essentialallinone.activity.Definition;
import com.example.essentialallinone.activity.Example;
import com.example.essentialallinone.activity.Hanged;
import com.example.essentialallinone.activity.ListeningAndReading;
import com.example.essentialallinone.activity.Match;
import com.example.essentialallinone.activity.MultiChoise;
import com.example.essentialallinone.utility.Selector;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static int objetivo;
    public static final int CANTIDADCAGAR = 20;
    public static final int ROUNDS =5;
    private static List<Termino> cuentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //prueba();
        complete();

    }
    public void prueba()
    {
        objetivo = Selector.examinar(this);
        switch (objetivo)
        {
            case 0:
            {
                Intent example = new Intent(this, Example.class);
                startActivity(example);
                break;
            }
            case 9:
            {
                Intent definition = new Intent(this, Definition.class);
                startActivity(definition);
                break;
            }
            case 8:
            {
                Intent multiChoise = new Intent(this, MultiChoise.class);
                startActivity(multiChoise);
                break;
            }
            case 7:
            case 6:
            {
                Intent listeningAndReading = new Intent(this, ListeningAndReading.class);
                startActivity(listeningAndReading);
                break;
            }
            case 5:
            {
                Intent match = new Intent(this, Match.class);
                startActivity(match);
                break;
            }
            case 4:
            {
                Intent hanged = new Intent(this, Hanged.class);
                startActivity(hanged);
                break;
            }
            case 3:
            {
                Intent activate = new Intent(this, Activate.class);
                startActivity(activate);
                break;
            }

            case 2:
            {
                Toast.makeText(this,"entro en 1",Toast.LENGTH_LONG).show();
                break;
            }
            case 1:
            {
                Intent cardinal = new Intent(this, Cardinal.class);
                startActivity(cardinal);
                break;
            }
        }
    }

    public  int getObjetivo()
    {
        return objetivo;
    }

    public void complete()
    {
        Intent complete = new Intent(this, Complete.class);
        startActivity(complete);
        //cuentos = new ArrayList<>();
        //cuentos = Controlador.cargarCuentos(this);
        //int a =0;

    }
}