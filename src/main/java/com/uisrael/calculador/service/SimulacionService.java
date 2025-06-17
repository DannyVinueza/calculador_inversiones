package com.uisrael.calculador.service;

import com.uisrael.calculador.entity.Simulacion;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SimulacionService {
    List<Simulacion> get();
    Simulacion create(Simulacion simulacion);

    Simulacion calcularSimulacion(Simulacion simulacion);
    double determinarTasaInteres(int periodosPorAnio);
    double calcularValorFuturo(double capital, double tasa, int periodos, int anios);
    int determinarCategoria(double capital, int periodos);
}
