package com.example.eventos.mesa;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MesaService {

    private final MesaRepository mesaRepository;

    public MesaService(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

    public List<Mesa> findByEvento(String idEvento){
        return mesaRepository.findByIdEvento(idEvento);
    }

    public List<Mesa> findByEventoOrderByNumero(String idEvento){
        return mesaRepository.findByIdEventoOrderByNumeroAsc(idEvento);
    }

    public void save(Mesa mesa){
        mesaRepository.save(mesa);
    }

    public void delete(Mesa mesa){
        mesaRepository.delete(mesa);
    }

    public void deleteMesas(String idEvento){
        mesaRepository.deleteByIdEvento(idEvento);
    }
}
