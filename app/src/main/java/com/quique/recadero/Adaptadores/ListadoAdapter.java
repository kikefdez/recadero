package com.quique.recadero.Adaptadores;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.quique.recadero.Objetos.Recado;
import com.quique.recadero.R;

import java.util.ArrayList;

/**
 * Created by FernandEn on 04/04/2017.
 */

public class ListadoAdapter extends BaseAdapter {
    private Activity actividad;
    private ArrayList<Recado> listado;

    public ListadoAdapter(Activity valorActividad, ArrayList<Recado> valorListado){
        this.actividad = valorActividad;
        this.listado = valorListado;
    }

    @Override
    public int getCount() { return listado.size(); }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vistaRespuesta;

        if(convertView == null){
            LayoutInflater layoutInflater = actividad.getLayoutInflater();
            vistaRespuesta = layoutInflater.inflate(R.layout.nodo_listado, parent, false);
        } else {
            vistaRespuesta = convertView;
        }

        Recado datosRecado = listado.get(position);
        ((TextView)vistaRespuesta.findViewById(R.id.cajaCliente)).setText(datosRecado.getNombre_cliente());
        ((TextView)vistaRespuesta.findViewById(R.id.cajaTelefono)).setText(datosRecado.getTelefono());
        ((TextView)vistaRespuesta.findViewById(R.id.cajaRecogida)).setText(datosRecado.getDireccion_recogida());
        ((TextView)vistaRespuesta.findViewById(R.id.cajaEntrega)).setText(datosRecado.getDireccion_entrega());

        return vistaRespuesta;
    }
}
