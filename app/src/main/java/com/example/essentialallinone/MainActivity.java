package com.example.essentialallinone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.essentialallinone.activity.Definition;
import com.example.essentialallinone.activity.Example;
import com.example.essentialallinone.activity.ListeningAndReading;
import com.example.essentialallinone.activity.MultiChoise;
import com.example.essentialallinone.utility.Selector;

public class MainActivity extends AppCompatActivity {
    private static int objetivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prueba();

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
        }
    }

    public  int getObjetivo()
    {
        return objetivo;
    }
}