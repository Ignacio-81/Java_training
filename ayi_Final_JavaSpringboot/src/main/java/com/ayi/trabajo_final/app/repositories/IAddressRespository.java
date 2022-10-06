package com.ayi.trabajo_final.app.repositories;

import com.ayi.trabajo_final.app.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRespository extends JpaRepository<AddressEntity, Long> {
}
