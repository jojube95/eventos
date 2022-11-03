package com.example.eventos.evento;

import com.example.eventos.horarioEvento.HorarioEvento;
import com.example.eventos.personas.Personas;
import com.example.eventos.tipoEvento.TipoEvento;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("evento")
@TypeAlias("EventoIndividual")
public class EventoIndividual extends Evento{
    public EventoIndividual(String id, TipoEvento tipo, HorarioEvento horario, Personas personas, float precioMenu, float precioMenuNinyos, String localidad, Date fecha, String titulo, String sala) {
        super(id, tipo, horario, personas, precioMenu, precioMenuNinyos, localidad, fecha, titulo, sala);
    }

    // TODO: Probably should be part of javascript OOP
    @Override
    public boolean isEventoWithMesasConReserva() {
        return true;
    }
}
