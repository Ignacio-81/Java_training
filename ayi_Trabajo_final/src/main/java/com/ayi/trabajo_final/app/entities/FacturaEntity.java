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
@Table(name= "facturas")
public class FacturaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descripcion")
    private String descripcion;
    @Column (name = "total")
    private Integer total;

    public FacturaEntity(String descripcion, Integer total) {
        this.descripcion = descripcion;
        this.total = total;
    }

    @ManyToOne
    @JoinColumn (name="id_cliente")
    private ClienteEntity cliente;
}
