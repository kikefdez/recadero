package com.quique.recadero.Actividades;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.quique.recadero.Adaptadores.FichaAdapter;
import com.quique.recadero.Objetos.Recado;
import com.quique.recadero.R;

import java.util.ArrayList;

public class Ficha extends AppCompatActivity {
    private ArrayList<Recado> listado;
    private int posicion;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("RECADERO - " + getClass().getCanonicalName(), "--- * INICIO DE ACTIVIDAD FICHA");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha);

        Log.d("RECADERO - " + getClass().getCanonicalName(), "--- * RECUPERAMOS INFORMACIÓN DEL BUNDLE");

        Intent datosIntent = getIntent();
        listado = (ArrayList<Recado>) datosIntent.getSerializableExtra("listado");
        posicion = datosIntent.getIntExtra("posicion", 0);

        viewPager = (ViewPager) findViewById(R.id.pagerFicha);
        viewPager.setAdapter(new FichaAdapter(getSupportFragmentManager(), listado));
        viewPager.setCurrentItem(posicion);
    }

    @Override
    public void onBackPressed() {
        Log.d("RECADERO - " + getClass().getCanonicalName(), "--- * DETECTADO EVENTO onBackPressed");
        Intent datosIntent = new Intent(this, Listado.class);
        datosIntent.putExtra("listado", listado);
        this.startActivity(datosIntent);
    }
}
