
package com.capstone.onlineBookStore.controller;

import com.capstone.onlineBookStore.dto.CheckoutForm;
import com.capstone.onlineBookStore.model.Cart;
//import com.capstone.onlineBookStore.model.Order;
import com.capstone.onlineBookStore.model.Checkout;
import com.capstone.onlineBookStore.model.User;
import com.capstone.onlineBookStore.service.CartService;
import com.capstone.onlineBookStore.service.CheckoutService;
//import com.capstone.onlineBookStore.service.OrderService;
import com.capstone.onlineBookStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CheckoutController {

    private final UserService userService;
    private final CartService cartService;
    private final CheckoutService checkoutService;

//    private final OrderService orderService;

    @Autowired
    public CheckoutController(UserService userService, CartService cartService,
                              CheckoutService checkoutService
    /*OrderService orderService*/) {
        this.userService = userService;
        this.cartService = cartService;
        this.checkoutService = checkoutService;
       // this.orderService = orderService;
    }

    @GetMapping("/checkout")
    public String showCheckout(Model model, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        Cart cart = cartService.findCartByUserId(user.getId());
        BigDecimal totalPrice = cartService.calculateCartPrice(user.getId());

        model.addAttribute("user", user);
        model.addAttribute("cart", cart);
        model.addAttribute("totalPrice", totalPrice);

        return "checkout"; // Return the Thymeleaf template for the checkout page
    }



    @PostMapping("/checkout")
    public String processCheckout(/* Add form data as parameters */) {
        // Process the checkout form data and create a new checkout instance
        // Save the checkout instance to the database
        // Perform any other necessary business logic for the checkout process

        // Redirect to a success page or confirmation page after checkout
        return "redirect:/checkout/success";
    }

    @GetMapping("/checkout/success")
    public String showCheckoutSuccess() {
        return "checkout_confirmed"; // Return the Thymeleaf template for the checkout success page
    }

//    @PostMapping("/checkout/confirm")
//    public String processCheckoutConfirm(@RequestParam String cardNumber,
//                                         @RequestParam String address,
//                                         @RequestParam String zipCode,
//                                         Principal principal) {
//        // Get the authenticated user
//        User user = userService.getUserByPrincipal(principal);
//
//        // Remove all books from the user's cart
//        cartService.removeAllBooksFromCart(user);
//
//        // Reset the cart by creating a new empty cart for the user
//        Cart newCart = new Cart();
//        newCart.setUser(user);
//        newCart.setBooks(new ArrayList<>());
//        cartService.saveCart(newCart);
//
//
//
//        return "redirect:/checkout/success";
//    }

    @PostMapping("/checkout/confirm")
    public String processCheckoutConfirm(@RequestParam String cardNumber,
                                         @RequestParam String address,
                                         @RequestParam String zipCode,
                                         Principal principal) {
        // Get the authenticated user
        User user = userService.getUserByPrincipal(principal);

        // Check if the user already has an existing cart
        Cart userCart = cartService.findCartByUserId(user.getId());

        if (userCart == null) {
            // If the user does not have an existing cart, create a new cart
            userCart = new Cart();
            userCart.setUser(user);

            // Save the new cart to the database
            cartService.saveCart(userCart);
        }

        // Continue with the rest of the checkout process
        // ...

        // Delete the cart after the checkout is complete
        cartService.deleteCartById(userCart.getId());

        return "redirect:/checkout/success";
    }

//    @PostMapping("/checkout/confirm")
//    public String handleCheckout(@ModelAttribute CheckoutForm form, Model model, Principal principal) {
//        // Process the checkout form
//        // ...
//
//        // Get the authenticated user
//        User user = userService.getUserByPrincipal(principal);
//
//        Cart userCart = cartService.findCartByUserId(user.getId());
//
//        // Calculate the total price
//        BigDecimal totalPrice = cartService.calculateCartPrice(user.getId());
//
//        // Create the Checkout entity and set the cart_id
//        Checkout checkout = new Checkout();
//        checkout.setUser(user);
//        checkout.setCart(userCart); // Set the cart associated with the checkout
//        checkout.setCreatedAt(LocalDateTime.now());
//        checkout.setOrderTotal(totalPrice);
//        checkout.setShippingAddress(form.getAddress());
//        checkout.setZipCode(form.getZipCode());
//
//        // Save the order to the database
//        checkout = checkoutService.createOrder(checkout);
//
//        // Remove all books from the user's cart
//        cartService.removeAllBooksFromCart(user);
//
//        Long checkoutId = checkout.getId();
//
//        // Reset the cart by creating a new empty cart for the user
//        Cart newCart = new Cart();
//        newCart.setUser(user);
//        newCart.setBooks(new ArrayList<>());
//        cartService.saveCart(newCart);
//
//        return "checkout_confirmed";
//    }



}
