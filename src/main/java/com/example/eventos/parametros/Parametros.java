package com.example.eventos.parametros;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("parametros")
public class Parametros {
    private float precioNinyosBodaComunion;
    private float precioNinyosOtros;
    private float ratioBeneficios;
    private float ratioCamarerosEvento;

    public Parametros() {
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
