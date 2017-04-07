package com.quique.recadero.Objetos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quique.recadero.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by FernandEn on 04/04/2017.
 */

public class FichaFragment extends Fragment {
    private ArrayList<Recado> listado;
    private int posicion;

    //public FichaFragment() { }
    public FichaFragment(ArrayList<Recado> valorListado, int valorPosicion){
        this.listado = valorListado;
        this.posicion = valorPosicion;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("RECADERO - " + getClass().getCanonicalName(), "--- * INTENTAMOS INFLAR LA FICHA EN EL VIEWPAGER");
        View nodo_ficha = inflater.inflate(R.layout.nodo_ficha, container, false);

        Recado datosRecado = listado.get(posicion);
        ((TextView) nodo_ficha.findViewById(R.id.cajaNFCreacion)).setText(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(datosRecado.getFecha_hora()));
        ((TextView) nodo_ficha.findViewById(R.id.cajaNFCliente)).setText(datosRecado.getNombre_cliente());
        ((TextView) nodo_ficha.findViewById(R.id.cajaNFTelefono)).setText(datosRecado.getTelefono());
        ((TextView) nodo_ficha.findViewById(R.id.cajaNFRecogida)).setText(datosRecado.getDireccion_recogida());
        ((TextView) nodo_ficha.findViewById(R.id.cajaNFEntrega)).setText(datosRecado.getDireccion_entrega());
        ((TextView) nodo_ficha.findViewById(R.id.cajaNFLimite)).setText(new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(datosRecado.getFecha_hora_maxima()));

        long diferencia = datosRecado.getFecha_hora_maxima().getTime() - datosRecado.getFecha_hora().getTime();
        long segundos = 1000;
        long minutos = segundos * 60;
        long horas = minutos * 60;

        long margenHoras = diferencia / horas;
        diferencia = diferencia % horas;
        long margenMinutos = diferencia / minutos;
        diferencia = diferencia % minutos;
        long margenSegundos = diferencia / segundos;

        ((TextView) nodo_ficha.findViewById(R.id.cajaNFRestante)).setText(margenHoras + ":" + margenMinutos + ":" + margenSegundos);
        ((TextView) nodo_ficha.findViewById(R.id.cajaNFDescripcion)).setText(datosRecado.getDescripcion());

        return nodo_ficha;
    }
}
