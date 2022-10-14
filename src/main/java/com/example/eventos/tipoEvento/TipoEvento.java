package com.example.eventos.tipoEvento;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("tipoEvento")
public class TipoEvento {
    private String value;

    public TipoEvento(){

    }

    public TipoEvento(String value) {
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
        return "TipoEvento{" +
                "value='" + value + '\'' +
                '}';
    }
}
