package com.ayi.trabajo_final.app.repositories;

import com.ayi.trabajo_final.app.entities.CustomerDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerDetailRepository extends JpaRepository<CustomerDetailEntity, Long> {
}
