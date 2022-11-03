package com.example.eventos.protagonista;

import com.example.eventos.persona.Persona;
import com.example.eventos.tipoProtagonista.TipoProtagonista;

import java.util.Objects;

public class Protagonista {
    private TipoProtagonista tipo;
    private Persona persona;


    public Protagonista() {

    }

    public Protagonista(TipoProtagonista tipo, Persona persona) {
        this.tipo = tipo;
        this.persona = persona;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public TipoProtagonista getTipo() {
        return tipo;
    }

    public void setTipo(TipoProtagonista tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Protagonista{" +
                "tipo='" + tipo + '\'' +
                ", persona=" + persona +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Protagonista that = (Protagonista) o;
        return Objects.equals(tipo, that.tipo) && Objects.equals(persona, that.persona);
    }

    @Override
    public int hashCode() {
        return Objects.hash(persona, tipo);
    }
}
