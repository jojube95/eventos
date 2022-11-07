package com.example.eventos.tipoEmpleado;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document("tipoEmpleado")
@TypeAlias("TipoEmpleado")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TipoEmpleado)) return false;
        TipoEmpleado that = (TipoEmpleado) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
