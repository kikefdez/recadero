package com.quique.recadero.Actividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.quique.recadero.AsyncTask.Receptor;
import com.quique.recadero.Objetos.Recado;
import com.quique.recadero.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String urlServer = "http://elrecadero-ebtm.rhcloud.com/ObtenerListaRecados";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Receptor(this).execute(urlServer);
    }
}
