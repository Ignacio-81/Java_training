package com.ayi.trabajo_final.app.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "address")
public class AddressEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "street")
    private String street;
    @Column (name = "number")
    private Integer number;
    @Column(name = "floor")
    private String floor;
    @Column (name = "floorNumber")
    private Integer floorNumber;
    @Column(name = "postalCode")
    private String postalCode;
    @Column (name = "state")
    private String state;
    @Column(name = "city")
    private String city;
    @Column (name = "country")
    private String country;

    @ManyToOne
    @JoinColumn (name="id_customer")
    private CustomerEntity customer;

}
