package com.google.alexpfragoso.fmanagerguind;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CadastroActivity extends AppCompatActivity implements View.OnClickListener{

    //variaveis

    private EditText nomeCompletoET;
    private EditText nomeUsuarioET;
    private EditText senha1ET;
    private EditText senha2ET;

    private Button salvarBT;
    private Button cancelarBT;

    //ARQUIVO DE PREFERENCIAS
    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME ="CadastroActivityPreferences";
    private final String LISTA_DE_CADASTRO = "lista de cadastro";
    //metodos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //trabahando com o arquivo sharedPreferences

        sharedPreferences = getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        //prefsEditor.clear();
        prefsEditor.commit();

        //pegando referencias para as views

        nomeCompletoET =(EditText) findViewById(R.id.et_nome_completo);
        nomeUsuarioET = (EditText) findViewById(R.id.et_nome_usuario);
        senha1ET =(EditText) findViewById(R.id.et_senha1);
        senha2ET =(EditText) findViewById(R.id.et_senha2);

        salvarBT = (Button) findViewById(R.id.bt_salvar);
        salvarBT.setOnClickListener(this);
        cancelarBT = (Button) findViewById(R.id.bt_cancelar);
        cancelarBT.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        if(view == salvarBT){

            //Existe uma lista de cadastro?
            if(sharedPreferences.contains(LISTA_DE_CADASTRO)){
                //Se existe, cadastre nela.

                //criando nova pessoa e armazenando-a em um objeto JSON



                try {

                    //recuperando o array de objetos JSON no sharedPreferences e guardando o novo objeto nele
                    JSONArray arrayJSON = new JSONArray(sharedPreferences.getString(LISTA_DE_CADASTRO,null));
                    Pessoa novaPessoa = new Pessoa(nomeCompletoET.getText().toString(),nomeUsuarioET.getText().toString(),senha1ET.getText().toString());
                    JSONObject objJSON = new JSONObject();

                    objJSON.put("nome_completo",novaPessoa.getNome_completo());
                    objJSON.put("nome_usuario",novaPessoa.getNome_usuario());
                    objJSON.put("senha",novaPessoa.getSenha());


                    arrayJSON.put(objJSON);
                    //convertendo o array JSON em uma String para usá-la no sharedPreferences
                    String stringArrayJSON = arrayJSON.toString();
                    //usando o editor para salvar a string no shared preferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(LISTA_DE_CADASTRO,stringArrayJSON);
                    editor.commit();
                    Toast.makeText(this, "Salvando novo usuário!", Toast.LENGTH_SHORT).show();


                }catch (JSONException e){
                    e.printStackTrace();
                }



            }else{

                //Senão existe, crie-a primeiro e depois cadastre.
                //Os arquivos serão persistidos em uma lista armazenada no formato JSON.
                //o arquivo JSON será armazenado no arquivo sharedPreferences junto da TAG LISTA_DE_CADASTRO.

                Pessoa novaPessoa = new Pessoa(nomeCompletoET.getText().toString(),nomeUsuarioET.getText().toString(),senha1ET.getText().toString());
                JSONObject objJSON = new JSONObject();
                JSONArray arrayJSON = new JSONArray();

                //preenchendo o objeto JSON com a nova pessoa
                try{
                    objJSON.put("nome_usuario",novaPessoa.getNome_usuario());
                    objJSON.put("nome_completo",novaPessoa.getNome_completo());
                    objJSON.put("senha",novaPessoa.getSenha());

                    //guardando o objeto JSON em um array de objetos JSON
                    arrayJSON.put(objJSON);

                }catch(JSONException e){
                    e.printStackTrace();
                }

                //convertendo o array JSON em uma String para usá-la no sharedPreferences
                String stringArrayJSON = arrayJSON.toString();

                //usando o editor para salvar a string no shared preferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(LISTA_DE_CADASTRO,stringArrayJSON);
                editor.commit();
                Toast.makeText(this, "Salvando novo usuário pela primeira vez!", Toast.LENGTH_SHORT).show();


            }
            finish();



        }
        else{
            //se clicou em cancelar apenas fecha-se a tela
            finish();

        }

    }
}
