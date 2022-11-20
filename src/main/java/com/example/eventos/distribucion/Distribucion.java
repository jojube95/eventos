package com.example.eventos.distribucion;

import java.util.Objects;

public class Distribucion {
    private String mapa;

    public Distribucion() {

    }

    public Distribucion(String mapa) {
        this.mapa = mapa;
    }

    public String getMapa() {
        return mapa;
    }

    public void setMapa(String mapa) {
        this.mapa = mapa;
    }

    @Override
    public String toString() {
        return "Distribucion{" +
                "mapa='" + mapa + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distribucion that = (Distribucion) o;
        return Objects.equals(mapa, that.mapa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mapa);
    }
}
