package com.capstone.onlineBookStore.service;

import com.capstone.onlineBookStore.model.Book;
import com.capstone.onlineBookStore.model.Cart;
import com.capstone.onlineBookStore.model.User;
import com.capstone.onlineBookStore.repository.CartRepository;
import com.capstone.onlineBookStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

//    private final CartRepository cartRepository;
//    private String currentUserId; // User identifier (e.g., username or user ID)
//
//    @Autowired
//    public CartServiceImpl(CartRepository cartRepository) {
//        this.cartRepository = cartRepository;
//    }
//
//    public void setCurrentUserId(String currentUserId) {
//        this.currentUserId = currentUserId;
//    }
//
//    @Override
//    public Cart getUserCart() {
//        return cartRepository.findByUser(currentUserId);
//    }

    private final CartRepository cartRepository;
    private final UserRepository userRepository; // Add UserRepository

    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Cart findCartByUserId(Long currentUserId) {
        User user = userRepository.findById(currentUserId).orElse(null);
        if (user != null) {
            return cartRepository.findByUser(user);
        }
        return null; // Or handle the case when the user is not found.
    }

    @Override
    public void addBookToCart(Book book) {
//        Cart cart = cartRepository.findByUser(userRepository.findById(user));
//        cart.getBooks().add(book);
//        cartRepository.save(cart);
    }

    @Override
    public void removeBookFromCart(Book book) {
//        Cart cart = getUserCart();
//        cart.getBooks().remove(book);
//        cartRepository.save(cart);
    }
}
