package com.capstone.onlineBookStore.serviceTests;
import com.capstone.onlineBookStore.model.Checkout;
import com.capstone.onlineBookStore.repository.CheckoutRepository;
import com.capstone.onlineBookStore.service.CheckoutServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CheckoutServiceImplTest {

    private CheckoutServiceImpl checkoutService;

    @Mock
    private CheckoutRepository checkoutRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        checkoutService = new CheckoutServiceImpl(checkoutRepository);
    }

    @Test
    public void testCreateOrder() {
        Checkout checkout = new Checkout();
        checkout.setId(1L);
        checkout.setOrderTotal(new BigDecimal("100.00"));

        when(checkoutRepository.save(any(Checkout.class))).thenReturn(checkout);

        Checkout savedCheckout = checkoutService.createOrder(checkout);

        assertEquals(savedCheckout.getOrderTotal(), checkout.getOrderTotal());

        verify(checkoutRepository).save(any(Checkout.class));
    }
}
