package org.example.dto.response;
/**
 * Request para Empleado - Hereda de PersonaResponseDTO
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
public class EmpleadoResponseDTO extends PersonaResponseDTO{

    private Integer idEmpleado;
    private Double sueldo;


    public EmpleadoResponseDTO() {
    }

    public EmpleadoResponseDTO(Integer id, String nombre, String apellido, Integer edad, String direccion, Integer idEmpleado, Double sueldo) {
        super(id, nombre, apellido, edad, direccion);
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
