package org.example.service;

import org.example.dto.request.EmpleadoDTO;
import org.example.dto.response.EmpleadoResponseDTO;
import org.example.dto.response.PersonaResponseDTO;
import org.example.entity.Empleado;

import java.util.List;

public interface IEmpleadoService {

    public List<EmpleadoResponseDTO> getAllEmpleados();

    public Integer insertEmpleado(EmpleadoDTO empleado);

    public Integer updateEmpleado(EmpleadoDTO empleadoDTO, Integer id);
    public int deleteEmpleado(Integer id);
}
