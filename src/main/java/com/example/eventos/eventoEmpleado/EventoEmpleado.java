package com.example.eventos.eventoEmpleado;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document("eventoEmpleado")
public class EventoEmpleado {
    @Id
    private String id;
    private String idEvento;
    private String idEmpleado;
    private String tipo;
    private String nombre;
    private boolean fijo;
    private boolean confirmado;
    private float horasExtras;

    public EventoEmpleado(String id, String idEvento, String idEmpleado, String tipo, String nombre, boolean fijo, boolean confirmado, float horasExtras) {
        this.id = id;
        this.idEvento = idEvento;
        this.idEmpleado = idEmpleado;
        this.tipo = tipo;
        this.nombre = nombre;
        this.fijo = fijo;
        this.confirmado = confirmado;
        this.horasExtras = horasExtras;
    }

    public EventoEmpleado(String idEvento, String idEmpleado, String tipo, String nombre, boolean fijo, boolean confirmado, float horasExtras) {
        this.idEvento = idEvento;
        this.idEmpleado = idEmpleado;
        this.tipo = tipo;
        this.nombre = nombre;
        this.fijo = fijo;
        this.confirmado = confirmado;
        this.horasExtras = horasExtras;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
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

    public boolean isFijo() {
        return fijo;
    }

    public void setFijo(boolean fijo) {
        this.fijo = fijo;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }

    public float getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(float horasExtras) {
        this.horasExtras = horasExtras;
    }

    @Override
    public String toString() {
        return "EventoEmpleado{" +
                "id='" + id + '\'' +
                ", idEvento='" + idEvento + '\'' +
                ", idEmpleado='" + idEmpleado + '\'' +
                ", tipo='" + tipo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fijo=" + fijo +
                ", confirmado=" + confirmado +
                ", horasExtras=" + horasExtras +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventoEmpleado that = (EventoEmpleado) o;
        return fijo == that.fijo && confirmado == that.confirmado && Float.compare(that.horasExtras, horasExtras) == 0 && Objects.equals(id, that.id) && Objects.equals(idEvento, that.idEvento) && Objects.equals(idEmpleado, that.idEmpleado) && Objects.equals(tipo, that.tipo) && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idEvento, idEmpleado, tipo, nombre, fijo, confirmado, horasExtras);
    }
}
