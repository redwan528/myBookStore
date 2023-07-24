package com.capstone.onlineBookStore.service;

import com.capstone.onlineBookStore.dto.UserDto;
import com.capstone.onlineBookStore.model.User;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    void saveUser(UserDto userDto);

    User findByEmail(String email);

//    void findUser(UserDto userDto);

    String findName(String name);

    List<UserDto> findAllUsers();

}
