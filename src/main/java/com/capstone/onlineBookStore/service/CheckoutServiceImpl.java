package com.capstone.onlineBookStore.service;

import com.capstone.onlineBookStore.model.Checkout;
import com.capstone.onlineBookStore.model.User;
import com.capstone.onlineBookStore.repository.CheckoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CheckoutRepository checkoutRepository;

    @Autowired
    public CheckoutServiceImpl(CheckoutRepository checkoutRepository) {
        this.checkoutRepository = checkoutRepository;
    }

//    @Override
//    public Checkout createCheckout(User user) {
////        Checkout checkout = new Checkout();
////        checkout.setUser(user);
////        // You can add more logic here to set other properties of the checkout (e.g., order total, books, etc.)
////        return checkoutRepository.save(checkout);
//
//    }

    // Add more service methods for checkout-related operations as needed
}
