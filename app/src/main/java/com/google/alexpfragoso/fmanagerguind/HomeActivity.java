package com.google.alexpfragoso.fmanagerguind;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    //variaveis

    private android.support.v4.app.FragmentManager fragmentManager;

    private Fragmento1Fragment fragmento1Fragment;
    private Fragmento2Fragment fragmento2Fragment;

    private final String TAG_1 = "FRAGMENTO_1";
    private final String TAG_2 = "FRAGMENTO_2";

    private Button mudaFragmentoBT;

    //metodos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fragmento1Fragment = new Fragmento1Fragment();
        fragmento2Fragment = new Fragmento2Fragment();

        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.main_fl,fragmento1Fragment,TAG_1).commit();

        mudaFragmentoBT = findViewById(R.id.muda_fragmento_bt);
        mudaFragmentoBT.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        if(view == mudaFragmentoBT){

            Fragment fragment = fragmentManager.findFragmentByTag(TAG_1);
            if(fragment == null){
                fragmentManager.beginTransaction().replace(R.id.main_fl,fragmento1Fragment,TAG_1).commit();

            }
            else{
                fragmentManager.beginTransaction().replace(R.id.main_fl,fragmento2Fragment,TAG_2).commit();
            }
        }
    }

}
