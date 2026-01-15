package com.sistema.remuneraciones.service;

import com.sistema.remuneraciones.entity.Empleado;
import com.sistema.remuneraciones.entity.Liquidacion;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class RemuneracionService {

    private static final double PORCENTAJE_AFP = 0.10;
    private static final double PORCENTAJE_SALUD = 0.07;

    public Liquidacion generarLiquidacion(Empleado empleado, double horasTrabajadas) {
        Liquidacion liq = new Liquidacion();

        // 1. Cálculo de Sueldo Bruto (Redondeado)
        double bruto = Math.round(empleado.getValorHora() * horasTrabajadas);

        // 2. Cálculos de Descuentos (Redondeados)
        double afp = Math.round(bruto * PORCENTAJE_AFP);
        double salud = Math.round(bruto * PORCENTAJE_SALUD);

        // 3. Cálculo de Sueldo Líquido
        double liquido = bruto - afp - salud;

        liq.setEmpleado(empleado);
        liq.setFechaEmision(LocalDate.now());
        liq.setSueldoBruto(bruto);
        liq.setDescuentoAFP(afp);
        liq.setDescuentoSalud(salud);
        liq.setSueldoLiquido(liquido);

        return liq;
    }
}