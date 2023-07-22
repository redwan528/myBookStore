package com.capstone.onlineBookStore.controller;

import com.capstone.onlineBookStore.model.Book;
import com.capstone.onlineBookStore.model.Cart;
import com.capstone.onlineBookStore.service.BookService;
import com.capstone.onlineBookStore.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public CartController(CartService cartService, BookService bookService) {
        this.cartService = cartService;
        this.bookService = bookService;
    }

    // Method to display the user's cart
//    @GetMapping("/cart")
//    public String viewCart(Model model) {
//        Cart cart = cartService.getUserCart(); // Get the user's cart (you may need to implement this method in CartService)
//        model.addAttribute("cart", cart);
//        return "cart"; // Return the name of the Thymeleaf template for displaying the cart
//    }
//    @GetMapping("/cart")
//    public String viewCart(Model model) {
//        // Fetch the user's cart items from the session
//        List<Book> cartItems = (List<Book>) httpSession.getAttribute("cartItems");
//        if (cartItems == null) {
//            cartItems = new ArrayList<>();
//        }
//
//        model.addAttribute("cartItems", cartItems);
//        return "cart"; // cart.html is the Thymeleaf template to display the cart items
//    }

    @GetMapping("/cart")
    public String viewCart(Model model, HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);
        if (httpSession != null) {
            Cart cart = (Cart) httpSession.getAttribute("cart");
            model.addAttribute("cart", cart);
        }
        return "cart"; // Return the name of the Thymeleaf template for displaying the cart
    }

    // Method to add a book to the cart
    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long bookId) {
        Book book = bookService.getBookById(bookId); // Get the book to add to the cart
        cartService.addBookToCart(book); // Add the book to the cart (you may need to implement this method in CartService)
        return "redirect:/cart"; // Redirect back to the cart page after adding the book
    }

    // Method to remove a book from the cart
    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam Long bookId) {
        Book book = bookService.getBookById(bookId); // Get the book to remove from the cart
        cartService.removeBookFromCart(book); // Remove the book from the cart (you may need to implement this method in CartService)
        return "redirect:/cart"; // Redirect back to the cart page after removing the book
    }

    // Add more methods for updating the cart, handling checkout, and other cart-related requests as needed
}
