package com.example.eventos.evento;

import com.example.eventos.horarioEvento.HorarioEvento;
import com.example.eventos.personas.Personas;
import com.example.eventos.tipoEvento.TipoEvento;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document("evento")
@TypeAlias("EventoBoda")
public class EventoBoda extends Evento{
    public EventoBoda(String id, TipoEvento tipo, HorarioEvento horario, Personas personas, float precioMenu, float precioMenuNinyos, String localidad, Date fecha, String titulo, String sala) {
        super(id, tipo, horario, personas, precioMenu, precioMenuNinyos, localidad, fecha, titulo, sala);
    }

    @Override
    public int getCamarerosRecomendados(float ratioCamarerosEvento) {
        int camareros = Math.round(this.personas.getMayores() / ratioCamarerosEvento);
        camareros++;
        return camareros;
    }
}
