package org.example.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "direcciones")
@Getter
@Setter
@ToString


public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "calle")
    private String calle;
    @Column (name = "numero")
    private Integer numero;

    public Direccion() {
    }

    public Direccion(String calle, Integer numero) {
        this.calle = calle;
        this.numero = numero;
    }



}
