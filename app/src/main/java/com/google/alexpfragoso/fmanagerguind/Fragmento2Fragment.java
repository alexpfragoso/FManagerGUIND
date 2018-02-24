package com.google.alexpfragoso.fmanagerguind;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Alex Fragoso on 23/02/2018.
 */

public class Fragmento2Fragment extends android.support.v4.app.Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_layout,null);
        TextView textoTV = view.findViewById(R.id.tv_texto);
        textoTV.setText(R.string.mensagem_fragmento2);
        return view;
    }
}
