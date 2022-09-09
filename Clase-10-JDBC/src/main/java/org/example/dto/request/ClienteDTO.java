package org.example.dto.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

//@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder

public class ClienteDTO extends PersonaDTO {
    //private Integer idCliente;
    private String vip;
    private int idPersona_cliente;
/*
    public ClienteDTO() {

    }

    public ClienteDTO(String nombre, String apellido, Integer edad, String direccion, Integer idCliente, String vip) {

        super(nombre, apellido, edad, direccion);
        this.idCliente = idCliente;
        this.vip = vip;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

 */
}
