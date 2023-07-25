package com.capstone.onlineBookStore.repository;

import com.capstone.onlineBookStore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // You can define additional methods here if needed
    //User findByUsername(String username);
    User findByEmail(String email);


}
