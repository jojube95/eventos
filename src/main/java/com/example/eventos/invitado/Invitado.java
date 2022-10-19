package com.example.eventos.invitado;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

import static com.example.eventos.config.Constants.INVITADO_TIPO_MAYOR;
import static com.example.eventos.config.Constants.INVITADO_TIPO_NINYO;

@Document("invitado")
@TypeAlias("Invitado")
public class Invitado {
    @Id
    private String id;

    private String eventoId;
    private String mesaId;

    private String nombre;
    private String tipo;
    private String descripcion;

    public Invitado(){

    }

    public Invitado(String id, String eventoId, String mesaId, String nombre, String tipo, String descripcion) {
        this.id = id;
        this.eventoId = eventoId;
        this.mesaId = mesaId;
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public Invitado(String eventoId, String mesaId, String nombre, String tipo, String descripcion) {
        this.eventoId = eventoId;
        this.mesaId = mesaId;
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public Invitado(String textoExcel, String eventoId) {
        this.eventoId = eventoId;
        String[] myData = textoExcel.split("-");
        this.nombre = "";
        this.tipo = "";
        this.descripcion = "";

        for (int i = 0; i < myData.length; i++) {
            if (i == 0) {
                this.nombre = myData[0].trim();
            }
            else{
                if (myData[i].trim().equals("x")) {
                    this.tipo = INVITADO_TIPO_NINYO;
                }
                else{
                    this.descripcion = myData[i].trim();
                }
            }
        }
        if(this.tipo.isEmpty()){
            this.tipo = INVITADO_TIPO_MAYOR;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMesaId() {
        return mesaId;
    }

    public void setMesaId(String mesaId) {
        this.mesaId = mesaId;
    }

    public String getEventoId() {
        return eventoId;
    }

    public void setEventoId(String eventoId) {
        this.eventoId = eventoId;
    }

    public Phrase getPhrase() {
        if(this.descripcion.isEmpty()){
            return new Phrase(this.toString(), new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL));
        }
        else{
            return new Phrase(this.toString(), new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.RED));
        }
    }

    @Override
    public String toString() {
        String res = nombre;

        // TODO: Add polyformism
        if (INVITADO_TIPO_NINYO.equals(tipo)) {
            res += "-x";
        }

        if (!descripcion.isEmpty()) {
            res += "(" + descripcion + ")";
        }

        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invitado invitado = (Invitado) o;
        return Objects.equals(id, invitado.id) && Objects.equals(eventoId, invitado.eventoId) && Objects.equals(mesaId, invitado.mesaId)
                && Objects.equals(nombre, invitado.nombre) && Objects.equals(tipo, invitado.tipo)
                && Objects.equals(descripcion, invitado.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventoId, mesaId, nombre, tipo, descripcion);
    }
}
