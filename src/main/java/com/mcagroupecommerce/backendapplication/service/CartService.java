package com.mcagroupecommerce.backendapplication.service;

import com.mcagroupecommerce.backendapplication.exception.ProductException;
import com.mcagroupecommerce.backendapplication.model.Cart;
import com.mcagroupecommerce.backendapplication.model.User;
import com.mcagroupecommerce.backendapplication.request.AddItemRequest;

public interface CartService {
    public Cart createCart(User user);

    public String addCartItem(Long userId, AddItemRequest req) throws ProductException;

    public Cart findUserCart(Long userId);
}
