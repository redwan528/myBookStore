package com.capstone.onlineBookStore.service;

import com.capstone.onlineBookStore.model.Book;
import com.capstone.onlineBookStore.model.Cart;
import com.capstone.onlineBookStore.model.User;

public interface CartService {
//    Cart getUserCart();
    Cart findCartByUserId(Long currentUserId);

    void addBookToCart(Long bookId, User user);
    void removeBookFromCart(Book book, User user);
    // Add more methods for cart-related operations if needed
}
