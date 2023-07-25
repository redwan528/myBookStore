package com.capstone.onlineBookStore.service;

import com.capstone.onlineBookStore.model.Book;
import com.capstone.onlineBookStore.model.Cart;
import com.capstone.onlineBookStore.model.User;
import com.capstone.onlineBookStore.repository.BookRepository;
import com.capstone.onlineBookStore.repository.CartRepository;
import com.capstone.onlineBookStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {


    private final CartRepository cartRepository;
    private final UserRepository userRepository; // Add UserRepository

    private final BookRepository bookRepository;
    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
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
    public void addBookToCart(Long bookId, User user) {
        Cart cart = findCartByUserId(user.getId());
//        cart.getBooks().add(book);
        bookRepository.findById(bookId);
        cartRepository.save(cart);
    }

    @Override
    public void removeBookFromCart(Book book, User user) {
        Cart cart = findCartByUserId(user.getId());
        cart.getBooks().remove(book);
        cartRepository.save(cart);
    }
}
