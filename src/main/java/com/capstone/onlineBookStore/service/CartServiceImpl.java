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

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


@Service
public class CartServiceImpl implements CartService {

//private Cart cart;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
//    private final PlatformTransactionManager transactionManager;


    @Autowired
    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository, BookRepository bookRepository)/*PlatformTransactionManager transactionManager*/ {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
//        this.transactionManager = transactionManager; // Assign the injected PlatformTransactionManager

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
    if (cart == null) {

        cart = new Cart();
        cart.setUser(user);
    }
    if (cart.getBooks() == null) {
        cart.setBooks(new ArrayList<>());
    }
    cart.getBooks().add(book);
    cartRepository.save(cart);
}



    @Override
    public void removeBookFromCart(Book book, User user) {
        Cart cart = findCartByUserId(user.getId());
        cart.getBooks().remove(book);
        cartRepository.save(cart);
    }


    @Override
    public void removeAllBooksFromCart(User user) {
        Cart cart = findCartByUserId(user.getId());
        if (cart != null) {
            cart.getBooks().clear();
            cartRepository.save(cart);
        }
    }
@Override
public double calculateTotal(Long userId) {

    Cart cart = findCartByUserId(userId);
    double total = 0.0;
    if (cart != null && cart.getBooks() != null) {
        for (Book book : cart.getBooks()) {
            total += book.getPrice();
        }
    }
    DecimalFormat df = new DecimalFormat("#.##");
    total = Double.valueOf(df.format(total));
    return total;
}

    @Override
    public BigDecimal calculateCartPrice(Long userId) {
        Cart cart = findCartByUserId(userId);
        List<Book> booksInCart = cart.getBooks();
        BigDecimal cartPrice = BigDecimal.ZERO;

        // Loop through the books in the cart and sum up their prices
        for (Book book : booksInCart) {
            cartPrice = cartPrice.add(BigDecimal.valueOf(book.getPrice()));
        }

        return cartPrice;
    }

    @Override
    public double calculateTotalPrice(User user) {
        Cart cart = findCartByUserId(user.getId());
        List<Book> books = cart.getBooks();
        double totalPrice = 0.0;
        for (Book book : books) {
            totalPrice += book.getPrice();
        }
        return totalPrice;
    }

    @Override
    public List<Book> getAllBooksInCart(Long userId) {
        Cart cart = findCartByUserId(userId);
        if (cart != null) {
            return cart.getBooks();
        }
        return new ArrayList<>(); // Return an empty list if the cart is null or has no books
    }

    @Override
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void deleteCartById(Long cartId) {
        cartRepository.deleteById(cartId);
    }




}
