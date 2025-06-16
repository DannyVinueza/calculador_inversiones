package com.uisrael.calculador.service.impl;

import com.uisrael.calculador.entity.Simulacion;

import com.uisrael.calculador.repository.SimulacionRepository;
import com.uisrael.calculador.service.SimulacionService;

import java.util.List;

import org.springframework.stereotype.Service;

@Service

public class SimulacionServiceImpl implements SimulacionService {

    private final SimulacionRepository simulacionRepository;

    public SimulacionServiceImpl(SimulacionRepository simulacionRepository) {
        this.simulacionRepository = simulacionRepository;
    }

    @Override
    public List<Simulacion> get() {
        return simulacionRepository.findAll();
    }

    @Override
    public Simulacion create(Simulacion simulacion) {
        return simulacionRepository.save(simulacion);
    }
}
