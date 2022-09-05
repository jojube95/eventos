package com.example.eventos.distribucion;

import java.util.Objects;

public class Distribucion {
    private String distribucion;

    public Distribucion() {

    }

    public Distribucion(String distribucion) {
        this.distribucion = distribucion;
    }

    public String getDistribucion() {
        return distribucion;
    }

    public void setDistribucion(String distribucion) {
        this.distribucion = distribucion;
    }

    @Override
    public String toString() {
        return "Distribucion{" +
                "distribucion='" + distribucion + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distribucion that = (Distribucion) o;
        return Objects.equals(distribucion, that.distribucion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(distribucion);
    }
}
