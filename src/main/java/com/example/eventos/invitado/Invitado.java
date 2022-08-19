package com.example.eventos.invitado;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document("invitado")
public class Invitado {
    @Id
    private String id;

    private String idEvento;
    private String idMesa;

    private String nombre;
    private String descripcion;

    public Invitado(){

    }

    public Invitado(String id, String idEvento, String idMesa, String nombre, String descripcion) {
        this.id = id;
        this.idEvento = idEvento;
        this.idMesa = idMesa;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Invitado(String idEvento, String idMesa, String nombre, String descripcion) {
        this.idEvento = idEvento;
        this.idMesa = idMesa;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(String idMesa) {
        this.idMesa = idMesa;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    @Override
    public String toString() {
        return "Invitado{" +
                "idEvento='" + idEvento + '\'' +
                ", idMesa='" + idMesa + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invitado invitado = (Invitado) o;
        return Objects.equals(id, invitado.id) && Objects.equals(idEvento, invitado.idEvento) && Objects.equals(idMesa, invitado.idMesa) && Objects.equals(nombre, invitado.nombre) && Objects.equals(descripcion, invitado.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idEvento, idMesa, nombre, descripcion);
    }
}
