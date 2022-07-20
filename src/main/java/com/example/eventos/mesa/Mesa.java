package com.example.eventos.mesa;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
}
