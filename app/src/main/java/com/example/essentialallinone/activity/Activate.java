package com.example.essentialallinone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.essentialallinone.Data.Data;
import com.example.essentialallinone.Essential;
import com.example.essentialallinone.R;
import com.example.essentialallinone.controlador.Controlador;
import com.example.essentialallinone.utility.Reproductor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Activate extends AppCompatActivity {
    private static List<Essential> listado = new ArrayList<>();
    private static List<Essential> listadoEnUso = new ArrayList<>();
    private static List<Essential> listadoCompleto = new ArrayList<>();
    private String path = "/sdcard/DB/DB.csv";
    Essential enUso= new Essential();
    EditText texto;
    TextView respuesta;
    private int listaNumerica[];
    private int objetivo;
    Random aleatorio = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate);
        cargar();
        setListaNumerica();
        seleccionarElemento();
        reproducir();
    }

    private void cargar()
    {
        listado = Controlador.ModuloActive(this);
    }

    private void setListaNumerica()
    {
        listaNumerica= new int[listado.size()];
    }

    public void seleccionarElemento()
    {
        int contador =0;
        objetivo = aleatorio.nextInt(listado.size());
        while (contador<listaNumerica.length)
        {
            if(listaNumerica[contador]<listaNumerica[objetivo])
            {
                objetivo= contador;
                break;
            }
            contador++;
        }
        enUso= listado.get(objetivo);

    }
    public void reproducir()
    {
        String word= listado.get(objetivo).getWord()+".mp3";
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
           if(texto.getText().toString().equalsIgnoreCase(listado.get(objetivo).getWord()))
           {
               texto.setText("");
               respuesta.setText("");
               actualizar();
           }
           else
           {
               texto.setText("");
               respuesta.setText(listado.get(objetivo).getWord());
               reiniciar();

           }
       }
    }

    private void actualizar()
    {
        listaNumerica[objetivo]++;
        boolean flag = false;

        for(Integer i: listaNumerica)
        {
            if(i<3)
            {
                flag = true;
                break;
            }
        }
        if(flag==true)
        {
            reiniciar();
        }
        else
        {
            guardar();
            Toast.makeText(this, "Saved file", Toast.LENGTH_SHORT).show();
        }
    }

    private void guardar()
    {
        listadoCompleto = Controlador.getListadoPrincipal(this);
        for(Essential ess: listado)
        {
            listadoCompleto.get(ess.getOrder()).setStatusActive(1);
            listadoCompleto.get(ess.getOrder()).setStatusHang(2);

        }
        Data.saveFile(listadoCompleto,path,this);
    }

    private void reiniciar()
    {
        seleccionarElemento();
        reproducir();
    }


}