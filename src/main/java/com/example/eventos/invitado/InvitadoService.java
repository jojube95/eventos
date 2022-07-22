package com.example.eventos.invitado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitadoService {
    @Autowired
    private InvitadoRepository invitadoRepository;

    public List<Invitado> findByMesa(String idMesa){
        return invitadoRepository.findByIdMesa(idMesa);
    }

    public void save(Invitado invitado){
        invitadoRepository.save(invitado);
    }

    public void delete(Invitado invitado){
        invitadoRepository.delete(invitado);
    }

    public void deleteInvitados(String idMesa){
        invitadoRepository.deleteByIdMesa(idMesa);
    }
}
