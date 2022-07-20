package com.example.eventos.mesa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class MesaRestController {
    @Autowired
    private MesaService mesaService;

    @PostMapping("/evento/mesas/add")
    public Mesa add(@RequestBody Mesa mesa, Model model){
        mesaService.save(mesa);
        return mesa;
    }

    @PostMapping("/evento/mesas/delete")
    public Mesa delete(@RequestBody Mesa mesa, Model model){
        mesaService.delete(mesa);
        return mesa;
    }

    @PostMapping("/evento/mesas/update")
    public Mesa update(@RequestBody Mesa mesa, Model model){
        mesaService.save(mesa);
        return mesa;
    }

}
