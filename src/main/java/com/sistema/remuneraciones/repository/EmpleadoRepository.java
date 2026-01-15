package com.sistema.remuneraciones.repository;

import com.sistema.remuneraciones.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, String> {
    // Permite buscar, guardar y eliminar empleados en la BD
}