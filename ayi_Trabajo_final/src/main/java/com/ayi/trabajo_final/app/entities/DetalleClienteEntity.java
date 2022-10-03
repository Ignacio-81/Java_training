package com.ayi.trabajo_final.app.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "detalle_clientes")
public class DetalleClienteEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean prime;

    @Column(name="puntos_acumulados")
    private Long puntosAcumulados;

    @OneToOne
    @JoinColumn(name="cliente_detalle_id")
    private ClienteEntity cliente;

    public DetalleClienteEntity(boolean prime, Long puntosAcumulados) {
        this.prime = prime;
        this.puntosAcumulados = puntosAcumulados;
    }

}
