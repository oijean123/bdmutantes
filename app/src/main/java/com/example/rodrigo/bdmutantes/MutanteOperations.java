package com.example.rodrigo.bdmutantes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MutanteOperations {
    private SimpleBDWrapper dbHelper;
    private String[] MUTANTE_TABLE_COLUMNS = { SimpleBDWrapper.MUTANTE_NAME, SimpleBDWrapper.MUTANTE_SKILL};
    private SQLiteDatabase database;

    public MutanteOperations(Context context){
        dbHelper = new SimpleBDWrapper(context);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void resetTables() throws SQLException {
        database.execSQL("delete from "+ SimpleBDWrapper.MUTANTES);
        database.execSQL("delete from "+ SimpleBDWrapper.HABILIDADES);
    }

    public void firstTime()throws SQLException {
        open();
        dbHelper.onCreate(database);
        resetTables();
        populateTables();
    }

    public void populateTables()
    {
        addMutante("Jean Grey", "Telepatia,Telecinese,Campos de Força");
        addMutante("Deadpool", "Regeneração,Habilidades físicas");
        addMutante("Magneto", "Magnetismo,Campos Eletromagnéticos");
        addMutante("Tempestade", "Hidrocenose,Aerocinese,Criocinese,Eletrocinese,Atmocinese,Voo");
        addMutante("Mística", "Metamórfa,Regeneração");
        addMutante("Noturno", "teletransporte,super agilidade,visão noturna");
        addMutante("Ciclope", "Raio-laser");
        addMutante("Lince Negra", "Intangibilidade,Durabilidade Orgânica");
        addMutante("Professor Charles Xavier", "Telepatia,Ilusão Telepática,Intelecto Genial");
        addMutante("Juggernaut", "Durabilidade,Campos de Força");
        addMutante("Wolverine", "Poderes de cura e defensiva,Durabilidade");
    }

    public void close(){
        dbHelper.close();
    }

    public String addMutante(String name, String skill){
        open();
        ContentValues values = new ContentValues();

        if(name.isEmpty() || skill.isEmpty()){
            return "Erro ao inserir registro: Preencher todos os valores";
        }

        List<String> skillList = Arrays.asList(skill.split(","));

        values.put(SimpleBDWrapper.MUTANTE_NAME, name);
        values.put(SimpleBDWrapper.MUTANTE_SKILL, skill);

        long resultado = database.insert(SimpleBDWrapper.MUTANTES, null, values);
        if (resultado != -1)
        {
        for(String skillItem : skillList) {
            ContentValues valuesHabilidades = new ContentValues();
            valuesHabilidades.put(SimpleBDWrapper.MUTANTE_NAME, name);
            valuesHabilidades.put(SimpleBDWrapper.MUTANTE_SKILL, skillItem);
            database.insert(SimpleBDWrapper.HABILIDADES, null, valuesHabilidades);
        }
        }

        database.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }

    private Mutante parseMutante(Cursor cursor)
    {
        Mutante mutante = new Mutante();
        mutante.setName((cursor.getString(0)));
        mutante.setHabilidades(cursor.getString(1));
        return mutante;
    }

    public List<Mutante> getAllMutantes()
    {
        open();
        List mutantes = new ArrayList();
//        String rawQuery = "SELECT m._name, h._skill FROM Mutantes m Join Habilidades h ON m._name = h._name";
//        Cursor cursor = database.rawQuery(rawQuery, null);

      Cursor cursor = database.query(SimpleBDWrapper.MUTANTES, MUTANTE_TABLE_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            Mutante mutante = parseMutante(cursor);
            mutantes.add(mutante);
            cursor.moveToNext();
        }
        cursor.close();
        return mutantes;
    }

    public List<Mutante> getMutantesByFilter(String nameFilter, String skillFilter)
    {
        open();
        List mutantes = new ArrayList();
        String where = "";
        String[] whereArgs = new String[]{};

        if (!nameFilter.equals("")  && skillFilter.equals("")) {
            where = "WHERE h._name LIKE ?";
            whereArgs = new String[] {
                    '%' +nameFilter+ '%'
            };
        }
        else if (nameFilter.equals("") && !skillFilter.equals("")) {
            where = "WHERE h._skill LIKE ?";
            whereArgs = new String[] {
                    '%' +skillFilter+ '%'
            };
        }
        else
            {
            where = "WHERE h._name LIKE ? and h._skill LIKE ?";
        whereArgs = new String[] {
                '%' +nameFilter+ '%', '%' +skillFilter+ '%'
        };
        }
//        Cursor cursor = database.query(SimpleBDWrapper.MUTANTES, MUTANTE_TABLE_COLUMNS, where, whereArgs, null, null, null);
       String rawQuery = "SELECT m._name, h._skill FROM Mutantes m Join Habilidades h ON m._name = h._name " + where;
       Cursor cursor = database.rawQuery(rawQuery, whereArgs);

        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            Mutante mutante = parseMutante(cursor);
            mutantes.add(mutante);
            cursor.moveToNext();
        }
        cursor.close();
        return mutantes;
    }

    public String deleteMutante(String name){
        open();
        String where = "_name=?";
        String[] whereArgs = new String[]{ name };

        long resultado = database.delete(SimpleBDWrapper.MUTANTES, where, whereArgs);
        database.delete(SimpleBDWrapper.HABILIDADES, where, whereArgs);
        database.close();

        if (resultado ==-1)
            return "Erro ao deletar registro";
        else
            return "Registro deletado com sucesso";
    }

    public String updateMutante(String nameFilter, String skillFilter){
        open();

        ContentValues cv = new ContentValues();
        cv.put("_name", nameFilter);
        cv.put("_skill", skillFilter);

        String whereClause = "_name=?";
        String[] whereArgs = new String[]{ nameFilter};

        long resultado = database.update(SimpleBDWrapper.MUTANTES, cv, whereClause, whereArgs);

        if (resultado != -1)
        {
            database.delete(SimpleBDWrapper.HABILIDADES, whereClause, whereArgs);

            List<String> skillList = Arrays.asList(skillFilter.split(","));
            for(String skillItem : skillList) {
                ContentValues valuesHabilidades = new ContentValues();
                valuesHabilidades.put(SimpleBDWrapper.MUTANTE_NAME, nameFilter);
                valuesHabilidades.put(SimpleBDWrapper.MUTANTE_SKILL, skillItem);
                database.insert(SimpleBDWrapper.HABILIDADES, null, valuesHabilidades);
            }
        }
        database.close();

        if (resultado ==-1)
            return "Erro ao atualizar registro";
        else
            return "Registro atualizado com sucesso";
    }
}
