package com.example.eventos.tipoEmpleado;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("tipoEmpleado")
public class TipoEmpleado {
    private String value;

    public TipoEmpleado(){

    }

    public TipoEmpleado(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TipoEmpleado{" +
                "value='" + value + '\'' +
                '}';
    }
}
