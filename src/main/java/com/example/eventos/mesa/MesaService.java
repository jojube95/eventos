package com.example.eventos.mesa;

import com.example.eventos.invitado.Invitado;
import com.example.eventos.invitado.InvitadoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MesaService {

    private final MesaRepository mesaRepository;
    private final InvitadoRepository invitadoRepository;

    public MesaService(MesaRepository mesaRepository, InvitadoRepository invitadoRepository) {
        this.mesaRepository = mesaRepository;
        this.invitadoRepository = invitadoRepository;
    }

    public List<Mesa> findByEvento(String idEvento){
        return mesaRepository.findByIdEvento(idEvento);
    }

    public List<Mesa> findByEventoOrderByNumero(String idEvento){
        return mesaRepository.findByIdEventoOrderByNumeroAsc(idEvento);
    }

    public Mesa save(Mesa mesa){
        return mesaRepository.save(mesa);
    }

    public void delete(Mesa mesa){
        invitadoRepository.deleteByIdMesa(mesa.getId());
        mesaRepository.delete(mesa);
    }

    public void deleteMesas(String idEvento){
        invitadoRepository.deleteByIdEvento(idEvento);
        mesaRepository.deleteByIdEvento(idEvento);
    }

    public void generateInvitados(Mesa mesa) {
        for (int i = 1; i <= mesa.getPersonas().getMayores(); i++) {
            invitadoRepository.save(new Invitado(mesa.getIdEvento(), mesa.getId(), "Invitado" + i, "Mayor", ""));
        }
        for (int i = 1; i <= mesa.getPersonas().getNinyos(); i++) {
            invitadoRepository.save(new Invitado(mesa.getIdEvento(), mesa.getId(), "Niño" + i, "Ninyo", ""));
        }
    }
}
