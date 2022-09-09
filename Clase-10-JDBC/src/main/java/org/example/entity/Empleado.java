package org.example.entity;

public class Empleado extends Persona{

    protected Integer idEmpleado;
    protected Double sueldo;


    public Empleado() {
    }

    public Empleado(String nombre, String apellido, Integer edad, String direccion, Integer idEmpleado, Double sueldo) {
        super(nombre, apellido, edad, direccion);
        this.idEmpleado = idEmpleado;
        this.sueldo = sueldo;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }
}
