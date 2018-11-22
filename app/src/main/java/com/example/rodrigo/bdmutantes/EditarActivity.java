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

public class EditarActivity extends AppCompatActivity {
    public MutanteOperations mutanteOperations;
    ListView list;
    EditText etNome, etSkill;
    Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        Intent intent = getIntent();

        EditText nameEditText = (EditText) findViewById(R.id.etNome);
        EditText skillEditText = (EditText) findViewById((R.id.etSkill));

        String name = intent.getStringExtra("name");
        String skill = intent.getStringExtra("skill");

        nameEditText.setText(name);
        skillEditText.setText(skill);

        Button botao = (Button) findViewById(R.id.btnAtualizar);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MutanteOperations crud = new MutanteOperations(getBaseContext());
                EditText name = (EditText) findViewById(R.id.etNome);
                EditText skill = (EditText) findViewById((R.id.etSkill));
                String nameString = name.getText().toString();
                String skillString = skill.getText().toString();
                String resultado;
                resultado = crud.updateMutante(nameString, skillString);
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                Intent redirect = new Intent(EditarActivity.this, MainActivity.class);
                startActivity(redirect);
            }
        });


    }}
