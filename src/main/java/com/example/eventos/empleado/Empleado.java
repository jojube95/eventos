package com.example.eventos.empleado;

import com.example.eventos.tipoEmpleado.TipoEmpleado;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Objects;

@Document("empleado")
@TypeAlias("Empleado")
public class Empleado {
    @Id
    private String id;

    private TipoEmpleado tipo;
    private String nombre;
    private String telefono;
    private boolean fijo;

    public Empleado() {

    }

    public Empleado(String id, TipoEmpleado tipo, String nombre, String telefono, boolean fijo) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fijo = fijo;
    }

    public Empleado(TipoEmpleado tipo, String nombre, String telefono, boolean fijo) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fijo = fijo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isFijo() {
        return fijo;
    }

    public void setFijo(boolean fijo) {
        this.fijo = fijo;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id='" + id + '\'' +
                ", tipo='" + tipo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fijo=" + fijo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return fijo == empleado.fijo && Objects.equals(id, empleado.id) && Objects.equals(tipo, empleado.tipo)
                && Objects.equals(nombre, empleado.nombre) && Objects.equals(telefono, empleado.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, nombre, telefono, fijo);
    }
}
