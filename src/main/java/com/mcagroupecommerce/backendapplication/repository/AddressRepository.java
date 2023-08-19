package com.mcagroupecommerce.backendapplication.repository;

import com.mcagroupecommerce.backendapplication.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
