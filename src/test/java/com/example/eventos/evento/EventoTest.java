package com.example.eventos.evento;

import com.example.eventos.protagonista.Protagonista;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventoTest {
    Evento eventoNoProtagonistas;
    Evento eventoProtagonistas;
    Protagonista protagonista;
    List<Protagonista> protagonistas;

    @BeforeEach
    public void initEach(){
        protagonistas = new ArrayList<Protagonista>();
        protagonista = new Protagonista("Novio/a", "Pepe", "666777888", "pepe@correo.es");
        protagonistas.add(protagonista);
        eventoNoProtagonistas = new Evento("Boda", "Cena", 150, 10, "Aielo de Malferit", new Date(), "Boda-Cena");
        eventoProtagonistas = new Evento("id", "Comunión", "Comida", 50, 15, "Olleria", new Date(), 80, 15, true, protagonistas, "Comunión-Comida");
    }

    @Test
    public void constructorNoProtagonistasTest(){
        assertEquals("Boda", eventoNoProtagonistas.getTipo());
        assertEquals("Cena", eventoNoProtagonistas.getHorario());
        assertEquals(150, eventoNoProtagonistas.getPersonas());
        assertEquals(10, eventoNoProtagonistas.getNinyos());
        assertEquals("Aielo de Malferit", eventoNoProtagonistas.getLocalidad());
        assertEquals(new Date(), eventoNoProtagonistas.getFecha());
        assertEquals("Boda-Cena", eventoNoProtagonistas.getTitulo());
        assertEquals(new ArrayList<Protagonista>(), eventoNoProtagonistas.getProtagonistas());
    }

    @Test
    public void constructorConProtagonistasTest(){
        assertEquals("id", eventoProtagonistas.getId());
        assertEquals("Comunión", eventoProtagonistas.getTipo());
        assertEquals("Comida", eventoProtagonistas.getHorario());
        assertEquals(50, eventoProtagonistas.getPersonas());
        assertEquals(15, eventoProtagonistas.getNinyos());
        assertEquals("Olleria", eventoProtagonistas.getLocalidad());
        assertEquals(new Date(), eventoProtagonistas.getFecha());
        assertEquals(80, eventoProtagonistas.getPrecioMenu());
        assertEquals(15, eventoProtagonistas.getPrecioMenuNinyos());
        assertEquals("Comunión-Comida", eventoProtagonistas.getTitulo());
        assertEquals(protagonistas, eventoProtagonistas.getProtagonistas());
    }

    @Test
    public void toStringTest(){
        assertEquals("Personas: 150\nLocalidad: Aielo de Malferit\nConfirmada: No", eventoNoProtagonistas.toString());
        assertEquals("Personas: 50\nLocalidad: Olleria\nConfirmada: Sí", eventoProtagonistas.toString());
    }
}
