package com.example.eventos.evento;

import com.example.eventos.distribucion.Distribucion;
import com.example.eventos.horarioEvento.HorarioEvento;
import com.example.eventos.personas.Personas;
import com.example.eventos.protagonista.Protagonista;
import com.example.eventos.tipoEvento.TipoEvento;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document("evento")
@TypeAlias("EventoIndividual")
public class EventoIndividual extends Evento{
    public EventoIndividual(String id, TipoEvento tipo, HorarioEvento horario, Personas personas, String localidad, Date fecha, float precioMenu, float precioMenuNinyos,
                            boolean confirmado, List<Protagonista> protagonistas, String titulo, String sala, Distribucion distribucion) {
        super(id, tipo, horario, personas, localidad, fecha, precioMenu, precioMenuNinyos, confirmado, protagonistas, titulo, sala, distribucion);
    }

    // TODO: Probably should be part of javascript OOP
    @Override
    public boolean isEventoWithMesasConReserva() {
        return true;
    }
}
