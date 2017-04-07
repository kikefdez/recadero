package com.quique.recadero.Actividades;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.quique.recadero.Adaptadores.ListadoAdapter;
import com.quique.recadero.Objetos.Recado;
import com.quique.recadero.R;
import com.quique.recadero.Utilidades.Ordenar;

import java.util.ArrayList;
import java.util.Collections;

public class Listado extends AppCompatActivity {
    private ArrayList<Recado> listado;
    private ListView listViewListado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("RECADERO - " + getClass().getCanonicalName(), "--- * INICIO DE ACTIVIDAD LISTADO");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        Log.d("RECADERO - " + getClass().getCanonicalName(), "--- * RECUPERAMOS EL LISTADO DESCARGADO");
        Intent intent = getIntent();
        listado = (ArrayList<Recado>) intent.getSerializableExtra("listado");

        Collections.sort(listado, new Ordenar("creacionASC"));

        Log.d("RECADERO - " + getClass().getCanonicalName(), "--- * ASOCIAMOS EL ADAPTER AL LISTVIEW CON LAS TAREAS");
        listViewListado = (ListView) findViewById(R.id.lvListado);
        listViewListado.setAdapter(new ListadoAdapter(this, listado));

        listViewListado.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Log.d("RECADERO - " + getClass().getCanonicalName(), "--- * DETECTADO CLICK SOBRE UNO DE LOS NODOS: " + position);
                Intent datosIntent = new Intent(v.getContext(), Ficha.class);
                Bundle datosBundle = new Bundle();
                datosBundle.putSerializable("listado", listado);
                datosBundle.putInt("posicion", position);
                datosIntent.putExtras(datosBundle);
                v.getContext().startActivity(datosIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("SINCRONIZAR");
        menu.add("ORDENDAR POR CREACIÓN ASC");
        menu.add("ORDENDAR POR CREACIÓN DES");
        menu.add("ORDENDAR POR LÍMITE ASC");
        menu.add("ORDENDAR POR LÍMITE DES");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("RECADERO - " + getClass().getCanonicalName(), "--- * OPCION DE MENÚ SELECCIONADA: " + item.getTitle().toString());

        switch (item.getTitle().toString()){
            case "SINCRONIZAR":
                this.startActivity(new Intent(this, MainActivity.class));
                break;
            case "ORDENDAR POR CREACIÓN ASC":
                Collections.sort(listado, new Ordenar("creacionASC"));
                break;
            case "ORDENDAR POR CREACIÓN DES":
                Collections.sort(listado, new Ordenar("creacionDES"));
                break;
            case "ORDENDAR POR LÍMITE ASC":
                Collections.sort(listado, new Ordenar("limiteASC"));
                break;
            case "ORDENDAR POR LÍMITE DES":
                Collections.sort(listado, new Ordenar("limiteDES"));
                break;
        }
        ListadoAdapter adaptadorListado = new ListadoAdapter(this, listado);
        listViewListado.setAdapter(adaptadorListado);
        adaptadorListado.notifyDataSetChanged();

        return true;
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
            .setTitle("RECADERO")
            .setMessage("¿ESTÁ SEGURO QUE DESEA SALIR?")
            .setPositiveButton("SI", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    moveTaskToBack(true);
                    finish();
                }
            })
            .setNegativeButton("NO", null)
            .show();
    }
}
