package com.capstone.onlineBookStore;

import com.capstone.onlineBookStore.model.Book;
import com.capstone.onlineBookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BookService bookService;

    @Autowired
    public DataInitializer(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Add some sample books to the database
        Book book1 = new Book("Title 1", "Author 1", 19.99);
        Book book2 = new Book("Title 2", "Author 2", 24.99);
        Book book3 = new Book("Title 3", "Author 3", 14.99);

        bookService.saveBook(book1);
        bookService.saveBook(book2);
        bookService.saveBook(book3);
    }
}
