package com.example.eventos.evento;

import com.example.eventos.protagonista.Protagonista;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Document("evento")
public class Evento {
    @Id
    private String id;

    private String tipo;
    private String horario;
    private int personas;
    private int ninyos;
    private String localidad;
    private float precioMenu;
    private float precioMenuNinyos;
    private boolean confirmado;
    private String titulo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    private List<Protagonista> protagonistas;

    public Evento() {

    }

    public Evento(String tipo, String horario, int personas, int ninyos, String localidad, Date fecha, String titulo) {
        super();
        this.tipo = tipo;
        this.horario = horario;
        this.personas = personas;
        this.ninyos = ninyos;
        this.localidad = localidad;
        this.fecha = fecha;
        this.titulo = titulo;
    }

    public Evento(String id, String tipo, String horario, int personas, int ninyos, String localidad, Date fecha, float precioMenu,
                  float precioMenuNinyos, boolean confirmado, List<Protagonista> protagonistas, String titulo) {
        super();
        this.id = id;
        this.tipo = tipo;
        this.horario = horario;
        this.personas = personas;
        this.ninyos = ninyos;
        this.localidad = localidad;
        this.fecha = fecha;
        this.precioMenu = precioMenu;
        this.precioMenuNinyos = precioMenuNinyos;
        this.confirmado = confirmado;
        this.protagonistas = protagonistas;
        this.titulo = titulo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getPersonas() {
        return personas;
    }

    public void setPersonas(int personas) {
        this.personas = personas;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNinyos() {
        return ninyos;
    }

    public void setNinyos(int ninyos) {
        this.ninyos = ninyos;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public float getPrecioMenu() {
        return precioMenu;
    }

    public void setPrecioMenu(float precioMenu) {
        this.precioMenu = precioMenu;
    }

    public float getPrecioMenuNinyos() {
        return precioMenuNinyos;
    }

    public void setPrecioMenuNinyos(float precioMenuNinyos) {
        this.precioMenuNinyos = precioMenuNinyos;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }

    public List<Protagonista> getProtagonistas() {
        if (protagonistas == null){
            return new ArrayList<Protagonista>();
        }
        else{
            return protagonistas;
        }

    }

    public void setProtagonistas(List<Protagonista> protagonistas) {
        this.protagonistas = protagonistas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return personas == evento.personas && Objects.equals(tipo, evento.tipo) && Objects.equals(horario, evento.horario);
    }

    @Override
    public String toString() {
        return "Personas: " + personas + "\n" +
                "Localidad: " + localidad + "\n" +
                "Confirmada: " + (confirmado ? "Sí" : "No");
    }
}
