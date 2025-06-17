package com.uisrael.calculador.service.impl;

import com.uisrael.calculador.entity.Simulacion;

import com.uisrael.calculador.repository.SimulacionRepository;
import com.uisrael.calculador.service.SimulacionService;

import java.time.LocalDateTime;
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

    @Override
    public Simulacion calcularSimulacion(Simulacion request) {
        Simulacion simulacionResponse = new Simulacion();
        simulacionResponse.setNombres(request.getNombres());
        simulacionResponse.setCedula(request.getCedula());
        simulacionResponse.setEmail(request.getEmail());
        simulacionResponse.setCelular(request.getCelular());
        simulacionResponse.setCapitalInicial(request.getCapitalInicial());
        simulacionResponse.setNumeroPeriodos(request.getNumeroPeriodos());
        simulacionResponse.setTiempoAnios(request.getTiempoAnios());

        double tasa = determinarTasaInteres(request.getNumeroPeriodos());
        simulacionResponse.setTasaInteres(tasa);

        double valorFuturo = calcularValorFuturo(request.getCapitalInicial(), tasa, request.getNumeroPeriodos(), request.getTiempoAnios());
        simulacionResponse.setValorFuturo(valorFuturo);

        simulacionResponse.setCategoria(determinarCategoria(request.getCapitalInicial(), request.getNumeroPeriodos()));

        double interesGanado = valorFuturo - request.getCapitalInicial();
        simulacionResponse.setInteresGanado(interesGanado);

        LocalDateTime fechaHoraActual = LocalDateTime.now();
        simulacionResponse.setFechaCreacion(fechaHoraActual);
        return simulacionResponse;
    }

    @Override
    public double determinarTasaInteres(int periodosPorAnio) {
        if (periodosPorAnio == 2) return 0.05;     // Semestral
        if (periodosPorAnio == 4) return 0.07;     // Trimestral
        if (periodosPorAnio == 12) return 0.11;    // Mensual
        throw new IllegalArgumentException("Número de periodos no válido");
    }

    @Override
    public double calcularValorFuturo(double capital, double tasa, int periodos, int anios) {
        return capital * Math.pow(1 + (tasa / periodos), periodos * anios);
    }

    @Override
    public int determinarCategoria(double capital, int periodos) {
        if (periodos == 12) {
            if (capital > 1000) return 1;
            if (capital > 500) return 2;
            if (capital >= 100) return 3;
        }
        return 0;
    }
}
