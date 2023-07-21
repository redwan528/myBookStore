package com.capstone.onlineBookStore.service;

import com.capstone.onlineBookStore.model.Book;
import com.capstone.onlineBookStore.model.Cart;
import com.capstone.onlineBookStore.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private String currentUserId; // User identifier (e.g., username or user ID)

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
    }

    @Override
    public Cart getUserCart() {
        return cartRepository.findByUserUsername(currentUserId);
    }

    @Override
    public void addBookToCart(Book book) {
        Cart cart = getUserCart();
        cart.getBooks().add(book);
        cartRepository.save(cart);
    }

    @Override
    public void removeBookFromCart(Book book) {
        Cart cart = getUserCart();
        cart.getBooks().remove(book);
        cartRepository.save(cart);
    }
}
