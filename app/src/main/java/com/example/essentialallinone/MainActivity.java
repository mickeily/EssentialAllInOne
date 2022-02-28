package com.example.essentialallinone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.example.essentialallinone.Data.Data;
import com.example.essentialallinone.activity.Example;
import com.example.essentialallinone.permission.Permission;
import com.example.essentialallinone.utility.Selector;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prueba();

    }
    public void prueba()
    {
        switch (Selector.examinar(this))
        {
            case 0:
            {
                Intent example = new Intent(this, Example.class);
                startActivity(example);
            }
        }
    }
}