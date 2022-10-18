package com.example.eventos.horarioEvento;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("horarioEvento")
@TypeAlias("HorarioEvento")
public class HorarioEvento {
    private String value;

    public HorarioEvento(){

    }

    public HorarioEvento(String value) {
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
        return "HorarioEvento{" +
                "value='" + value + '\'' +
                '}';
    }
}
