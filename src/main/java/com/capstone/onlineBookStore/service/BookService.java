package com.capstone.onlineBookStore.service;

import com.capstone.onlineBookStore.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    Book getBookById(Long id);

    Book saveBook(Book book);

    void deleteBook(Long id);
}
