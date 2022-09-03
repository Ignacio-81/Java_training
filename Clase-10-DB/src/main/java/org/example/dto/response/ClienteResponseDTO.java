package org.example.dto.response;

public class ClienteResponseDTO extends PersonaResponseDTO {

    private Integer idCliente;
    private String vip;

    public ClienteResponseDTO() {

    }

    public ClienteResponseDTO(Integer id,String nombre, String apellido, Integer edad, String direccion, Integer idCliente, String vip) {

        super(id, nombre, apellido, edad, direccion);
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
}
