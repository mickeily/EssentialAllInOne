package com.example.essentialallinone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.essentialallinone.Data.Data;
import com.example.essentialallinone.Essential;
import com.example.essentialallinone.R;
import com.example.essentialallinone.controlador.Controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hanged extends AppCompatActivity {
    private static List<Essential> listado = new ArrayList<>();
    private static List<Essential> listadoEnUso = new ArrayList<>();
    private static List<Essential> listadoCompleto = new ArrayList<>();
    private int listaNumerica[];
    private String path = "/sdcard/DB/DB.csv";
    private LinearLayout layoutGenerico;
    Random aleatorio = new Random();
    List<View> vistas = new ArrayList<>();
    Essential enUso= new Essential();
    List<Character> palabraEnUso;
    List<String> mascara;
    String espacio = "  ";
    private String gion = "_";
    int contadorPulsaciones=0;
    TextView textoTextView;
    int objetivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hanged);
        cargar();
        setListaNumerica();
        seleccionarElemento();
        desplegarRespuesta();
        crearMascara();
        proyectarMascara();
        crearPalabraEnUso();

    }

    private void cargar()
    {
        listado = Controlador.moduloHang(this);
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
    public void desplegarRespuesta()
    {
        TextView oracion = (TextView)findViewById(R.id.text_oracion);
        oracion.setText(convertSentence(enUso.getMeaning(),enUso.getWord()));
    }
    public String convertSentence(String oracion,String palabra)
    {
        String word ="";
        String sentence = oracion.toLowerCase();
        word = sentence.replace(palabra,"______");
        return word;
    }
    public void crearMascara()
    {
        int contador =0;
        mascara= new ArrayList<>();
        while (contador<enUso.getWord().length())
        {
            mascara.add(gion);
            contador++;
        }
    }

    public void crearPalabraEnUso()
    {
        int contador=0;
        palabraEnUso=new ArrayList<>();
        while (contador<enUso.getWord().length())
        {
            palabraEnUso.add(enUso.getWord().charAt(contador));
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
                proyectarMascara();
            }
            contador++;
        }
        if(!mascara.contains(gion))
        {
           actualizar();
        }
        if(!palabraEnUso.contains(textoTextView.getText()))
        {
            contadorPulsaciones++;
            if(contadorPulsaciones>=7)
            {
                reiniciar();
            }
        }
    }

    public void reiniciar()
    {
        contadorPulsaciones=0;
        activarVistas();
        seleccionarElemento();
        desplegarRespuesta();
        crearMascara();
        proyectarMascara();
        crearPalabraEnUso();
    }

    public void actualizar()
    {
        listaNumerica[objetivo]++;
        boolean flag = false;

        for(Integer i: listaNumerica)
        {
            if(i<2)
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
            desactivarTeclado();
            guardar();
            Toast.makeText(this, "Saved file", Toast.LENGTH_SHORT).show();
        }

    }

    public void activarVistas()
    {
        for(View view: vistas)
        {
            view.setEnabled(true);
        }
        vistas.clear();
    }

    private void guardar()
    {
        listadoCompleto = Controlador.getListadoPrincipal(this);
        for(Essential ess: listado)
        {
            listadoCompleto.get(ess.getOrder()).setStatusHang(1);
            listadoCompleto.get(ess.getOrder()).setStatusMatch(2);
            listadoCompleto.get(ess.getOrder()).setStatusComplete(0);
        }
        Data.saveFile(listadoCompleto,path,this);
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
}