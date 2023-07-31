package com.capstone.onlineBookStore.service;

import com.capstone.onlineBookStore.model.Checkout;
import com.capstone.onlineBookStore.model.User;
import com.capstone.onlineBookStore.repository.CheckoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CheckoutRepository checkoutRepository;

    @Autowired
    public CheckoutServiceImpl(CheckoutRepository checkoutRepository) {
        this.checkoutRepository = checkoutRepository;
    }

    @Override
    public Checkout createOrder(Checkout checkout) {
        // Create a new Checkout entity and save it to the database
        Checkout newCheckout = new Checkout();
        newCheckout.setUser(checkout.getUser());
        newCheckout.setCart(checkout.getCart());
        newCheckout.setBooks(checkout.getBooks());
        newCheckout.setOrderTotal(checkout.getOrderTotal());
        newCheckout.setEmail(checkout.getEmail());
        newCheckout.setCardNumber(checkout.getCardNumber());
        newCheckout.setShippingAddress(checkout.getShippingAddress());
        newCheckout.setZipCode(checkout.getZipCode());
        newCheckout.setCreatedAt(LocalDateTime.now());
        return checkoutRepository.save(checkout);
    }


}

