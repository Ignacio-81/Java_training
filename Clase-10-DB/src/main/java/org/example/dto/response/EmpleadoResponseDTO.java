package org.example.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder


public class EmpleadoResponseDTO extends PersonaResponseDTO{

    private Integer idEmpleado;
    private Double sueldo;
    private Integer idPersona;

/*
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

 */
}
