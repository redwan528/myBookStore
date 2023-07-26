package com.capstone.onlineBookStore.controller;

import com.capstone.onlineBookStore.model.Book;
import com.capstone.onlineBookStore.model.Cart;
import com.capstone.onlineBookStore.model.User;
import com.capstone.onlineBookStore.repository.BookRepository;
import com.capstone.onlineBookStore.repository.CartRepository;
import com.capstone.onlineBookStore.service.BookService;
import com.capstone.onlineBookStore.service.CartService;
import com.capstone.onlineBookStore.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;



import java.security.Principal;
import java.util.*;

@Controller
public class CartController {
    private HttpSession httpSession;

    private final CartService cartService;
    private final BookService bookService;

    private final UserService userService;

    private final CartRepository cartRepository;
    private final BookRepository bookRepository;

    @Autowired
    public CartController(CartService cartService, BookService bookService, UserService userService, CartRepository cartRepository, BookRepository bookRepository) {
        this.cartService = cartService;
        this.bookService = bookService;
        this.userService = userService;
        this.cartRepository = cartRepository;
        this.bookRepository = bookRepository;

    }


    @GetMapping("/cart")
    public String viewCart(Model model, Principal principal) {
        System.out.println("IN CartController->viewCart()");

        // Fetch the current user
        User user = userService.getUserByPrincipal(principal);

        // Find the user's cart
        Cart cart = cartService.findCartByUserId(user.getId());

        // If cart is null create a new Cart
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart = cartRepository.save(cart);
        }

        //double total = cartService.calculateTotal(user.getId());
        BigDecimal total = cartService.calculateCartPrice(user.getId());

        // Add the cart to the model
        model.addAttribute("cart", cart);
        model.addAttribute("totalPrice",total);

        return "cart";
    }

@PostMapping("cart/add-book/{bookId}")
public ResponseEntity<Void> addBookToCart(@PathVariable Long bookId, Principal principal) {
    User user = userService.getUserByPrincipal(principal);
    Book book = bookService.getBookById(bookId);
    if (user != null && book != null) {
        cartService.addBookToCart(book, user);
        return new ResponseEntity<>(HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

    @PostMapping("/cart/remove/{bookId}")
    public ResponseEntity<Map<String, Object>> removeFromCart(@PathVariable Long bookId, Principal principal) {
        // ... Your existing code ...
        User currentUser = userService.getUserByPrincipal(principal);
        Book book = bookService.getBookById(bookId);
        // Call the cartService to remove the book from the user's cart.
        cartService.removeBookFromCart(book, currentUser);

        // Recalculate the total price after removing the book
        BigDecimal total = cartService.calculateCartPrice(currentUser.getId());

        // Prepare the response data with the updated total price
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("message", "Book removed from cart successfully.");
        responseData.put("totalPrice", total);

        return ResponseEntity.ok(responseData);
    }


}
