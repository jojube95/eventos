package com.example.eventos.protagonista;

import java.util.Objects;

public class Protagonista {
    private String tipo;
    private String nombre;
    private String telefono;
    private String correo;

    public Protagonista() {

    }

    public Protagonista(String tipo, String nombre, String telefono, String correo) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Protagonista that = (Protagonista) o;
        return Objects.equals(tipo, that.tipo) && Objects.equals(nombre, that.nombre) && Objects.equals(telefono, that.telefono)
                && Objects.equals(correo, that.correo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipo, nombre, telefono, correo);
    }
}
