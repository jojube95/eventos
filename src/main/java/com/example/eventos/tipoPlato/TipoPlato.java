package com.example.eventos.tipoPlato;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Objects;

@Document("tipoEvento")
@TypeAlias("TipoPlato")
public class TipoPlato {
    private String value;
    private int order;

    public TipoPlato() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "TipoPlato{" +
                "value='" + value + '\'' +
                ", order=" + order +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoPlato tipoPlato = (TipoPlato) o;
        return order == tipoPlato.order && Objects.equals(value, tipoPlato.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, order);
    }
}
