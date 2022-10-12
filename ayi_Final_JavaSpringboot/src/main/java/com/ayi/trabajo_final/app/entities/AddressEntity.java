package com.ayi.trabajo_final.app.entities;
/**
 * Address Entity
 * @Table (name = "Address")
 * Properties:
 * @id Long;
 * @streetName String;
 * @streetNumber Integer;
 * @floor Integer;
 * @apartmentLetter String;
 * @postalCode String;
 * @state String;
 * @city String;
 * @country String;
 *

 */

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "address")
public class AddressEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "streetName")
    private String streetName;
    @Column (name = "streetNumber")
    private Integer streetNumber;
    @Column(name = "floor")
    private Integer floor;
    @Column (name = "apartmentLetter")
    private String apartmentLetter;
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
