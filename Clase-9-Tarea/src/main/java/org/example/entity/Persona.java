package org.example.entity;

/**
 * Clase Para registrar Persona - Informacion persona
 * Constructor:
 * @nombre String,
 * @apellido String,
 * @edad String,
 * @direccion String,
 * @dni String

 * Metodos:
 * @Seters - nombre, apellido, edad , direccion.
 * @Geters - nombre, apellido, edad , direccion.
 * @ToString - nombre, apellido, edad , direccion.

 */

public class Persona {
    protected String nombre;
    protected String apellido;
    protected Integer edad;
    protected String direccion;

    public Persona() {
    }

    public Persona(String nombre, String apellido, Integer edad, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
