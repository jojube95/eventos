package com.example.eventos.evento;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document("evento")
public class Evento {
    @Id
    private String id;

    private String tipo;
    private String horario;
    private int personas;

    public Evento(String tipo, String horario, int personas) {
        super();
        this.tipo = tipo;
        this.horario = horario;
        this.personas = personas;
    }

    @Override
    public String toString() {
        return tipo + " - " + horario + " - " + personas;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return personas == evento.personas && Objects.equals(tipo, evento.tipo) && Objects.equals(horario, evento.horario);
    }
}
