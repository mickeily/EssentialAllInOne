package com.example.NewAllinOne;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import com.example.NewAllinOne.Data.Data;
import com.example.NewAllinOne.activity.Activate;
import com.example.NewAllinOne.activity.Complete;
import com.example.NewAllinOne.activity.Contenido;
import com.example.NewAllinOne.activity.Definition;
import com.example.NewAllinOne.activity.Example;
import com.example.NewAllinOne.activity.Hanged;
import com.example.NewAllinOne.activity.Listen;
import com.example.NewAllinOne.activity.Match;
import com.example.NewAllinOne.activity.MultiChoice;
import com.example.NewAllinOne.activity.Read;
import com.example.NewAllinOne.activity.ReadComplex;
import com.example.NewAllinOne.activity.TranscriptionMc;
import com.example.NewAllinOne.activity.Translation;
import com.example.NewAllinOne.utility.Selector;
import com.example.essentialallinone.R;
import com.example.NewAllinOne.controlador.Controlador;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private  static List<Contenido> listaDeContenido;
    private  static  Button btnListen, btnRead,btnMultiChoice,btnTranslationMc,btntMatch,btntTranslation,btntExample;
    private  static Button btntDefinition,btnComplete,btntReadComplex,btntHanged,btntActivate,btntCardinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        takePermission();
        iniciar();


    }
    public void iniciar()
    {
        inicializarBotones();

        listaDeContenido = Controlador.getFilteredDatabase(this);
        activarBotones();
        activarBotonCardinal();
    }
    public void read(View view)
    {
        Intent read = new Intent(this, Read.class);
        startActivity(read);
    }

    public void listen(View view)
    {
        Intent listen = new Intent(this, Listen.class);
        startActivity(listen);
    }

    public void multiChoise(View view)
    {
        Intent multiChoice = new Intent(this, MultiChoice.class);
        startActivity(multiChoice);
    }

    public void transMc(View view)
    {
        Intent transMc = new Intent(this, TranscriptionMc.class);
        startActivity(transMc);
    }

    public void definition(View view)
    {
        Intent definition = new Intent(this, Definition.class);
        startActivity(definition);
    }

    public void example(View view)
    {
        Intent example = new Intent(this, Example.class);
        startActivity(example);
    }

    public void translation(View view)
    {
        Intent translation = new Intent(this, Translation.class);
        startActivity(translation);
    }
    public void readComplex(View view)
    {
        Intent readComplex = new Intent(this, ReadComplex.class);
        startActivity(readComplex);
    }
    public void hanged(View view)
    {
        Intent hanged = new Intent(this, Hanged.class);
        startActivity(hanged);
    }
    public void activate(View view)
    {
        Intent activate = new Intent(this, Activate.class);
        startActivity(activate);
    }
    public void match(View view)
    {
        Intent match = new Intent(this, Match.class);
        startActivity(match);
    }

    public void complete(View view) {
        Intent complete = new Intent(this, Complete.class);
        startActivity(complete);
    }



    public void takePermissions()
    {
        if (isPermissionsGranted())
        {
            //Toast.makeText(this,"Permission Already Granted",Toast.LENGTH_LONG).show();
        }
        else
        {
            takePermission();

        }
    }

    private boolean isPermissionsGranted()
    {
        if(Build.VERSION.SDK_INT==Build.VERSION_CODES.R)
        {
            return Environment.isExternalStorageManager();
        }
        else
        {
            int readExternalStoragePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            return  readExternalStoragePermission== PackageManager.PERMISSION_GRANTED;
        }
    }

    private void takePermission()
    {
        if(Build.VERSION.SDK_INT==Build.VERSION_CODES.R)
        {
            try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(String.format("package:%s",getApplicationContext().getOpPackageName())));
                startActivityForResult(intent,100);
            }catch (Exception e)
            {
                Intent intent = new Intent();
                intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivityForResult(intent,100);
            }
        }else
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},101);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode== RESULT_OK)
        {
            if(requestCode==100)
            {
                if(Build.VERSION.SDK_INT==Build.VERSION_CODES.R)
                {
                    if(Environment.isExternalStorageManager())
                    {
                        //Toast.makeText(this,"Permission Granted in android 11",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        takePermission();
                    }
                }
            }
        }
    }

    @Override public void onRequestPermissionsResult(int requestCode, @Nullable String[] permission, @Nullable int[] grantedResults)
    {
        super.onRequestPermissionsResult(requestCode,permission,grantedResults);
        if(grantedResults.length>0)
        {
            if (requestCode==101)
            {
                boolean readExternalStorage = grantedResults[0] == PackageManager.PERMISSION_GRANTED;
                if(readExternalStorage)
                {
                    //Toast.makeText(this,"Read Permission is Granted in android 10 or below",Toast.LENGTH_LONG).show();
                }
                else
                {
                    takePermission();
                }
            }

        }

    }

    private void inicializarBotones()
    {
        btnListen =(Button) findViewById(R.id.btn_listen);
        btnRead =(Button) findViewById(R.id.btn_read);
        btnMultiChoice =(Button) findViewById(R.id.btn_multichoice);
        btntTranslation =(Button) findViewById(R.id.btn_translation);
        btntMatch =(Button) findViewById(R.id.btn_match);
        btnTranslationMc =(Button) findViewById(R.id.btn_transmc);
        btntExample =(Button) findViewById(R.id.btn_example);
        btntDefinition =(Button) findViewById(R.id.btn_definiton);
        btnComplete =(Button) findViewById(R.id.btn_complete);
        btntReadComplex =(Button) findViewById(R.id.btn_read_complex);
        btntHanged =(Button) findViewById(R.id.btn_hanged);
        btntActivate =(Button) findViewById(R.id.btn_activate);
        btntCardinal =(Button) findViewById(R.id.btn_cardinal);
        btntCardinal.setEnabled(false);

    }
    public static void activarBotones()
    {
        int a =0;
        Contenido cont = listaDeContenido.get(0);

        if(cont.getStRead()==0) btnRead.setTextColor(Color.BLUE);
        else  btnRead.setTextColor(Color.RED);

        if(cont.getStListen()==0) btnListen.setTextColor(Color.BLUE);
        else btnListen.setTextColor(Color.RED);

        if(cont.getStMultiChoice()==0) btnMultiChoice.setTextColor(Color.BLUE);
        else  btnMultiChoice.setTextColor(Color.RED);

        if(cont.getStTranslationMc()==0) btnTranslationMc.setTextColor(Color.BLUE);
        else btnTranslationMc.setTextColor(Color.RED);

        if(cont.getStMatch()==0) btntActivate.setTextColor(Color.BLUE);
        else  btntActivate.setTextColor(Color.RED);

        if(cont.getStTranslation()==0) btntTranslation.setTextColor(Color.BLUE);
        else btntTranslation.setTextColor(Color.RED);

        if(cont.getStExample()==0) btntExample.setTextColor(Color.BLUE);
        else btntExample.setTextColor(Color.RED);

        if(cont.getStDefinition()==0) btntDefinition.setTextColor(Color.BLUE);
        else btntDefinition.setTextColor(Color.RED);

        if(cont.getStComplete()==0) btnComplete.setTextColor(Color.BLUE);
        else  btnComplete.setTextColor(Color.RED);

        if(cont.getStReadComplex()==0) btntReadComplex.setTextColor(Color.BLUE);
        else btntReadComplex.setTextColor(Color.RED);

        if(cont.getStHanged()==0) btntHanged.setTextColor(Color.BLUE);
        else  btntHanged.setTextColor(Color.RED);

        if(cont.getStActivate()==0) btntActivate.setTextColor(Color.BLUE);
        else btntActivate.setTextColor(Color.RED);

        if(cont.getStMatch()==0) btntMatch.setTextColor(Color.BLUE);
        else btntMatch.setTextColor(Color.RED);
    }

    public static void activarBotonCardinal()
    {
        int a =0;
        Contenido cont = listaDeContenido.get(0);
        if(     cont.getStRead()==1 &&
                cont.getStListen()==1 &&
                cont.getStMultiChoice()==1 &&
                cont.getStTranslationMc()==1 &&
                cont.getStMatch()==1 &&
                cont.getStExample()==1 &&
                cont.getStDefinition()==1 &&
                cont.getStComplete()==1 &&
                cont.getStReadComplex()==1 &&
                cont.getStHanged()==1 &&
                cont.getStActivate()==1)
        {
           btntCardinal.setEnabled(true);
        }


    }
}