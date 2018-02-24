package com.google.alexpfragoso.fmanagerguind;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PrincipalActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    //variáveis

    private ListView opcoesMenuLV;
    private DrawerLayout gavetaNavegacaoDL;
    private ActionBarDrawerToggle botaoABDT;
    private String[] lista = {"Home","Cadastro","Alteração","Sair"};

    //métodos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        opcoesMenuLV = (ListView) findViewById(R.id.lv_opcoes_menu);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,lista);
        opcoesMenuLV.setAdapter(arrayAdapter);
        opcoesMenuLV.setOnItemClickListener(this);

        gavetaNavegacaoDL = (DrawerLayout) findViewById(R.id.dl_gaveta_navegacao);

        botaoABDT = new ActionBarDrawerToggle(this,gavetaNavegacaoDL,R.string.menu_aberto,R.string.menu_fechado){

            public void onDrawerOpened(View drawerView){
                Toast.makeText(getApplicationContext(), R.string.abriu, Toast.LENGTH_SHORT).show();

            }

            public void onDrawerClosed(View drawerView){
                Toast.makeText(getApplicationContext(),"fechou", Toast.LENGTH_SHORT).show();
            }

        };

        gavetaNavegacaoDL.addDrawerListener(botaoABDT);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        String textoOpcao = ((TextView) view).getText().toString();
        getSupportActionBar().setSubtitle(textoOpcao);

        switch (position){

            case 0:{
                Intent homeIntent = new Intent(this,HomeActivity.class);
                startActivity(homeIntent);
                break;
            }
            case 1:{
                Intent cadastroIntent = new Intent(this,CadastroActivity.class);
                startActivity(cadastroIntent);
                break;
            }
            case 2:{
                Intent alteracaoIntent = new Intent(this,AlteracaoActivity.class);
                startActivity(alteracaoIntent);
                break;
            }
        }

    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        botaoABDT.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        botaoABDT.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(botaoABDT.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
