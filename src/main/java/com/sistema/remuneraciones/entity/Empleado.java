package com.sistema.remuneraciones.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "empleados")
@Data
public class Empleado {
    @Id
    @Column(name = "rut_empleado")
    @NotBlank(message = "El RUT es obligatorio")
    private String rut;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String direccion;
    private String telefono;

    @Positive(message = "El valor hora debe ser positivo")
    private double valorHora;

    @PositiveOrZero
    private double valorHoraExtra;
}