package com.example.eventos.eventoEmpleado;

import com.example.eventos.tipoEmpleado.TipoEmpleado;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document("eventoEmpleado")
public class EventoEmpleado {
    @Id
    private String id;
    private String eventoId;
    private String empleadoId;
    private TipoEmpleado tipo;
    private String nombre;
    private boolean fijo;
    private boolean confirmado;
    private float horasExtras;

    public EventoEmpleado(){

    }

    public EventoEmpleado(String id, String eventoId, String empleadoId, TipoEmpleado tipo, String nombre, boolean fijo, boolean confirmado,
                          float horasExtras) {
        this.id = id;
        this.eventoId = eventoId;
        this.empleadoId = empleadoId;
        this.tipo = tipo;
        this.nombre = nombre;
        this.fijo = fijo;
        this.confirmado = confirmado;
        this.horasExtras = horasExtras;
    }

    public EventoEmpleado(String eventoId, String empleadoId, TipoEmpleado tipo, String nombre, boolean fijo, boolean confirmado, float horasExtras) {
        this.eventoId = eventoId;
        this.empleadoId = empleadoId;
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

    public String getEventoId() {
        return eventoId;
    }

    public void setEventoId(String eventoId) {
        this.eventoId = eventoId;
    }

    public String getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(String empleadoId) {
        this.empleadoId = empleadoId;
    }

    public TipoEmpleado getTipo() {
        return tipo;
    }

    public void setTipo(TipoEmpleado tipo) {
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
                ", eventoId='" + eventoId + '\'' +
                ", empleadoId='" + empleadoId + '\'' +
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
        return fijo == that.fijo && confirmado == that.confirmado && Float.compare(that.horasExtras, horasExtras) == 0 && Objects.equals(id, that.id)
                && Objects.equals(eventoId, that.eventoId) && Objects.equals(empleadoId, that.empleadoId) && Objects.equals(tipo, that.tipo)
                && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventoId, empleadoId, tipo, nombre, fijo, confirmado, horasExtras);
    }
}
