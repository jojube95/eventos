package com.example.eventos.evento;

import com.example.eventos.protagonista.Protagonista;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class EventoTest {
    Evento eventoNoProtagonistas;
    Evento eventoProtagonistas;
    Evento eventoSimple;
    Protagonista protagonista;
    List<Protagonista> protagonistas;
    Date fecha;

    @BeforeEach
    public void initEach(){
        fecha = new GregorianCalendar(2022, Calendar.JULY, 25).getTime();
        protagonistas = new ArrayList<>();
        protagonista = new Protagonista("Novio/a", "Pepe", "666777888", "pepe@correo.es");
        protagonistas.add(protagonista);
        eventoSimple = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");
        eventoNoProtagonistas = new Evento("id2", "Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");
        eventoProtagonistas = new Evento("id", "Comunión", "Comida", 50, 15, "Olleria", fecha, 80, 15, true, protagonistas, "Comunión-Comida", "Sala1");
    }

    @Test
    void constructorTest(){
        assertEquals("Boda", eventoSimple.getTipo());
        assertEquals("Cena", eventoSimple.getHorario());
        assertEquals(150, eventoSimple.getPersonas());
        assertEquals(10, eventoSimple.getNinyos());
        assertEquals("Aielo de Malferit", eventoSimple.getLocalidad());
        assertEquals(new GregorianCalendar(2022, Calendar.JULY, 25).getTime(), eventoSimple.getFecha());
        assertEquals("Boda-Cena", eventoSimple.getTitulo());
        assertEquals("Sala1", eventoSimple.getSala());
    }

    @Test
    void constructorNoProtagonistasTest(){
        assertEquals("id2", eventoNoProtagonistas.getId());
        assertEquals("Boda", eventoNoProtagonistas.getTipo());
        assertEquals("Cena", eventoNoProtagonistas.getHorario());
        assertEquals(150, eventoNoProtagonistas.getPersonas());
        assertEquals(10, eventoNoProtagonistas.getNinyos());
        assertEquals("Aielo de Malferit", eventoNoProtagonistas.getLocalidad());
        assertEquals(new GregorianCalendar(2022, Calendar.JULY, 25).getTime(), eventoNoProtagonistas.getFecha());
        assertEquals("Boda-Cena", eventoNoProtagonistas.getTitulo());
        assertEquals("Sala1", eventoNoProtagonistas.getSala());
        assertEquals(new ArrayList<Protagonista>(), eventoNoProtagonistas.getProtagonistas());
    }

    @Test
    void constructorConProtagonistasTest(){
        assertEquals("id", eventoProtagonistas.getId());
        assertEquals("Comunión", eventoProtagonistas.getTipo());
        assertEquals("Comida", eventoProtagonistas.getHorario());
        assertEquals(50, eventoProtagonistas.getPersonas());
        assertEquals(15, eventoProtagonistas.getNinyos());
        assertEquals("Olleria", eventoProtagonistas.getLocalidad());
        assertEquals(new GregorianCalendar(2022, Calendar.JULY, 25).getTime(), eventoProtagonistas.getFecha());
        assertEquals(80, eventoProtagonistas.getPrecioMenu());
        assertEquals(15, eventoProtagonistas.getPrecioMenuNinyos());
        assertEquals("Comunión-Comida", eventoProtagonistas.getTitulo());
        assertEquals("Sala1", eventoProtagonistas.getSala());
        assertEquals(protagonistas, eventoProtagonistas.getProtagonistas());
    }

    @Test
    void toStringTest(){
        assertEquals("Personas: 150\nLocalidad: Aielo de Malferit\nConfirmada: No", eventoNoProtagonistas.toString());
        assertEquals("Personas: 50\nLocalidad: Olleria\nConfirmada: Sí", eventoProtagonistas.toString());
    }

    @Test
    void equalsTestFalseTipo(){
        Evento evento1 = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");
        Evento evento2 = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");
        evento2.setTipo("Comunión");

        assertNotEquals(evento1, evento2);
    }

    @Test
    void equalsTestFalseHorario(){
        Evento evento1 = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");
        Evento evento2 = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");
        evento2.setHorario("Comida");

        assertNotEquals(evento1, evento2);
    }

    @Test
    void equalsTestFalsePersonas(){
        Evento evento1 = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");
        Evento evento2 = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");
        evento2.setPersonas(151);

        assertNotEquals(evento1, evento2);
    }

    @Test
    void equalsTestFalseNinyos(){
        Evento evento1 = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");
        Evento evento2 = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");
        evento2.setNinyos(12);

        assertNotEquals(evento1, evento2);
    }

    @Test
    void equalsTestFalsePrecio(){
        Evento evento1 = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");
        Evento evento2 = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");
        evento2.setPrecioMenu(85);

        assertNotEquals(evento1, evento2);
    }

    @Test
    void equalsTestFalsePrecioNinyos(){
        Evento evento1 = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");
        Evento evento2 = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");
        evento2.setPrecioMenuNinyos(15);

        assertNotEquals(evento1, evento2);
    }

    @Test
    void equalsTestFalseTitulo(){
        Evento evento1 = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");
        Evento evento2 = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");
        evento2.setTitulo("AOAE");

        assertNotEquals(evento1, evento2);
    }

    @Test
    void equalsTestFalseLocalidad(){
        Evento evento1 = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");
        Evento evento2 = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");
        evento2.setLocalidad("Olleria");

        assertNotEquals(evento1, evento2);
    }

    @Test
    void equalsTestFalseFecha(){
        Evento evento1 = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");
        Evento evento2 = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");
        evento2.setFecha(new GregorianCalendar(2022, Calendar.JULY, 26).getTime());

        assertNotEquals(evento1, evento2);
    }

    @Test
    void equalsTestFalseSala(){
        Evento evento1 = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");
        Evento evento2 = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");
        evento2.setSala("Sala2");

        assertNotEquals(evento1, evento2);
    }

    @Test
    void equalsTestTrue(){
        Evento evento1 = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");
        Evento evento2 = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");

        assertEquals(evento1, evento2);
    }

    @Test
    void equalsTestNull(){
        Evento evento1 = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", fecha, "Boda-Cena", "Sala1");

        assertNotEquals(evento1, null);
    }
}
