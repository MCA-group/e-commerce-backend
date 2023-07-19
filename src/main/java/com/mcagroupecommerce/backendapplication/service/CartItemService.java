package com.mcagroupecommerce.backendapplication.service;

import com.mcagroupecommerce.backendapplication.exception.CartItemException;
import com.mcagroupecommerce.backendapplication.exception.UserException;
import com.mcagroupecommerce.backendapplication.model.Cart;
import com.mcagroupecommerce.backendapplication.model.CartItem;
import com.mcagroupecommerce.backendapplication.model.Product;

public interface CartItemService {

    public CartItem createCartItem(CartItem cartItem);

    public CartItem updateCartItem(Long userId, Long id,CartItem cartItem) throws CartItemException, UserException;

    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);

    public void removeCartItem(Long userId,Long cartItemId) throws CartItemException, UserException;

    public CartItem findCartItemById(Long cartItemId) throws CartItemException;

}
