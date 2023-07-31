package com.capstone.onlineBookStore.serviceTests;
import com.capstone.onlineBookStore.model.Book;
import com.capstone.onlineBookStore.model.Cart;
import com.capstone.onlineBookStore.model.Checkout;
import com.capstone.onlineBookStore.model.User;
import com.capstone.onlineBookStore.repository.BookRepository;
import com.capstone.onlineBookStore.repository.CartRepository;
import com.capstone.onlineBookStore.repository.CheckoutRepository;
import com.capstone.onlineBookStore.repository.UserRepository;
import com.capstone.onlineBookStore.service.CartService;
import com.capstone.onlineBookStore.service.CartServiceImpl;
import com.capstone.onlineBookStore.service.CheckoutServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


public class CartServiceImplTest {

    @InjectMocks
    private CheckoutServiceImpl checkoutService;

    @InjectMocks
    private CartServiceImpl cartService;

    @Mock
    private CheckoutRepository checkoutRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private BookRepository bookRepository;

    // Initialize User and Cart here
    private User user;
    private Cart cart;

    private Checkout checkout;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Set up User and Cart in the setUp method
        user = new User();
        user.setId(1L);

        cart = new Cart();
        cart.setId(1L);
        cart.setUser(user);
        cart.setBooks(new ArrayList<>());
    }

    @Test
    void testCreateOrder() {
        // Arrange
        Checkout checkout = new Checkout();
        // Set necessary properties
        checkout.setId(1L);
        checkout.setOrderTotal(new BigDecimal("100.00"));
        checkout.setCardNumber("1234567812345678");
        checkout.setShippingAddress("123 Street");
        checkout.setZipCode("12345");
        checkout.setCreatedAt(LocalDateTime.now());

        when(checkoutRepository.save(checkout)).thenReturn(checkout);

        // Act
        Checkout savedCheckout = checkoutService.createOrder(checkout);

        // Assert
        assertEquals(1L, savedCheckout.getId());
        assertEquals(new BigDecimal("100.00"), savedCheckout.getOrderTotal());
        assertEquals("1234567812345678", savedCheckout.getCardNumber());
        assertEquals("123 Street", savedCheckout.getShippingAddress());
        assertEquals("12345", savedCheckout.getZipCode());
    }



    @Test
    void testFindCartByUserId() {
        User user = new User();
        user.setId(1L);

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setUser(user);
        cart.setBooks(new ArrayList<>());

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(cartRepository.findByUser(user)).thenReturn(cart);

        Cart result = cartService.findCartByUserId(1L);

        assertEquals(cart, result);
    }

    @Test
    void testRemoveBookFromCart() {
        User user = new User();
        user.setId(1L);

        Book book = new Book();
        book.setId(1L);

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setUser(user);
        cart.setBooks(new ArrayList<>());

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(cartRepository.findByUser(user)).thenReturn(cart);
        when(cartRepository.save(cart)).thenReturn(cart);

        cartService.removeBookFromCart(book, user);

        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void testRemoveAllBooksFromCart() {
        User user = new User();
        user.setId(1L);

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setUser(user);
        cart.setBooks(new ArrayList<>());

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(cartRepository.findByUser(user)).thenReturn(cart);
        when(cartRepository.save(cart)).thenReturn(cart);

        cartService.removeAllBooksFromCart(user);

        verify(cartRepository, times(1)).save(cart);
    }

@Test
void testCalculateTotal() {
    Book book1 = new Book();
    book1.setPrice(10.00);
    Book book2 = new Book();
    book2.setPrice(20.00);
    List<Book> books = Arrays.asList(book1, book2);

    User user = new User();  // assuming you have a default constructor
    Cart cart = new Cart();  // assuming you have a default constructor
    cart.setBooks(books);    // assuming you have a setter for books

    UserRepository userRepository = mock(UserRepository.class);
    CartRepository cartRepository = mock(CartRepository.class);

    when(userRepository.findById(1L)).thenReturn(Optional.of(user));
    when(cartRepository.findByUser(user)).thenReturn(cart);

    CartService cartService = new CartServiceImpl( cartRepository,  userRepository,  bookRepository);

    double total = cartService.calculateTotal(1L);

    assertEquals(30.00, total);
}

@Test
void testCalculateCartPrice() {
    Book book1 = new Book();
    book1.setPrice(10.00);
    Book book2 = new Book();
    book2.setPrice(20.00);
    List<Book> books = Arrays.asList(book1, book2);

    Cart cart = mock(Cart.class);
    when(cart.getBooks()).thenReturn(books);

    when(userRepository.findById(1L)).thenReturn(Optional.of(user));
    when(cartRepository.findByUser(user)).thenReturn(cart);

    BigDecimal price = cartService.calculateCartPrice(1L);
    price = price.setScale(2, RoundingMode.HALF_UP);  // Set the scale

    assertEquals(new BigDecimal("30.00"), price);
}

@Test
void testGetAllBooksInCart() {
    Book book1 = new Book();
    book1.setPrice(10.00);
    Book book2 = new Book();
    book2.setPrice(20.00);
    List<Book> books = Arrays.asList(book1, book2);

    Cart cart = new Cart();
    User user = new User();
    cart.setBooks(books);

    when(userRepository.findById(1L)).thenReturn(Optional.of(user));
    when(cartRepository.findByUser(user)).thenReturn(cart);

    List<Book> result = cartService.getAllBooksInCart(1L);

    assertEquals(2, result.size());
    assertTrue(result.containsAll(books));
}
    @Test
    void testSaveCart() {
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        Cart result = cartService.saveCart(cart);

        assertEquals(cart, result);
        verify(cartRepository, times(1)).save(cart);
    }
}


