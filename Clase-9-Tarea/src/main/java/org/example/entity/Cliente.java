package org.example.entity;
/**
 * Clase Para registrar Cliente - Hereda de Persona
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
public class Cliente extends Persona{
    protected Integer idCliente;
    protected String vip;

    public Cliente() {

    }

    public Cliente(String nombre, String apellido, Integer edad, String direccion, Integer idCliente, String vip) {

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
