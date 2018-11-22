package com.example.rodrigo.bdmutantes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class CadastrarActivity extends AppCompatActivity {
    public MutanteOperations mutanteOperations;
    ListView list;
    EditText etNome, etSkill;
    Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);


        Button botao = (Button) findViewById(R.id.btnCadastrar);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MutanteOperations crud = new MutanteOperations(getBaseContext());
                EditText name = (EditText) findViewById(R.id.etNome);
                EditText skill = (EditText) findViewById((R.id.etSkill));
                String nameString = name.getText().toString();
                String skillString = skill.getText().toString();
                String resultado;

                resultado = crud.addMutante(nameString, skillString);
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                Intent redirect = new Intent(CadastrarActivity.this, MainActivity.class);
                startActivity(redirect);
            }
        });


    }}
