package com.example.rodrigo.bdmutantes;

public class Mutante {
    private String name;
    private String habilidades;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: " + name).append(System.getProperty("line.separator"));
        sb.append("Habilidades: " + habilidades);
        return sb.toString();
    }
}
