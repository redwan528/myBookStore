package com.capstone.onlineBookStore.service;

import com.capstone.onlineBookStore.model.Checkout;
import com.capstone.onlineBookStore.model.User;
import com.capstone.onlineBookStore.repository.CheckoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
//public class CheckoutServiceImpl implements CheckoutService {
//
//    private final CheckoutRepository checkoutRepository;
//
//    @Autowired
//    public CheckoutServiceImpl(CheckoutRepository checkoutRepository) {
//        this.checkoutRepository = checkoutRepository;
//    }
//
//
//}
@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CheckoutRepository checkoutRepository;

    @Autowired
    public CheckoutServiceImpl(CheckoutRepository checkoutRepository) {
        this.checkoutRepository = checkoutRepository;
    }

    @Override
    public Checkout createOrder(Checkout checkout) {
        // Implement the logic to save the checkout entity here
        return checkoutRepository.save(checkout);
    }


}

