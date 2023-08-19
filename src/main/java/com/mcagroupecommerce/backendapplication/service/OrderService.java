package com.mcagroupecommerce.backendapplication.service;

import com.mcagroupecommerce.backendapplication.exception.OrderException;
import com.mcagroupecommerce.backendapplication.model.Address;
import com.mcagroupecommerce.backendapplication.model.Order;
import com.mcagroupecommerce.backendapplication.model.User;

import java.util.List;

public interface OrderService {
    public Order createOrder(User user, Address shippingAdress);

    public Order findOrderById(Long orderId) throws OrderException;

    public List<Order> usersOrderHistory(Long userId);

    public Order placedOrder(Long orderId) throws OrderException;

    public Order confirmedOrder(Long orderId)throws OrderException;

    public Order shippedOrder(Long orderId) throws OrderException;

    public Order deliveredOrder(Long orderId) throws OrderException;

    public Order cancledOrder(Long orderId) throws OrderException;

    public List<Order>getAllOrders();

    public void deleteOrder(Long orderId) throws OrderException;

}
