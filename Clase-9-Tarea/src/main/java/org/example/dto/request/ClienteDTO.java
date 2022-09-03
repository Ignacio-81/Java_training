package org.example.dto.request;
/**
 * Request para Cliente - Hereda de PersonaDTO
 * Constructor:
 * @nombre String,
 * @apellido String,
 * @edad String,
 * @direccion String,
 * @dni String,
 * @idCliente Integer,
 * @vip String,

 * Metodos:
 * @Seters - idCliente, vip,
 * @Geters - idCliente, vip,
 */
public class ClienteDTO extends PersonaDTO {
    private Integer idCliente;
    private String vip;

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
}
