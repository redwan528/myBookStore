package com.capstone.onlineBookStore;

import com.capstone.onlineBookStore.model.Book;
import com.capstone.onlineBookStore.service.BookService;
import com.github.javafaker.Faker;
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
        Faker faker = new Faker();

        // generate 100 random books
        for(int i = 0; i < 100; i++) {
            String title = faker.book().title();
            String author = faker.book().author();
            double price = faker.number().randomDouble(2, 10, 100);
            Book book = new Book(title, author, price);
            bookService.saveBook(book);
        }

//        Book book1 = new Book("Harry Potter and the Half Blood Prince", "JK Rowling", 19.99);
//        Book book2 = new Book("Man's Search for Meaning", "Viktor Frankl", 24.99);
//        Book book3 = new Book("Laws of Power", "Robert Greene", 14.99);

//        bookService.saveBook(book1);
//        bookService.saveBook(book2);
//        bookService.saveBook(book3);
    }
}
