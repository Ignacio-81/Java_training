package org.example.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name= "facturas")
@Getter
@Setter

public class Factura {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "descripcion")
    private String descripcion;
    @Column (name = "total")
    private Integer total;

    public Factura() {
    }

    public Factura(String descripcion, Integer total) {
        this.descripcion = descripcion;
        this.total = total;
    }

    @ManyToOne
    @JoinColumn (name="id_cliente")
    private Cliente cliente;

    @Override
    public String toString() {
        return "Factura{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", total=" + total +
                '}';
    }
}
