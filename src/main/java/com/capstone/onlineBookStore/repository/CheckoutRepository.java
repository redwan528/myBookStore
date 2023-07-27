package com.capstone.onlineBookStore.repository;

import com.capstone.onlineBookStore.model.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {
    // You can define additional methods here if needed
}
