package org.curso.ayi.jpa.app.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class ClienteResponseDTO {
    //Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column (name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column (name = "forma_pago", nullable = false, length = 15)
    private String formaPago;

    @Column (name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;
}
