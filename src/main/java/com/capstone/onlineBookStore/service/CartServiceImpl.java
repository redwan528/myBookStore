package com.capstone.onlineBookStore.service;

import com.capstone.onlineBookStore.model.Book;
import com.capstone.onlineBookStore.model.Cart;
import com.capstone.onlineBookStore.model.User;
import com.capstone.onlineBookStore.repository.BookRepository;
import com.capstone.onlineBookStore.repository.CartRepository;
import com.capstone.onlineBookStore.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
//import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;


@Service
public class CartServiceImpl implements CartService {


    private final CartRepository cartRepository;
    private final UserRepository userRepository; // Add UserRepository

    private final BookRepository bookRepository;
    private final PlatformTransactionManager transactionManager; // Inject PlatformTransactionManager


    @Autowired
    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository, BookRepository bookRepository, PlatformTransactionManager transactionManager) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.transactionManager = transactionManager; // Assign the injected PlatformTransactionManager

    }

    @Override
    public Cart findCartByUserId(Long currentUserId) {
        User user = userRepository.findById(currentUserId).orElse(null);
        if (user != null) {
            return cartRepository.findByUser(user);
        }
        return null; // Or handle the case when the user is not found.
    }

    @Transactional
    @Override
    public void addBookToCart(Book book, User user) {
        Cart cart = findCartByUserId(user.getId());
        cart.getBooks().add(book);
        cartRepository.save(cart);
    }

    @Override
    public void removeBookFromCart(Book book, User user) {
        Cart cart = findCartByUserId(user.getId());
        cart.getBooks().remove(book);
        cartRepository.save(cart);
    }
}
