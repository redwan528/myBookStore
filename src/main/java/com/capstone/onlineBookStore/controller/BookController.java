package com.capstone.onlineBookStore.controller;
import com.capstone.onlineBookStore.exception.BookNotFoundException;
import com.capstone.onlineBookStore.model.Book;
import com.capstone.onlineBookStore.service.BookService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    private HttpSession httpSession;
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all-books")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "bookList"; // bookList.html is the Thymeleaf template to display the list of books
    }

    @GetMapping("/book-{id}")
    public String getBookById(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "bookDetails"; // bookDetails.html is the Thymeleaf template to display book details
    }

//    @GetMapping("/")
//    public String showHomePage() {
//        return "homepage"; // Return the name of the Thymeleaf template for the homepage
//    }

    @GetMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable Long id) {
        Book book = bookService.getBookById(id);

        // Fetch the user's cart items from the session
        List<Book> cartItems = (List<Book>) httpSession.getAttribute("cartItems");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        // Add the book to the cart
        cartItems.add(book);
        // Update the cart items in the session
        httpSession.setAttribute("cartItems", cartItems);
        return "redirect:/cart"; // Redirect back to the all-books page after adding to cart
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleBookNotFound(BookNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }



    // Add more methods for handling book-related requests (e.g., add, update, delete)
}
