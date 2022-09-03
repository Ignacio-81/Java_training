package org.example.service.Impl;

import org.example.dto.request.EmpleadoDTO;
import org.example.dto.response.EmpleadoResponseDTO;
import org.example.entity.Empleado;
import org.example.mapper.EmpleadoMapperImpl;
import org.example.service.IEmpleadoService;
import org.example.service.IServicios;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoServiceImpl implements IEmpleadoService {

    private EmpleadoMapperImpl empleadoMapperImpl = new EmpleadoMapperImpl();

    @Override
    public void insertEmpleado(Empleado empleado) {
        Empleado empleado1 = new Empleado();
        empleado1 = empleado;
        System.out.println("Insertando datos del Empleado: " + empleado1.toString());
    }

    @Override
    public EmpleadoResponseDTO updateEmpleado(EmpleadoDTO empleadoDTO, Integer id) {
        Empleado empleadoModificado = new Empleado();

        //recupero el nombre y apellido
        empleadoModificado.setNombre(empleadoDTO.getNombre());
        empleadoModificado.setApellido(empleadoDTO.getApellido());
        //aqui se modifica el registro

        return empleadoMapperImpl.EntityToDto(empleadoModificado);
    }

    @Override
    public void deleteEmpleado(Integer id)  {
        System.out.println("Borrando datos de Empleado: " + id);
    }

    @Override
    public List<Empleado> listarEmpleados() {
        //creo la persona
        Empleado auxEmpleado = new Empleado("Jose","Martinez",34,"Argentina",3002,300.3);
        //lista de personas
        List<Empleado> listaEmpleado = new ArrayList<>();
        //agrego la persona a lista
        listaEmpleado.add(auxEmpleado);
        //devuelvo la lista
        return listaEmpleado;
    }

    /*@Override
    public void insert(String nombre, String apellido){
        System.out.println("Insertando datos de empleado: " + nombre + " " + apellido);
    }

    @Override
    public String update(String nombre, String apellido, Integer id){

        return "Modificando datos de empleado: " + nombre + " " + apellido + " con el id: " + id;
    }

    @Override
    public String listarTodos(){

        return "Mostrando todos los datos de empleado ";
    }

    @Override
    public void delete(Integer id){
        System.out.println("Borrando datos de empleado: " + id);
    }*/
}
