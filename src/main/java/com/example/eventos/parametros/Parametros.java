package com.example.eventos.parametros;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

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
}
