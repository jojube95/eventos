package com.example.eventos.tipoProtagonista;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document("tipoProtagonista")
@TypeAlias("TipoProtagonista")
public class TipoProtagonista {
    private String value;

    public TipoProtagonista(){

    }

    public TipoProtagonista(String value) {
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
        return "TipoProtagonista{" +
                "value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TipoProtagonista)) return false;
        TipoProtagonista that = (TipoProtagonista) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
