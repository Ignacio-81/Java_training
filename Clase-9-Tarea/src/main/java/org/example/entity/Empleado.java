package org.example.entity;

/**
 * Clase Para registrar Empleado - Hereda de Persona
 * Constructor:
 * @nombre String,
 * @apellido String,
 * @edad String,
 * @direccion String,
 * @dni String,
 * @idEmpleadp Integer,
 * @Sueldo Double,

 * Metodos:
 * @Seters - idEmppleado, Sueldo,
 * @Geters - idEmppleado, Sueldo,
 */

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
