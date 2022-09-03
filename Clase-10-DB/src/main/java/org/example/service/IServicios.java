package org.example.service;

import org.example.entity.Cliente;
import org.example.entity.Empleado;
import org.example.entity.Persona;

public interface IServicios {

    void insert(String nombre, String apellido);
    String update(String nombre, String apellido, Integer id);
    int delete(Integer id);
    String listarTodos();

}
