package org.example.controller;

import org.example.dto.request.EmpleadoDTO;
import org.example.dto.response.EmpleadoResponseDTO;
import org.example.entity.Empleado;
import org.example.service.IEmpleadoService;
import org.example.service.IServicios;
import org.example.service.Impl.EmpleadoServiceImpl;

import java.util.List;


public class EmpleadoController {

    private IEmpleadoService iServiciosEmpleado= new EmpleadoServiceImpl();

    public void agregarEmpleado (Empleado empleado){

        iServiciosEmpleado.insertEmpleado(empleado);
    }
    public void borrarEmpleado (Integer id){
        iServiciosEmpleado.deleteEmpleado(id);
    }
    public List<Empleado> listarEmpleados (){
        return iServiciosEmpleado.listarEmpleados();
    }

    public EmpleadoResponseDTO modificarEmpleado(EmpleadoDTO empleadoDTO, Integer id){
        return iServiciosEmpleado.updateEmpleado(empleadoDTO, id);
    }
}
