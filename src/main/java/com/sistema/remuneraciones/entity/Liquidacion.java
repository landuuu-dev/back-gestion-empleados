package com.sistema.remuneraciones.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "liquidaciones")
@Data
public class Liquidacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rut_empleado")
    private Empleado empleado;

    private LocalDate fechaEmision;
    private double sueldoBruto;
    private double descuentoAFP;
    private double descuentoSalud;
    private double sueldoLiquido;
}