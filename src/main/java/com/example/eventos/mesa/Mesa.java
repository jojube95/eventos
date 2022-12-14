package com.example.eventos.mesa;

import com.example.eventos.personas.Personas;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.DEDUCTION;

@Document("mesa")
@TypeAlias("Mesa")
@JsonTypeInfo(use = DEDUCTION, defaultImpl = Mesa.class)
@JsonSubTypes( {@JsonSubTypes.Type(Mesa.class), @JsonSubTypes.Type(MesaReserva.class)})
public class Mesa {
    @Id
    protected String id;
    protected String eventoId;
    protected Personas personas;
    protected int numero;
    protected String descripcion;

    public Mesa(){

    }

    public Mesa(String eventoId, Personas personas, int numero, String descripcion) {
        this.eventoId = eventoId;
        this.personas = personas;
        this.numero = numero;
        this.descripcion = descripcion;
    }

    public Mesa(String id, String eventoId, Personas personas, int numero, String descripcion) {
        this.id = id;
        this.eventoId = eventoId;
        this.personas = personas;
        this.numero = numero;
        this.descripcion = descripcion;
    }

    public Mesa(String textoExcel, String eventoId, Personas personas){
        this.eventoId = eventoId;
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

    public String getEventoId() {
        return eventoId;
    }

    public void setEventoId(String eventoId) {
        this.eventoId = eventoId;
    }

    public Personas getPersonas() {
        return personas;
    }

    public void setPersonas(Personas personas) {
        this.personas = personas;
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

    public Phrase generatePhrase() {
        if(this.descripcion.isEmpty()){
            return new Phrase(this.toString(), new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD));
        }
        else{
            return new Phrase(this.toString(), new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.RED));
        }
    }

    public Personas incrementPersonas(Personas personas) {
        int mayores = personas.getMayores() + this.getPersonas().getMayores();
        int niynos = personas.getNinyos() + this.getPersonas().getNinyos();

        personas.setMayores(mayores);
        personas.setNinyos(niynos);

        return personas;
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
        return Objects.equals(id, mesa.id) && personas.getMayores() == mesa.personas.getMayores() && personas.getNinyos() == mesa.personas.getNinyos()
                && numero == mesa.numero && Objects.equals(eventoId, mesa.eventoId)
                && Objects.equals(descripcion, mesa.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventoId, personas, numero, descripcion);
    }
}
