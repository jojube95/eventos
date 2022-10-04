package com.example.eventos.mesa;

import com.example.eventos.personas.Personas;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Objects;

@Document("mesa")
public class Mesa {
    @Id
    private String id;
    private String idEvento;
    private String representante;
    private Personas personas;
    private int numero;
    private boolean pagado;
    private String descripcion;

    public Mesa(){

    }

    public Mesa(String idEvento, String representante, Personas personas, int numero, boolean pagado, String descripcion) {
        this.idEvento = idEvento;
        this.representante = representante;
        this.personas = personas;
        this.numero = numero;
        this.pagado = pagado;
        this.descripcion = descripcion;
    }

    public Mesa(String idEvento, Personas personas, int numero, String descripcion) {
        this.idEvento = idEvento;
        this.personas = personas;
        this.numero = numero;
        this.descripcion = descripcion;
    }

    public Mesa(String id, String idEvento, Personas personas, int numero, String descripcion) {
        this.id = id;
        this.idEvento = idEvento;
        this.personas = personas;
        this.numero = numero;
        this.descripcion = descripcion;
    }

    public Mesa(String id, String idEvento, String representante, Personas personas, int numero, boolean pagado, String descripcion) {
        this.id = id;
        this.idEvento = idEvento;
        this.representante = representante;
        this.personas = personas;
        this.numero = numero;
        this.pagado = pagado;
        this.descripcion = descripcion;
    }

    public Mesa(String textoExcel, String idEvento, Personas personas){
        this.idEvento = idEvento;
        this.personas = personas;

        String[] valoresMesa = textoExcel.split("-");
        this.numero = 0;
        this.descripcion = "";

        for (int i = 0; i < valoresMesa.length; i++) {
            if (i == 0) {
                this.numero = Integer.parseInt(valoresMesa[0].replaceAll("\\D", ""));
            }
            else{
                this.descripcion = valoresMesa[i].trim();
            }
        }
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

    public Personas getPersonas() {
        return personas;
    }

    public void setPersonas(Personas personas) {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        String res = "Mesa " + numero + ". " + personas.toString();

        if (!descripcion.isEmpty()) {
            res += ". Descripción: " + descripcion;
        }

        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mesa mesa = (Mesa) o;
        return Objects.equals(id, mesa.id) && personas.getMayores() == mesa.personas.getMayores() && personas.getNinyos() == mesa.personas.getNinyos() && numero == mesa.numero && pagado == mesa.pagado && Objects.equals(idEvento, mesa.idEvento) && Objects.equals(representante, mesa.representante) && Objects.equals(descripcion, mesa.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idEvento, representante, personas, numero, pagado, descripcion);
    }
}
