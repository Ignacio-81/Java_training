package org.example.controller;
/**
 * Implementacion Controller Empleado

 * Metodos:
 * @listarEmpleados List<EmpleadoResponseDTO> ()
 * @agregarEmpleado Integer (EmpleadoDTO empleados)
 * @modificarEmpleado Integer (EmpleadoDTO empleadoDTO, Integer id)
 * @borrarEmpleado int (Integer id)

 */
import org.example.dto.request.EmpleadoDTO;
import org.example.dto.response.EmpleadoResponseDTO;
import org.example.service.IEmpleadoService;
import org.example.service.IServicios;
import org.example.service.Impl.EmpleadoServiceImpl;

import java.util.List;


public class EmpleadoController {

    private IEmpleadoService iServiciosEmpleado= new EmpleadoServiceImpl();

    public List<EmpleadoResponseDTO> listarEmpleados() {
        return iServiciosEmpleado.getAllEmpleados();
    }

    public Integer agregarEmpleado(EmpleadoDTO empleado){
        return iServiciosEmpleado.insertEmpleado(empleado);
    }
    public Integer modificarEmpleado(EmpleadoDTO empleadoDTO, Integer id) {
        return iServiciosEmpleado.updateEmpleado(empleadoDTO, id);
    }
    public int borrarEmpleado(Integer id){
        return iServiciosEmpleado.deleteEmpleado(id);
    }

/*
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

 */
}
