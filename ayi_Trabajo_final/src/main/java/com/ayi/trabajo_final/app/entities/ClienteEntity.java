package com.ayi.trabajo_final.app.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "clientes")//, indexes = @Index(name = "uniqueIndexPersona", columnList = "nombre, apellido", unique = true))


public class ClienteEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column(name = "dni", nullable = false)
    private Integer dni;

    @Column(name = "direccion", nullable = false, length = 50)
    private String direccion;

    @OneToMany (cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable (name = "tbl_clientes_direcciones", joinColumns = @JoinColumn(name="id_cliente"),
            inverseJoinColumns = @JoinColumn(name = "id_direccion")
            , uniqueConstraints = @UniqueConstraint(columnNames={"id_direccion"}))

    private List<DireccionEntity> direcciones;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cliente")
    private List<FacturaEntity> facturas;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cliente")
    private DetalleClienteEntity detalle;

    public ClienteEntity(String nombre, String apellido, Integer dni, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.direccion = direccion;
    }

/*    public ClienteEntity(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }*/

}
