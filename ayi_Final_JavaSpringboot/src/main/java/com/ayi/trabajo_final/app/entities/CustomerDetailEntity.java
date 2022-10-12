package com.ayi.trabajo_final.app.entities;
/**
 * Customer Detail  Entity
 * @Table (name = "customers_details")
 * Properties:
 * @id Long;
 * @vip Boolean;
 * @totalPoints Long;
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
@Table(name = "customer_details")
public class CustomerDetailEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="isVip")
    private Boolean vip;
    @Column(name="total_points")
    private Long totalPoints;

    @OneToOne
    @JoinColumn(name="customer_detail_id")
    private CustomerEntity customer;

    public CustomerDetailEntity(Boolean vip, Long totalPoints) {
        this.vip = vip;
        this.totalPoints = totalPoints;
    }

}
