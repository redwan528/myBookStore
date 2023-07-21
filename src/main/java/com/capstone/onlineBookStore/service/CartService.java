package com.capstone.onlineBookStore.service;

import com.capstone.onlineBookStore.model.Book;
import com.capstone.onlineBookStore.model.Cart;

public interface CartService {
    Cart getUserCart();
    void addBookToCart(Book book);
    void removeBookFromCart(Book book);
    // Add more methods for cart-related operations if needed
}
