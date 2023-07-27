
package com.capstone.onlineBookStore.controller;

import com.capstone.onlineBookStore.model.Cart;
import com.capstone.onlineBookStore.model.User;
import com.capstone.onlineBookStore.service.CartService;
import com.capstone.onlineBookStore.service.CheckoutService;
import com.capstone.onlineBookStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CheckoutController {

    private final UserService userService;
    private final CartService cartService;
    private final CheckoutService checkoutService;

    @Autowired
    public CheckoutController(UserService userService, CartService cartService, CheckoutService checkoutService) {
        this.userService = userService;
        this.cartService = cartService;
        this.checkoutService = checkoutService;
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
        return "checkout_success"; // Return the Thymeleaf template for the checkout success page
    }
}
