package com.example.eventos.plato;

import com.example.eventos.tipoPlato.TipoPlato;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document("plato")
@TypeAlias("Plato")
public class Plato {
    @Id
    protected String id;

    protected TipoPlato tipo;

    protected String nombre;
    protected float precio;
    protected boolean individual;
    protected int racionesPersona;

    public Plato() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TipoPlato getTipo() {
        return tipo;
    }

    public void setTipo(TipoPlato tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public boolean isIndividual() {
        return individual;
    }

    public void setIndividual(boolean individual) {
        this.individual = individual;
    }

    public int getRacionesPersona() {
        return racionesPersona;
    }

    public void setRacionesPersona(int racionesPersona) {
        this.racionesPersona = racionesPersona;
    }

    @Override
    public String toString() {
        return "Plato{" +
                "id='" + id + '\'' +
                ", tipo=" + tipo +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", individual=" + individual +
                ", racionesPersona=" + racionesPersona +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plato plato = (Plato) o;
        return Float.compare(precio, plato.precio) == 0 && individual == plato.individual && racionesPersona == plato.racionesPersona && Objects.equals(id, plato.id) && Objects.equals(tipo, plato.tipo) && Objects.equals(nombre, plato.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, nombre, precio, individual, racionesPersona);
    }
}