package com.example.eventos.mesa;

import com.example.eventos.invitado.Invitado;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("mesa")
public class Mesa {
    @Id
    private String id;

    private String idEvento;

    private String representante;
    private int personas;
    private int numero;
    private boolean pagado;
    private List<Invitado> invitados;

    public Mesa(String idEvento, String representante, int personas, int numero, boolean pagado, List<Invitado> invitados) {
        this.idEvento = idEvento;
        this.representante = representante;
        this.personas = personas;
        this.numero = numero;
        this.pagado = pagado;
        this.invitados = invitados;
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

    public List<Invitado> getInvitados() {
        return invitados;
    }

    public void setInvitados(List<Invitado> invitados) {
        this.invitados = invitados;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
