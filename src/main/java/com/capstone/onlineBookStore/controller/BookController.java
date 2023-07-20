package com.capstone.onlineBookStore.controller;
import com.capstone.onlineBookStore.model.Book;
import com.capstone.onlineBookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/books")
public class BookController {

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

    @GetMapping("/")
    public String showHomePage() {
        return "homepage"; // Return the name of the Thymeleaf template for the homepage
    }

    // Add more methods for handling book-related requests (e.g., add, update, delete)

}
