package com.example.eventos.personas;

import java.util.Objects;

public class Personas {
    private int mayores;
    private int ninyos;

    public Personas() {

    }

    public Personas(int mayores, int ninyos) {
        this.mayores = mayores;
        this.ninyos = ninyos;
    }

    public int getMayores() {
        return mayores;
    }

    public void setMayores(int mayores) {
        this.mayores = mayores;
    }

    public int getNinyos() {
        return ninyos;
    }

    public void setNinyos(int ninyos) {
        this.ninyos = ninyos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personas personas = (Personas) o;
        return mayores == personas.mayores && ninyos == personas.ninyos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mayores, ninyos);
    }

    @Override
    public String toString() {
        return "Personas{" +
                "mayores=" + mayores +
                ", ninyos=" + ninyos +
                '}';
    }
}
