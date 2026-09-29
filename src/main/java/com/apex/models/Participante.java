package com.apex.models;

public class Participante {
    private int id_participante;
    private String nombre;
    private String correo;
    private String empresa;

    public Participante() {
    }

    public Participante(String nombre, String correo, String empresa) {
        this.nombre = nombre;
        this.correo = correo;
        this.empresa = empresa;
    }


    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public int getId_participante() {
        return id_participante;
    }

    public void setId_participante(int id_participante) {
        this.id_participante = id_participante;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public void mostrarInfo() {
        System.out.println("\n-----------------------");
        System.out.println("------PARTICIPANTE------");
        System.out.println("id: " + this.id_participante);
        System.out.println("nombre: " + this.nombre);
        System.out.println("correo: " + this.correo);
        System.out.println("empresa: " + this.empresa);
    }
}
