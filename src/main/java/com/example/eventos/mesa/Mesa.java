package com.example.eventos.mesa;

import com.example.eventos.personas.Personas;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Objects;

@Document("mesa")
@TypeAlias("Mesa")
public class Mesa {
    @Id
    private String id;
    private String eventoId;
    private String representante;
    private Personas personas;
    private int numero;
    private boolean pagado;
    private String descripcion;

    public Mesa(){

    }

    public Mesa(String eventoId, String representante, Personas personas, int numero, boolean pagado, String descripcion) {
        this.eventoId = eventoId;
        this.representante = representante;
        this.personas = personas;
        this.numero = numero;
        this.pagado = pagado;
        this.descripcion = descripcion;
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

    public Mesa(String id, String eventoId, String representante, Personas personas, int numero, boolean pagado, String descripcion) {
        this.id = id;
        this.eventoId = eventoId;
        this.representante = representante;
        this.personas = personas;
        this.numero = numero;
        this.pagado = pagado;
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

    public Phrase generatePhrase() {
        if(this.descripcion.isEmpty()){
            return new Phrase(this.toString(), new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD));
        }
        else{
            return new Phrase(this.toString(), new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.RED));
        }
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
                && numero == mesa.numero && pagado == mesa.pagado && Objects.equals(eventoId, mesa.eventoId)
                && Objects.equals(representante, mesa.representante) && Objects.equals(descripcion, mesa.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventoId, representante, personas, numero, pagado, descripcion);
    }
}
