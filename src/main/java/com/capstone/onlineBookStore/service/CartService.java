package com.capstone.onlineBookStore.service;

import com.capstone.onlineBookStore.model.Book;
import com.capstone.onlineBookStore.model.Cart;
import com.capstone.onlineBookStore.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {
//    Cart getUserCart();
    Cart findCartByUserId(Long currentUserId);

    void addBookToCart(Book book, User user);
    void removeBookFromCart(Book book, User user);
    // Add more methods for cart-related operations if needed

//    Cart getCart();
 void removeAllBooksFromCart(User user);
    double calculateTotal(Long currentUserId);

    BigDecimal calculateCartPrice(Long userId);

     double calculateTotalPrice(User user);

    List<Book> getAllBooksInCart(Long userId);

}
