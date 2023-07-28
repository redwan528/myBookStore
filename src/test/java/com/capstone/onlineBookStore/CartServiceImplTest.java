package com.capstone.onlineBookStore;


import com.capstone.onlineBookStore.model.Book;
import com.capstone.onlineBookStore.model.Cart;
import com.capstone.onlineBookStore.model.User;
import com.capstone.onlineBookStore.repository.BookRepository;
import com.capstone.onlineBookStore.repository.CartRepository;
import com.capstone.onlineBookStore.repository.UserRepository;
import com.capstone.onlineBookStore.service.CartServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CartServiceImplTest {

    @InjectMocks
    private CartServiceImpl cartService;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void testAddBookToCart() {
        // given
        User user = new User();
        user.setId(1L);
        user.setName("Test User");
        user.setEmail("test.user@test.com");
        user.setPassword("test1234");

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");

        Cart cart = new Cart();
        cart.setUser(user);
        cart.getBooks().add(book);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(cartRepository.findByUser(user)).thenReturn(cart);
        when(bookRepository.save(book)).thenReturn(book);

        // when
        cartService.addBookToCart(book, user);

        // then
        verify(cartRepository, times(1)).save(any(Cart.class));
    }
}
