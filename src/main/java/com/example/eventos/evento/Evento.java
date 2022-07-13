package com.example.eventos.evento;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

@Document("evento")
public class Evento {
    @Id
    private String id;

    private String tipo;
    private String horario;
    private int personas;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    public Evento(String tipo, String horario, int personas, Date fecha) {
        super();
        this.tipo = tipo;
        this.horario = horario;
        this.personas = personas;
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return fecha.toString() + " - " + tipo + " - " + horario + " - " + personas;
    }

    public String getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getPersonas() {
        return personas;
    }

    public void setPersonas(int personas) {
        this.personas = personas;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return personas == evento.personas && Objects.equals(tipo, evento.tipo) && Objects.equals(horario, evento.horario);
    }
}
