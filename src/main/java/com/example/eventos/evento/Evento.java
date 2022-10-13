package com.example.eventos.evento;

import com.example.eventos.distribucion.Distribucion;
import com.example.eventos.personas.Personas;
import com.example.eventos.protagonista.Protagonista;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import static com.example.eventos.config.Constants.EVENTO_FECHA_FORMAT;

@Document("evento")
public class Evento {
    @Id
    private String id;

    private String tipo;
    private String horario;
    private Personas personas;
    private String localidad;
    private float precioMenu;
    private float precioMenuNinyos;
    private boolean confirmado;
    private String titulo;
    private String sala;

    @DateTimeFormat(pattern = EVENTO_FECHA_FORMAT)
    private Date fecha;

    private List<Protagonista> protagonistas;

    private Distribucion distribucion;

    public Evento() {

    }

    public Evento(String tipo, String horario, Personas personas, String localidad, Date fecha, String titulo, String sala) {
        super();
        this.tipo = tipo;
        this.horario = horario;
        this.personas = personas;
        this.localidad = localidad;
        this.fecha = fecha;
        this.titulo = titulo;
        this.sala = sala;
        this.confirmado = false;
    }

    public Evento(String id, String tipo, String horario, Personas personas, String localidad, Date fecha, String titulo, String sala) {
        super();
        this.id = id;
        this.tipo = tipo;
        this.horario = horario;
        this.personas = personas;
        this.localidad = localidad;
        this.fecha = fecha;
        this.titulo = titulo;
        this.sala = sala;
        this.confirmado = false;
    }

    public Evento(String id, String tipo, String horario, Personas personas, String localidad, Date fecha, float precioMenu, float precioMenuNinyos,
                  boolean confirmado, List<Protagonista> protagonistas, String titulo, String sala, Distribucion distribucion) {
        super();
        this.id = id;
        this.tipo = tipo;
        this.horario = horario;
        this.personas = personas;
        this.localidad = localidad;
        this.fecha = fecha;
        this.precioMenu = precioMenu;
        this.precioMenuNinyos = precioMenuNinyos;
        this.confirmado = confirmado;
        this.protagonistas = protagonistas;
        this.sala = sala;
        this.titulo = titulo;
        this.distribucion = distribucion;
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

    public Personas getPersonas() {
        return personas;
    }

    public void setPersonas(Personas personas) {
        this.personas = personas;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public List<Protagonista> getProtagonistas() {
        if (protagonistas == null){
            return new ArrayList<>();
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

    public Distribucion getDistribucion() {
        if (distribucion == null){
            return new Distribucion("");
        }
        else{
            return distribucion;
        }
    }

    public void setDistribucion(Distribucion distribucion) {
        this.distribucion = distribucion;
    }

    public boolean isEventoIndividual(){
        return "Evento individual".equals(this.tipo);
    }

    public int getCamarerosRecomendados(float ratioCamarerosEvento){
        int camareros = Math.round(this.personas.getMayores() / ratioCamarerosEvento);

        if("Boda".equals(this.tipo)){
            camareros++;
        }

        return camareros;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return Objects.equals(id, evento.id) && personas.getMayores() == evento.personas.getMayores()
                && personas.getNinyos() == evento.personas.getNinyos() && Float.compare(evento.precioMenu, precioMenu) == 0
                && Float.compare(evento.precioMenuNinyos, precioMenuNinyos) == 0 && confirmado == evento.confirmado
                && Objects.equals(tipo, evento.tipo) && Objects.equals(horario, evento.horario) && Objects.equals(localidad, evento.localidad)
                && Objects.equals(titulo, evento.titulo) && Objects.equals(fecha.getTime(), evento.fecha.getTime())
                && Objects.equals(protagonistas, evento.protagonistas) && Objects.equals(sala, evento.sala);
    }

    @Override
    public String toString() {
        return "Personas: " + personas.getMayores() + "\n" +
                "Localidad: " + localidad + "\n" +
                "Confirmada: " + (confirmado ? "Sí" : "No");
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, horario, personas, localidad, precioMenu, precioMenuNinyos, confirmado, titulo, fecha, protagonistas);
    }
}
