package com.uisrael.calculador.service;

import com.uisrael.calculador.entity.Simulacion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SimulacionService {
    List<Simulacion> get();
    Simulacion create(Simulacion simulacion);
}
