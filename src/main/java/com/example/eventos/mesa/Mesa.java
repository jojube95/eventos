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
    private int ninyos;
    private int numero;
    private boolean pagado;
    private String descripcion;

    public Mesa(){

    }

    public Mesa(String idEvento, String representante, int personas, int ninyos, int numero, boolean pagado, String descripcion) {
        this.idEvento = idEvento;
        this.representante = representante;
        this.personas = personas;
        this.ninyos = ninyos;
        this.numero = numero;
        this.pagado = pagado;
        this.descripcion = descripcion;
    }

    public Mesa(String idEvento, int personas, int ninyos, int numero, String descripcion) {
        this.idEvento = idEvento;
        this.personas = personas;
        this.ninyos = ninyos;
        this.numero = numero;
        this.descripcion = descripcion;
    }

    public Mesa(String id, String idEvento, int personas, int ninyos, int numero, String descripcion) {
        this.id = id;
        this.idEvento = idEvento;
        this.personas = personas;
        this.ninyos = ninyos;
        this.numero = numero;
        this.descripcion = descripcion;
    }

    public Mesa(String id, String idEvento, String representante, int personas, int ninyos, int numero, boolean pagado, String descripcion) {
        this.id = id;
        this.idEvento = idEvento;
        this.representante = representante;
        this.personas = personas;
        this.ninyos = ninyos;
        this.numero = numero;
        this.pagado = pagado;
        this.descripcion = descripcion;
    }

    public Mesa(String textoExcel, String idEvento, int personas, int ninyos){
        this.idEvento = idEvento;
        this.personas = personas;
        this.ninyos = ninyos;

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

    public int getPersonas() {
        return personas;
    }

    public void setPersonas(int personas) {
        this.personas = personas;
    }

    public int getNinyos() {
        return ninyos;
    }

    public void setNinyos(int ninyos) {
        this.ninyos = ninyos;
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
        return "Mesa{" +
                "id='" + id + '\'' +
                ", idEvento='" + idEvento + '\'' +
                ", representante='" + representante + '\'' +
                ", personas=" + personas +
                ", ninyos=" + ninyos +
                ", numero=" + numero +
                ", pagado=" + pagado +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mesa mesa = (Mesa) o;
        return Objects.equals(id, mesa.id) && personas == mesa.personas && ninyos == mesa.ninyos && numero == mesa.numero && pagado == mesa.pagado && Objects.equals(idEvento, mesa.idEvento) && Objects.equals(representante, mesa.representante) && Objects.equals(descripcion, mesa.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idEvento, representante, personas, ninyos, numero, pagado, descripcion);
    }
}
