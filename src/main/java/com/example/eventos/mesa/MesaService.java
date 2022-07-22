package com.example.eventos.mesa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    public List<Mesa> findByEvento(String idEvento){
        return mesaRepository.findByIdEvento(idEvento);
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
