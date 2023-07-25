package com.capstone.onlineBookStore.controller;

import com.capstone.onlineBookStore.model.Book;
import com.capstone.onlineBookStore.model.Cart;
import com.capstone.onlineBookStore.model.User;
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

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    private HttpSession httpSession;

    private final CartService cartService;
    private final BookService bookService;

    private final UserService userService;

    @Autowired
    public CartController(CartService cartService, BookService bookService, UserService userService) {
        this.cartService = cartService;
        this.bookService = bookService;
        this.userService = userService;
    }



    @GetMapping("/cart")
    public String viewCart(Model model, HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);
        if (httpSession != null) {
            Cart cart = (Cart) httpSession.getAttribute("cart");
            model.addAttribute("cart", cart);
        }
        return "cart"; // Return the name of the Thymeleaf template for displaying the cart
    }

    @PostMapping("/add/{bookId}")
    public ResponseEntity<Void> addBookToCart(@PathVariable Long userId, @PathVariable Long bookId) {
        User user = userService.getUserById(userId);
        Book book = bookService.getBookById(bookId);
        if (user != null && book != null) {
            cartService.addBookToCart(book, user);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // Method to remove a book from the cart
    @PostMapping("/cart/remove")
    public ResponseEntity<?> removeFromCart(@RequestParam Long bookId, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        Book book = bookService.getBookById(bookId); // Get the book to remove from the cart
        cartService.removeBookFromCart(book, currentUser); // Remove the book from the cart (you may need to implement this method in CartService)
        return new ResponseEntity<>("Book removed from cart!", HttpStatus.OK);
    }

    // Add more methods for updating the cart, handling checkout, and other cart-related requests as needed
}
