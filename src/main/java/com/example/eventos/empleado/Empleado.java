package com.example.eventos.empleado;

import com.example.eventos.persona.Persona;
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
    private Persona persona;
    private boolean fijo;

    public Empleado() {

    }

    public Empleado(String id, TipoEmpleado tipo, Persona persona, boolean fijo) {
        this.id = id;
        this.tipo = tipo;
        this.persona = persona;
        this.fijo = fijo;
    }

    public Empleado(TipoEmpleado tipo, Persona persona, boolean fijo) {
        this.tipo = tipo;
        this.persona = persona;
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

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
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
                ", tipo=" + tipo +
                ", persona=" + persona +
                ", fijo=" + fijo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return fijo == empleado.fijo && Objects.equals(id, empleado.id) && Objects.equals(tipo, empleado.tipo)
                && Objects.equals(persona, empleado.persona);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, persona, fijo);
    }
}
