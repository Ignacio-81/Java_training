package org.example.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table (name = "clientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Cliente {

    //Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column (name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column (name = "forma_pago", nullable = false, length = 15)
    private String formaPago;

/*    @Embedded
    private Auditoria audit = new Auditoria();*/


    @OneToMany (cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable (name = "tbl_clientes_direcciones", joinColumns = @JoinColumn(name="id_cliente"),
                inverseJoinColumns = @JoinColumn(name = "id_direccion")
            , uniqueConstraints = @UniqueConstraint(columnNames={"id_direccion"}))

    private List<Direccion> direcciones;

    public Cliente(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }
/*    @Column (name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;*/


/*
//Al minimo tenemos que tener un constructor Vacio.
    public Cliente() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", formaPago='" + formaPago + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }

 */
}
