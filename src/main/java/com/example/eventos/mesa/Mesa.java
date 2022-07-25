package com.example.eventos.mesa;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document("mesa")
public class Mesa {
    @Id
    private String id;

    private String idEvento;

    private String representante;
    private int personas;
    private int numero;
    private boolean pagado;

    public Mesa(String idEvento, String representante, int personas, int numero, boolean pagado) {
        this.idEvento = idEvento;
        this.representante = representante;
        this.personas = personas;
        this.numero = numero;
        this.pagado = pagado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public int getPersonas() {
        return personas;
    }

    public void setPersonas(int personas) {
        this.personas = personas;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mesa mesa = (Mesa) o;
        return personas == mesa.personas && numero == mesa.numero && pagado == mesa.pagado && Objects.equals(id, mesa.id) && Objects.equals(idEvento, mesa.idEvento) && Objects.equals(representante, mesa.representante);
    }
}
