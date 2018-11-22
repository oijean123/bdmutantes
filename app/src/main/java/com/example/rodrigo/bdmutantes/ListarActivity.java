package com.example.rodrigo.bdmutantes;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ListarActivity extends AppCompatActivity {
    private MutanteOperations mutanteOperations;
    ListView list;
    UserCustomAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        mutanteOperations = new MutanteOperations(this);

        List<Mutante> values = mutanteOperations.getAllMutantes();
        list = (ListView)findViewById(R.id.mutantelistView);

        userAdapter = new UserCustomAdapter(this, R.layout.row, values);
        list.setAdapter(userAdapter);

        if (values.isEmpty())
            Toast.makeText(getApplicationContext(), "Nenhum registro encontrado", Toast.LENGTH_LONG).show();
    }
};
