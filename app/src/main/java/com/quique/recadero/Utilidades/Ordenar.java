package com.quique.recadero.Utilidades;

import android.util.Log;

import com.quique.recadero.Objetos.Recado;

import java.util.Comparator;

/**
 * Created by FernandEn on 04/04/2017.
 */

public class Ordenar implements Comparator<Recado> {
    private String parametro;

    public Ordenar(String valorParametro){
        this.parametro = valorParametro;
    }

    @Override
    public int compare(Recado nodo1, Recado nodo2) {
        int respuesta = 0;

        switch(parametro){
            case "creacionASC":
                respuesta = nodo1.getFecha_hora().compareTo(nodo2.getFecha_hora());
                break;
            case "creacionDES":
                respuesta = nodo2.getFecha_hora().compareTo(nodo1.getFecha_hora());
                break;
            case "limiteASC":
                respuesta = nodo1.getFecha_hora_maxima().compareTo(nodo2.getFecha_hora_maxima());
                break;
            case "limiteDES":
                respuesta = nodo2.getFecha_hora_maxima().compareTo(nodo1.getFecha_hora_maxima());
                break;
        }
        return respuesta;
    }
}
