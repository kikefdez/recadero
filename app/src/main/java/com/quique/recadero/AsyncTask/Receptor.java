package com.quique.recadero.AsyncTask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quique.recadero.Actividades.Listado;
import com.quique.recadero.Objetos.Recado;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by FernandEn on 03/04/2017.
 */

public class Receptor extends AsyncTask<String, Void, ArrayList<Recado>> {
    private Activity actividad;
    private ProgressDialog progDailog;

    public Receptor (Activity valorActividad){
        this.actividad = valorActividad;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progDailog = new ProgressDialog(actividad);
        progDailog.setMessage("Recibiendo datos del servidor...");
        progDailog.setIndeterminate(false);
        progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDailog.setCancelable(true);
        progDailog.show();
    }

    @Override
    protected ArrayList<Recado> doInBackground(String... parametros) {
        ArrayList<Recado> listado = new ArrayList<>();

        HttpURLConnection http = null;

        try{
            Log.d("RECADERO - " + getClass().getCanonicalName(), "--- * ESTABLECEMOS LA CONEXIÓN CON EL SERVIDOR");
            URL datosUrl = new URL(parametros[0].toString());
            http = (HttpURLConnection)datosUrl.openConnection();
            Log.d("RECADERO - " + getClass().getCanonicalName(), "--- * LA DIRECCIÓN DE CONSULTA ES: " + parametros[0].toString());

            if(http.getResponseCode() == HttpURLConnection.HTTP_OK){
                Log.d("RECADERO - " + getClass().getCanonicalName(), "--- * CONTACTO ESTABLECIDO CON EL SERVIDOR");

                InputStreamReader isr = new InputStreamReader(http.getInputStream());
                BufferedReader br = new BufferedReader(isr);
                String respuesta = br.readLine();

                Log.d("RECADERO - " + getClass().getCanonicalName(), "--- * RECIBIDA INFORMACIÓN DEL SERVIDOR");
                Type tipoListado = new TypeToken<ArrayList<Recado>>() {}.getType();
                listado = new Gson().fromJson(respuesta.toString(), tipoListado);
                Log.d("RECADERO - " + getClass().getCanonicalName(), "--- * INFORMACIÓN TRANSFORMADA EN ARRAY");

            } else {
                Log.d("RECADERO - " + getClass().getCanonicalName(), "--- * NO HA SIDO POSIBLE CONTACTAR CON EL SERVIDOR: " + http.getResponseCode());
            }

        } catch (Throwable t) {
            Log.e("RECADERO - " + getClass().getCanonicalName(), "--- * ERROR AL RECIBIR LA INFORMACIÓN DEL SERVIDOR: " + t.getMessage());
        } finally {
            http.disconnect();
        }

        return listado;
    }

    @Override
    protected void onPostExecute(ArrayList<Recado> recados) {
        Log.d("RECADERO - " + getClass().getCanonicalName(), "--- * FINALIZADO EL PROCESO DE DESCARGA");
        progDailog.dismiss();
        Log.d("RECADERO - " + getClass().getCanonicalName(), "--- * LISTADO DE RECADOS: " + recados.size());
        if(recados.size() > 0){
            Intent datosIntent = new Intent(actividad, Listado.class);
            Bundle datosBundle = new Bundle();
            datosBundle.putSerializable("listado", recados);
            datosIntent.putExtras(datosBundle);
            actividad.startActivity(datosIntent);
        } else {
            Toast.makeText(actividad, "NO HAY RECADOS DISPONIBLES PARA REALIZAR", Toast.LENGTH_LONG).show();
        }
    }
}

