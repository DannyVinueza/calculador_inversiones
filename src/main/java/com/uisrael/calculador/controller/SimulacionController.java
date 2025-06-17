package com.uisrael.calculador.controller;

import com.uisrael.calculador.entity.Simulacion;
import com.uisrael.calculador.service.SimulacionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/simulacion")
public class SimulacionController {

    private final SimulacionService simulacionService;

    public SimulacionController(SimulacionService simulacionService) {
        this.simulacionService = simulacionService;
    }

    @GetMapping
    public String listarSimulaciones(Model model){
        List<Simulacion> simulaciones = simulacionService.get();
        model.addAttribute("listarSimulaciones", simulaciones);
        return "listar";
    }

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("simulacion", new Simulacion()); 
        return "formulario"; 
    }


    @PostMapping("/guardar")
    public String guardarSimulacion(@ModelAttribute("simulacion") Simulacion simulacion) {

        Simulacion simulacionEntity = simulacionService.calcularSimulacion(simulacion);
        simulacionService.create(simulacionEntity);
        return "redirect:/simulacion";
    }
}
