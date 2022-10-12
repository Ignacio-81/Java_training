package com.ayi.trabajo_final.app.entities;
/**
 * Customer Entity
 * @Table (name = "customers")
 * Properties:
 * @id Long;
 * @firstName String;
 * @lastName Integer;
 * @documentNumbre Integer;
 * @dateCreated String;
 * @dateModified String;
 *

 */
import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "customers")//, indexes = @Index(name = "uniqueIndexPersona", columnList = "nombre, apellido", unique = true))


public class CustomerEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstName", nullable = false, length = 50)
    private String firstName;

    @Column(name = "lastName", nullable = false, length = 50)
    private String lastName;

    @Column(name = "docNumber", nullable = false)
    private Integer documentNumber;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "customer") //
    private List<TicketEntity> tickets = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "customer")
    private CustomerDetailEntity details;

    @Column(name = "date_created", nullable = false)
    private LocalDate dateCreated;

    @Column(name = "date_modified")
    private LocalDate dateModified;
    public CustomerEntity(String name, String lastName, Integer dni, LocalDate created, LocalDate modified) {
        this.firstName = name;
        this.lastName = lastName;
        this.documentNumber = dni;
        this.dateCreated = created;
        this.dateModified = modified;
    }
    public CustomerEntity(String name, String lastName, Integer dni) {
        this.firstName = name;
        this.lastName = lastName;
        this.documentNumber = dni;
        //this.address = address;
    }

}
