package com.example.eventos.horarioEvento;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HorarioEvento)) return false;
        HorarioEvento that = (HorarioEvento) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
