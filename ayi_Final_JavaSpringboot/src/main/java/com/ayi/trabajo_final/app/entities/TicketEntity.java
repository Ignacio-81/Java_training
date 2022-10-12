package com.ayi.trabajo_final.app.entities;
/**
 * Ticket Entity
 * @Table (name = "tickets")
 * Properties:
 * @id Long;
 * @description String;
 * @total Double;
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
@Table(name= "tickets")
public class TicketEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;
    @Column (name = "total")
    private Double total;

    public TicketEntity(String description, Double total) {
        this.description = description;
        this.total = total;
    }

    @ManyToOne
    @JoinColumn (name="id_customer")
    private CustomerEntity customer;

}
