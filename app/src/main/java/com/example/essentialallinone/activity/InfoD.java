package com.example.essentialallinone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.essentialallinone.R;
import com.example.essentialallinone.controlador.Controlador;

import java.util.ArrayList;
import java.util.List;

public class InfoD extends AppCompatActivity {

    LinearLayout b1,b2,b3,b4,b5,b6;


    private static List<Contenido> listado = new ArrayList<>();
    int[][] avance =new int[8][20];
    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_d);
        b1 = (LinearLayout)findViewById(R.id.b1);
        b2 = (LinearLayout)findViewById(R.id.b2);
        b3 = (LinearLayout)findViewById(R.id.b3);
        b4 = (LinearLayout)findViewById(R.id.b4);
        b5 = (LinearLayout)findViewById(R.id.b5);
        b6 = (LinearLayout)findViewById(R.id.b6);



        cargar();
        setAvance();
        displayAvance();
    }

    private void cargar()
    {
        listado = Controlador.getDatabase(this);
    }

    public void setAvance()
    {

        for(Contenido cont: listado) {
            avance[Integer.parseInt(cont.getBook())][cont.getPointPrincipal()] += 1;


        }
    }

    public void displayAvance()
    {
        for(int i=0;i<19;i++)
        {
           if(avance[1][i]!=0)
           {
              createTextView(i,b1,avance[1][i]);
           }
        }

        for(int i=0;i<19;i++)
        {
            if(avance[2][i]!=0)
            {
                createTextView(i,b2,avance[2][i]);
            }
        }

        for(int i=0;i<19;i++)
        {
            if(avance[3][i]!=0)
            {
                createTextView(i,b3,avance[3][i]);
            }
        }
        for(int i=0;i<19;i++)
        {
            if(avance[4][i]!=0)
            {
                createTextView(i,b4,avance[4][i]);
            }
        }

        for(int i=0;i<19;i++)
        {
            if(avance[5][i]!=0)
            {
                createTextView(i,b5,avance[5][i]);
            }
        }

        for(int i=0;i<19;i++)
        {
            if(avance[6][i]!=0)
            {
                createTextView(i,b6,avance[6][i]);
            }
        }
    }

    private void createTextView(int avance, LinearLayout linearLayout, int cant)
    {
        texto = new TextView(this);
        texto.setText(avance+"\t\t"+ cant);
        linearLayout.addView(texto);
    }
}

