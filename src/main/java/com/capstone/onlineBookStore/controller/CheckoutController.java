
package com.capstone.onlineBookStore.controller;

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

    @PostMapping("/checkout/confirm")
    public String processCheckoutConfirm(@RequestParam String cardNumber,
                                         @RequestParam String address,
                                         @RequestParam String zipCode,
                                         Principal principal) {
        // Get the authenticated user
        User user = userService.getUserByPrincipal(principal);

        // Remove all books from the user's cart
        cartService.removeAllBooksFromCart(user);

        // Create the order and save it to the database
       // orderService.createOrder(user, cardNumber, address, zipCode);

        // Redirect to the checkout success page
        return "redirect:/checkout/success";
    }


//    @PostMapping("/checkout/success")
//    public String confirmOrder(@ModelAttribute("checkout") Checkout checkout, Model model) {
//        // Here, you would process the data submitted from the form and save it to the database.
//
//        // Assuming you have the total amount available in the cart, you can retrieve it
//// Recalculate the total price after removing the book
//        User currentUser = userService.getUserByPrincipal(principal);
//        BigDecimal total = cartService.calculateCartPrice(currentUser.getId());
//        // Create a new Checkout object and set its properties
//        Checkout checkout = new Checkout();
//        checkout.setUser(userService.findByEmail(currentUser.getEmail())); // Set the User associated with this checkout (you need to fetch the user based on their ID or email)
////        checkout.setBooks(...); // Set the list of books in the checkout
//        checkout.setOrderTotal(totalAmount); // Set the total amount from the cart
//        checkout.setCardNumber(checkout.getCardNumber()); // Set the card number from the form
//        checkout.setShippingAddress(checkout.getAddress()); // Set the shipping address from the form
//        checkout.setZipCode(checkout.getZipCode()); // Set the zip code from the form
//
//        // Save the Checkout object to the database (using your JPA repository)
//        checkoutRepository.save(checkout);
//
//        // Populate the model attributes with the data to be displayed on the confirmation page
//        model.addAttribute("totalAmount", totalAmount);
//        model.addAttribute("cardNumber", orderForm.getCardNumber());
//        model.addAttribute("address", orderForm.getAddress());
//        model.addAttribute("zipCode", orderForm.getZipCode());
//
//        // Return the name of the confirmation page template (HTML file)
//        return "order-confirmation";
//    }

}
