package org.example.service;
/**
 * Servicio Empleado -

 * Metodos:
 * @insertEmpleado (Empleado empleado)
 * @updateEmpleado EmpleadoResponseDTO (EmpleadoDTO empleadoDTO, Integer id)
 * @deleteEmpleado (Integer id)
 * @listarEmpleados  List<Empleado> ()

 */
import org.example.dto.request.EmpleadoDTO;
import org.example.dto.response.EmpleadoResponseDTO;
import org.example.entity.Empleado;

import java.util.List;

public interface IEmpleadoService {

    void insertEmpleado (Empleado empleado);
    EmpleadoResponseDTO updateEmpleado (EmpleadoDTO empleado , Integer id);
    void deleteEmpleado (Integer id);
    List<Empleado> listarEmpleados();
}
