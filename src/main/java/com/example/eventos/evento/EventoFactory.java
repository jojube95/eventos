package com.example.eventos.evento;

public class EventoFactory {
    public static Evento crearEvento(Evento evento) {
        if ("eventoIndividual".equals(evento.getTipo().getValue())) {
            return new EventoIndividual(evento.getTipo(), evento.getHorario(), evento.getPersonas(), evento.getPrecioMenu(), evento.getPrecioMenuNinyos(),
                    evento.getLocalidad(), evento.getFecha(), evento.getTitulo(), evento.getSala());
        }
        else if ("boda".equals(evento.getTipo().getValue())){
            return new EventoBoda(evento.getTipo(), evento.getHorario(), evento.getPersonas(), evento.getPrecioMenu(), evento.getPrecioMenuNinyos(),
                    evento.getLocalidad(), evento.getFecha(), evento.getTitulo(), evento.getSala());
        }
        else {
            return evento;
        }
    }
}
