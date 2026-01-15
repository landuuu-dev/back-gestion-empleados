package com.sistema.remuneraciones.repository;

import com.sistema.remuneraciones.entity.Liquidacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiquidacionRepository extends JpaRepository<Liquidacion, Long> {
    // Permite guardar el histórico de liquidaciones
}