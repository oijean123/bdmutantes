package com.example.rodrigo.bdmutantes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MutanteOperations mutanteOperations;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void cadastrarMutante(View view){
        Intent it = new Intent(this, CadastrarActivity.class);
        startActivity(it);
    }

    public void listarMutante(View view){
        Intent it = new Intent(this, ListarActivity.class);
        startActivity(it);
    }

    public void pesquisarMutante(View view){
        Intent it = new Intent(this, PesquisarActivity.class);
        startActivity(it);
    }

    public void fecharApp(View view){
        System.exit(0);
    }
}
