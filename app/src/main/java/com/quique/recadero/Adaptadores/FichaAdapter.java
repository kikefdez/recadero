package com.quique.recadero.Adaptadores;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.quique.recadero.Objetos.FichaFragment;
import com.quique.recadero.Objetos.Recado;

import java.util.ArrayList;

/**
 * Created by FernandEn on 04/04/2017.
 */

public class FichaAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Recado> listado;

    public FichaAdapter (FragmentManager fm, ArrayList<Recado> valorListado){
        super(fm);
        this.listado = valorListado;
        Log.d("RECADERO - " + getClass().getCanonicalName(), "--- * ACCEDEMOS AL CONSTRUCTOR DE FichaAdapter");
    }

    @Override
    public int getCount() { return listado.size(); }

    @Override
    public Fragment getItem(int position) {
        Log.d("RECADERO - " + getClass().getCanonicalName(), "--- * RECUPERAMOS EL ELEMENTO CON POSICION: " + position);

        /*
        FichaFragment fichaFragment = new FichaFragment();
        Bundle datosBundle = new Bundle();
        datosBundle.putSerializable("listado", listado);
        datosBundle.putInt("posicion", position);
        fichaFragment.setArguments(datosBundle);
        return fichaFragment;
        */

        return new FichaFragment(listado, position);
    }
}
