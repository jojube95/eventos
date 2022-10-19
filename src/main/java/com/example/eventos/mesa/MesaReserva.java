package com.example.eventos.mesa;

import com.example.eventos.personas.Personas;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.DEDUCTION;

@Document("mesa")
@TypeAlias("MesaReserva")
@JsonTypeInfo(use = DEDUCTION)
@JsonSubTypes( {@Type(Mesa.class), @Type(MesaReserva.class)})
public class MesaReserva extends Mesa{
    private String representante;
    private boolean pagado;

    public MesaReserva(String id, String eventoId, Personas personas, int numero, String descripcion, String representante, boolean pagado) {
        super(id, eventoId, personas, numero, descripcion);
        this.representante = representante;
        this.pagado = pagado;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    @Override
    public String toString() {
        return "MesaReserva{" +
                "representante='" + representante + '\'' +
                ", pagado=" + pagado +
                ", id='" + id + '\'' +
                ", eventoId='" + eventoId + '\'' +
                ", personas=" + personas +
                ", numero=" + numero +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MesaReserva mesa = (MesaReserva) o;
        return Objects.equals(id, mesa.id) && personas.getMayores() == mesa.personas.getMayores() && personas.getNinyos() == mesa.personas.getNinyos()
                && numero == mesa.numero && pagado == mesa.pagado && Objects.equals(eventoId, mesa.eventoId)
                && Objects.equals(representante, mesa.representante) && Objects.equals(descripcion, mesa.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventoId, representante, personas, numero, pagado, descripcion);
    }
}
