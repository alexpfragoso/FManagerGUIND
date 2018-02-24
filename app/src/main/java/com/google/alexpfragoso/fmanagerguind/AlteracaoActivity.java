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

import java.util.ArrayList;
import java.util.List;

public class AlteracaoActivity extends AppCompatActivity implements View.OnClickListener{

    //variaveis


    private int posicaoNaLista=-1;
    List<Pessoa> lista_de_cadastro = new ArrayList<Pessoa>();

    private EditText nomeCompletoET;
    private EditText nomeUsuarioET;
    private EditText senha1ET;
    private EditText senha2ET;

    private Button alterarBT;
    private Button cancelarBT;
    private Button buscarBT;

    //ARQUIVO DE PREFERENCIAS
    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME ="CadastroActivityPreferences";
    private final String LISTA_DE_CADASTRO = "lista de cadastro";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alteracao);


        //pegando referencias para as views

        nomeCompletoET =(EditText) findViewById(R.id.et_nome_completo_alter);
        nomeUsuarioET = (EditText) findViewById(R.id.et_nome_usuario_alter);
        senha1ET =(EditText) findViewById(R.id.et_senha1_alter);

        alterarBT = (Button) findViewById(R.id.bt_alterar);
        alterarBT.setOnClickListener(this);
        cancelarBT = (Button) findViewById(R.id.bt_cancelar_alteracao);
        cancelarBT.setOnClickListener(this);
        buscarBT = (Button) findViewById(R.id.bt_buscar);
        buscarBT.setOnClickListener(this);

        //trabahando com o arquivo sharedPreferences

        sharedPreferences = getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
       //prefsEditor.clear();
        //prefsEditor.commit();
    }

    @Override
    public void onClick(View view) {

        if(view == buscarBT){

            //buscando no array List obtido do ArrayJSON o usuario desejado

            nomeCompletoET.setText("");
            senha1ET.setText("");



            //se existe uma lista de cadastro, procura-se nela
            if(sharedPreferences.contains(LISTA_DE_CADASTRO)){

                try{

                    JSONArray arrayJSON = new JSONArray(sharedPreferences.getString(LISTA_DE_CADASTRO,null));
                    Pessoa pessoa;
                    JSONObject objJSON;

                    //preenchendo a lista de cadastro com os dados do arquivo JSON
                    for(int i=0; i<arrayJSON.length();i++){

                        objJSON = arrayJSON.getJSONObject(i);
                        pessoa = new Pessoa(objJSON.getString("nome_completo"),objJSON.getString("nome_usuario"),objJSON.getString("senha"));

                        lista_de_cadastro.add(pessoa);

                    }

                }catch(JSONException e){
                    e.printStackTrace();
                }

                //procurando na lista de cadastro o usuario desejado

                for(int i=0; i<lista_de_cadastro.size();i++){

                    String nome_usuario = lista_de_cadastro.get(i).getNome_usuario();


                    //se existe um usuario com o nome procurado, exibe-se
                    if(nomeUsuarioET.getText().toString().compareTo(nome_usuario)==0){

                        nomeCompletoET.setText(lista_de_cadastro.get(i).getNome_completo());
                        senha1ET.setText(lista_de_cadastro.get(i).getSenha());
                        posicaoNaLista = i;
                    }
                    //se não existe, comunica-se a não existencia
                    else{
                        if(i == lista_de_cadastro.size()-1){
                            Toast.makeText(this, "Usuário não encontrado!", Toast.LENGTH_SHORT).show();
                        }


                    }

                }

            }
            else{
                //se não existe lista de cadastro, apenas informa que não existe
                Toast.makeText(this, "Não existe nenhum usuário cadastrado ainda!", Toast.LENGTH_SHORT).show();
            }

        }
        else{
            if(view == cancelarBT){
                finish();
            }
            else{
                //se clicaram no botao alterar...realiza-se alteração dos dados
                if(view == alterarBT){

                    if(posicaoNaLista == -1){
                        Toast.makeText(this, "Busque por um usuário existente!", Toast.LENGTH_SHORT).show();
                    }else{

                        lista_de_cadastro.get(posicaoNaLista).setNome_completo(nomeCompletoET.getText().toString());
                        lista_de_cadastro.get(posicaoNaLista).setSenha(senha1ET.getText().toString());

                        JSONArray arrayJSON = new JSONArray();
                        for(int i=0;i<lista_de_cadastro.size();i++){

                            Pessoa pessoa = lista_de_cadastro.get(i);
                            JSONObject objJSON = new JSONObject();

                            //preenchendo o objeto JSON com a nova pessoa
                            try{
                                objJSON.put("nome_usuario",pessoa.getNome_usuario());
                                objJSON.put("nome_completo",pessoa.getNome_completo());
                                objJSON.put("senha",pessoa.getSenha());

                                //guardando o objeto JSON em um array de objetos JSON
                                arrayJSON.put(objJSON);

                            }catch(JSONException e){
                                e.printStackTrace();
                            }
                        }

                        //convertendo o array JSON em uma String para usá-la no sharedPreferences
                        String stringArrayJSON = arrayJSON.toString();

                        //usando o editor para salvar a string no shared preferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(LISTA_DE_CADASTRO,stringArrayJSON);
                        editor.commit();
                        Toast.makeText(this, "Alterando usuário!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        }

    }
}
