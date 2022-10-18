package com.example.eventos.eventoEmpleado;

import com.example.eventos.empleado.Empleado;
import com.example.eventos.evento.Evento;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Objects;

@Document("eventoEmpleado")
@TypeAlias("EventoEmpleado")
public class EventoEmpleado {
    @Id
    private String id;

    @DBRef
    private Evento evento;

    @DBRef
    private Empleado empleado;

    private boolean confirmado;
    private double horasExtras;

    public EventoEmpleado(){

    }

    public EventoEmpleado(String id, Evento evento, Empleado empleado, boolean confirmado, float horasExtras) {
        this.id = id;
        this.evento = evento;
        this.empleado = empleado;
        this.confirmado = confirmado;
        this.horasExtras = horasExtras;
    }

    public EventoEmpleado(Evento evento, Empleado empleado, boolean confirmado, float horasExtras) {
        this.evento = evento;
        this.empleado = empleado;
        this.confirmado = confirmado;
        this.horasExtras = horasExtras;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }

    public double getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(double horasExtras) {
        this.horasExtras = horasExtras;
    }

    @Override
    public String toString() {
        return "EventoEmpleado{" +
                "id='" + id + '\'' +
                ", evento=" + evento +
                ", empleado=" + empleado +
                ", confirmado=" + confirmado +
                ", horasExtras=" + horasExtras +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventoEmpleado that = (EventoEmpleado) o;
        return confirmado == that.confirmado && Double.compare(that.horasExtras, horasExtras) == 0 && Objects.equals(id, that.id) && Objects.equals(evento, that.evento) && Objects.equals(empleado, that.empleado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, evento, empleado, confirmado, horasExtras);
    }
}
