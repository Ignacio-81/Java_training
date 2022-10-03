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


public class CustomerEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "lastName", nullable = false, length = 50)
    private String lastName;

    @Column(name = "dni", nullable = false)
    private Integer dni;

/*    @Column(name = "address", nullable = false, length = 50)
    private String address;*/

    @OneToMany (cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable (name = "tbl_clientes_direcciones", joinColumns = @JoinColumn(name="id_customer"),
            inverseJoinColumns = @JoinColumn(name = "id_address")
            , uniqueConstraints = @UniqueConstraint(columnNames={"id_address"}))

    private List<AddressEntity> address;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "customer")
    private List<TicketEntity> tickets;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "customer")
    private CustomerDetailEntity details;

    public CustomerEntity(String name, String lastName, Integer dni, String address) {
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        //this.address = address;
    }

/*    public ClienteEntity(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }*/

}
