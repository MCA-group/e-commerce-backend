package com.mcagroupecommerce.backendapplication.controller;

import com.mcagroupecommerce.backendapplication.exception.ProductException;
import com.mcagroupecommerce.backendapplication.exception.UserException;
import com.mcagroupecommerce.backendapplication.model.Cart;
import com.mcagroupecommerce.backendapplication.model.User;
import com.mcagroupecommerce.backendapplication.request.AddItemRequest;
import com.mcagroupecommerce.backendapplication.responses.ApiResponse;
import com.mcagroupecommerce.backendapplication.service.CartService;
import com.mcagroupecommerce.backendapplication.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private CartService cartService;
    private UserService userService;

    public CartController(CartService cartService,UserService userService) {
        this.cartService=cartService;
        this.userService=userService;
    }

    @GetMapping("/")
    public ResponseEntity<Cart> findUserCartHandler(@RequestHeader("Authorization") String jwt) throws UserException {

        User user=userService.findUserProfileByJwt(jwt);

        Cart cart=cartService.findUserCart(user.getId());

        System.out.println("cart - "+cart.getUser().getEmail());

        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddItemRequest req, @RequestHeader("Authorization") String jwt) throws UserException, ProductException {

        User user=userService.findUserProfileByJwt(jwt);

        cartService.addCartItem(user.getId(), req);

        ApiResponse res= new ApiResponse("Item Added To Cart Successfully",true);

        return new ResponseEntity<ApiResponse>(res,HttpStatus.ACCEPTED);

    }
}
