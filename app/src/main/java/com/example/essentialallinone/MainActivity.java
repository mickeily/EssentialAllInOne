package com.example.essentialallinone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
    private static int objetivo=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //metodoDirecto();
        //prueba(new View(this));
        //complete();

    }
    public void metodoDirecto()
    {
        Intent activate = new Intent(this, Activate.class);
        startActivity(activate);
    }
    public void iniciar(View ve)
    {
        objetivo = Selector.examinar(this);
        switch (objetivo)
        {
            case 0:
            case 9:
            {
                Intent example = new Intent(this, Example.class);
                startActivity(example);
                break;
            }
            case 8:
            {
                Intent complete = new Intent(this, Complete.class);
                startActivity(complete);
                break;
            }
            case 7:
            {
                Intent multichoise = new Intent(this, MultiChoise.class);
                startActivity(multichoise);
                break;
            }
            case 5:
            case 6:
            {
                Intent listeningAndReading = new Intent(this, ListeningAndReading.class);
                startActivity(listeningAndReading);
                break;
            }
            case 4:
            {
                Intent match = new Intent(this, Match.class);
                startActivity(match);
                break;
            }
            case 3:
            {
                Intent hanged = new Intent(this, Hanged.class);
                startActivity(hanged);
                break;
            }

            case 2:
            {
                Intent activate = new Intent(this, Activate.class);
                startActivity(activate);
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

    public  static int getObjetivo()
    {
        return objetivo;
    }



}