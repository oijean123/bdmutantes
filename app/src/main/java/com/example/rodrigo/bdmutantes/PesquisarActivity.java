package com.example.rodrigo.bdmutantes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PesquisarActivity extends AppCompatActivity {
    private MutanteOperations mutanteOperations;
    ListView list;
    UserCustomAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);
        Button botao = (Button) findViewById(R.id.btnPesquisar);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Mutante> resultado = new ArrayList<Mutante>();
                MutanteOperations crud = new MutanteOperations(getBaseContext());
                EditText name = (EditText) findViewById(R.id.etNome);
                EditText skill = (EditText) findViewById((R.id.etSkill));
                String nameString = name.getText().toString();
                String skillString = skill.getText().toString();

                resultado = crud.getMutantesByFilter(nameString, skillString);
                Listar(resultado);
                if (resultado.isEmpty())
                    Toast.makeText(getApplicationContext(), "Nenhum registro encontrado", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void Listar(List<Mutante> resultado)
    {
        list = (ListView)findViewById(R.id.mutantelistView);

        ArrayAdapter<Mutante> adapter = new ArrayAdapter<Mutante>(this,
                android.R.layout.simple_list_item_1, resultado);

//        userAdapter = new UserCustomAdapter(this, R.layout.row, resultado);

        list.setAdapter(adapter);

        }
}
