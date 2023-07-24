package com.capstone.onlineBookStore.service;

import com.capstone.onlineBookStore.dto.UserDto;
import com.capstone.onlineBookStore.model.User;
import com.capstone.onlineBookStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

//    private final UserRepository userRepository;
//
//    @Autowired
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//
//
//    public void saveUser(User user) {
//        userRepository.save(user);
//    }
//
//    public User getUserById(Long id) {
//        return userRepository.findById(id).orElse(null);
//    }

    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();

}
