
package com.capstone.onlineBookStore.repository;

import com.capstone.onlineBookStore.model.Cart;
import com.capstone.onlineBookStore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {


    Cart findByUser(User user);

}
