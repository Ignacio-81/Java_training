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
@Table(name = "customer_details")
public class CustomerDetailEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean prime;

    @Column(name="total_points")
    private Long totalPoints;

    @OneToOne
    @JoinColumn(name="customer_detail_id")
    private CustomerEntity customer;

    public CustomerDetailEntity(boolean prime, Long totalPoints) {
        this.prime = prime;
        this.totalPoints = totalPoints;
    }

}
