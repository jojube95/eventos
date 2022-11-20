package com.example.eventos.invitado;

import com.example.eventos.personas.Personas;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("invitado")
@TypeAlias("InvitadoMayor")
public class InvitadoMayor extends Invitado{

    public InvitadoMayor(String id, String eventoId, String mesaId, String nombre, String tipo, String descripcion) {
        super(id, eventoId, mesaId, nombre, tipo, descripcion);
    }

    @Override
    public String generateTextoListado() {
        return this.nombre;
    }

    @Override
    public Personas incrementPersonas(Personas personas) {
        int mayores = personas.getMayores() + 1;
        personas.setMayores(mayores);

        return personas;
    }
}
