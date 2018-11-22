package com.example.rodrigo.bdmutantes;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserCustomAdapter extends ArrayAdapter<Mutante> {
    Context context;
    int layoutResourceId;
    List<Mutante> data = new ArrayList<Mutante>();
    private MutanteOperations mutanteOperations;


    public UserCustomAdapter(Context context, int layoutResourceId,
                             List<Mutante> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        UserHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new UserHolder();
            holder.textName = (TextView) row.findViewById(R.id.textView1);
            holder.textSkills= (TextView) row.findViewById(R.id.textView2);
            holder.btnEdit = (Button) row.findViewById(R.id.button1);
            holder.btnDelete = (Button) row.findViewById(R.id.button2);
            row.setTag(holder);
        } else {
            holder = (UserHolder) row.getTag();
        }
        final Mutante mutante = data.get(position);
        holder.textName.setText("Nome: " + mutante.getName());
        holder.textSkills.setText("Habilidades: " + mutante.getHabilidades());

        holder.btnEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent redirect = new Intent(context, EditarActivity.class);
                redirect.putExtra("name", mutante.getName());
                redirect.putExtra("skill", mutante.getHabilidades());
                context.startActivity(redirect);

                Log.i("Edit Button Clicked", "**********");
                Toast.makeText(context, "Edit button Clicked",
                        Toast.LENGTH_LONG).show();
            }
        });

        holder.btnDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mutante.getName();
                mutanteOperations = new MutanteOperations(context);
                String msg = mutanteOperations.deleteMutante(name);
                Log.i("Delete Button Clicked", "**********");
                Toast.makeText(context, msg,
                        Toast.LENGTH_LONG).show();
                Intent redirect = new Intent(context,ListarActivity.class);
                context.startActivity(redirect);
            }
        });
        return row;

    }

    static class UserHolder {
        TextView textName;
        TextView textSkills;
        Button btnEdit;
        Button btnDelete;
    }
}