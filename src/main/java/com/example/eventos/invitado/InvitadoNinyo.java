package com.example.eventos.invitado;

import com.example.eventos.personas.Personas;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("invitado")
@TypeAlias("InvitadoNinyo")
public class InvitadoNinyo extends Invitado{

    public InvitadoNinyo(String id, String eventoId, String mesaId, String nombre, String tipo, String descripcion) {
        super(id, eventoId, mesaId, nombre, tipo, descripcion);
    }

    @Override
    public String generateTextoListado() {
        return this.nombre + "-x";
    }

    @Override
    public Personas incrementPersonas(Personas personas) {
        int niynos = personas.getNinyos() + 1;
        personas.setNinyos(niynos);

        return personas;
    }
}
