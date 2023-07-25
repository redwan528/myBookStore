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

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        double total = cartService.calculateTotal(user.getId());
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

    //@RequestMapping(value = "/cart", method = RequestMethod.GET)
//    public String getCart(Model model) {
//        Cart cart = cartService.getCart();  // Assuming getCart() returns a Cart object with a List of Books
//        model.addAttribute("cart", cart);
//
//        double totalPrice = 0;
//        for (Book book : cart.getBooks()) {
//            totalPrice += book.getPrice();
//        }
//        model.addAttribute("totalPrice", totalPrice);
//
//        return "cart";  // Return the name of your Thymeleaf template (assumes "cart.html")
//    }





    // Method to remove a book from the cart
//    @PostMapping("/cart/remove")
//    public ResponseEntity<?> removeFromCart(@RequestParam Long bookId, HttpSession session) {
//        User currentUser = (User) session.getAttribute("currentUser");
//        Book book = bookService.getBookById(bookId); // Get the book to remove from the cart
//        cartService.removeBookFromCart(book, currentUser); // Remove the book from the cart (you may need to implement this method in CartService)
//        return new ResponseEntity<>("Book removed from cart!", HttpStatus.OK);
//    }
    @PostMapping("/cart/remove/{bookId}")
    public ResponseEntity<?> removeFromCart(@RequestParam Long bookId, Principal principal) {
        User currentUser = userService.getUserByPrincipal(principal);
        Book book = bookService.getBookById(bookId);
        cartService.removeBookFromCart(book, currentUser);
        return new ResponseEntity<>("Book removed from cart!", HttpStatus.OK);
    }


    // Add more methods for updating the cart, handling checkout, and other cart-related requests as needed
}
