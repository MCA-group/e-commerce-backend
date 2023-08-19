package com.mcagroupecommerce.backendapplication.repository;

import com.mcagroupecommerce.backendapplication.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
