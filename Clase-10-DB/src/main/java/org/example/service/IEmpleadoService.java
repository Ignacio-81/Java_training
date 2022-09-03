package org.example.service;

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
