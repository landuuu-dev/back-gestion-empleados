package com.sistema.remuneraciones.controller;

import com.sistema.remuneraciones.entity.Empleado;
import com.sistema.remuneraciones.entity.Liquidacion;
import com.sistema.remuneraciones.service.RemuneracionService;
import com.sistema.remuneraciones.repository.EmpleadoRepository;
import com.sistema.remuneraciones.repository.LiquidacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/remuneraciones")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class RemuneracionController {

    @Autowired
    private RemuneracionService remuneracionService;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private LiquidacionRepository liquidacionRepository;

    // Registrar un nuevo empleado (Requerimiento del Administrador)
    @PostMapping("/empleados")
    public Empleado guardarEmpleado(@RequestBody Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    // Obtener todos los empleados
    @GetMapping("/empleados")
    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    // Calcular y guardar una liquidación (Proceso de Calidad)
    @PostMapping("/calcular/{rut}/{horas}")
    public Liquidacion procesarSueldo(@PathVariable String rut, @PathVariable double horas) {
        Empleado emp = empleadoRepository.findById(rut)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        Liquidacion nuevaLiq = remuneracionService.generarLiquidacion(emp, horas);
        return liquidacionRepository.save(nuevaLiq);
    }

    @DeleteMapping("/empleados/{rut}")
    public String eliminarEmpleado(@PathVariable String rut) {
        // 1. Buscamos si el empleado tiene liquidaciones asociadas
        // Usamos el repository de liquidaciones para contar cuántas tiene ese RUT
        List<Liquidacion> liquidaciones = liquidacionRepository.findAll().stream()
                .filter(l -> l.getEmpleado().getRut().equals(rut))
                .toList();

        if (!liquidaciones.isEmpty()) {
            // 2. Si tiene, enviamos el mensaje de advertencia en lugar de borrar
            return "Advertencia: No se puede eliminar al empleado porque tiene "
                    + liquidaciones.size() + " liquidaciones registradas. "
                    + "Primero debes eliminar sus liquidaciones.";
        }

        // 3. Si no tiene liquidaciones, procedemos a borrar
        empleadoRepository.deleteById(rut);
        return "Empleado eliminado con éxito.";
    }

    // Eliminar una liquidación específica por su ID
    @DeleteMapping("/liquidaciones/{id}")
    public void eliminarLiquidacion(@PathVariable Long id) {
        liquidacionRepository.deleteById(id);
    }

    // Listar todas las liquidaciones generadas para ver el historial en React
    @GetMapping("/liquidaciones")
    public List<Liquidacion> listarLiquidaciones() {
        return liquidacionRepository.findAll();
    }
}