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
@Table(name = "direcciones")
public class DireccionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "calle")
    private String calle;
    @Column (name = "numero")
    private Integer numero;
    @Column(name = "piso")
    private String piso;
    @Column (name = "numeroPiso")
    private Integer numeroPiso;
    @Column(name = "codigoPostal")
    private String codigoPostal;
    @Column (name = "localidad")
    private String localidad;
    @Column(name = "ciudad")
    private String ciudad;
    @Column (name = "pais")
    private String pais;

}
