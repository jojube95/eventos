package com.example.eventos.parametros;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document("parametros")
@TypeAlias("Parametros")
public class Parametros {
    private float precioNinyosBodaComunion;
    private float precioNinyosOtros;
    private float ratioBeneficios;
    private float ratioCamarerosEvento;

    public Parametros() {
    }

    public Parametros(float precioNinyosBodaComunion, float precioNinyosOtros, float ratioBeneficios, float ratioCamarerosEvento) {
        this.precioNinyosBodaComunion = precioNinyosBodaComunion;
        this.precioNinyosOtros = precioNinyosOtros;
        this.ratioBeneficios = ratioBeneficios;
        this.ratioCamarerosEvento = ratioCamarerosEvento;
    }

    public float getPrecioNinyosBodaComunion() {
        return precioNinyosBodaComunion;
    }

    public float getPrecioNinyosOtros() {
        return precioNinyosOtros;
    }

    public float getRatioBeneficios() {
        return ratioBeneficios;
    }

    public float getRatioCamarerosEvento() {
        return ratioCamarerosEvento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parametros)) return false;
        Parametros that = (Parametros) o;
        return Float.compare(that.precioNinyosBodaComunion, precioNinyosBodaComunion) == 0 && Float.compare(that.precioNinyosOtros, precioNinyosOtros) == 0 && Float.compare(that.ratioBeneficios, ratioBeneficios) == 0 && Float.compare(that.ratioCamarerosEvento, ratioCamarerosEvento) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(precioNinyosBodaComunion, precioNinyosOtros, ratioBeneficios, ratioCamarerosEvento);
    }
}
