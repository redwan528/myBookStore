package com.capstone.onlineBookStore.serviceTests;
import com.capstone.onlineBookStore.dto.UserDto;
import com.capstone.onlineBookStore.model.User;
import com.capstone.onlineBookStore.repository.CartRepository;
import com.capstone.onlineBookStore.repository.UserRepository;
import com.capstone.onlineBookStore.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private CartRepository cartRepository;

    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        initMocks(this);
        userService = new UserServiceImpl(userRepository, passwordEncoder, cartRepository);
    }

    @Test
    void testRegisterUser() {
        User user = new User();
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.registerUser(user);
        assertEquals(user, result);
    }

    @Test
    void testSaveUser() {
        UserDto userDto = new UserDto();
        userDto.setFirstName("first");
        userDto.setLastName("last");
        userDto.setEmail("email@example.com");
        userDto.setPassword("password");

        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");

        userService.saveUser(userDto);
    }

    @Test
    void testFindByEmail() {
        User user = new User();
        user.setEmail("email@example.com");

        when(userRepository.findByEmail(any(String.class))).thenReturn(user);

        User result = userService.findByEmail("email@example.com");
        assertEquals(user, result);
    }

    @Test
    void testFindName() {
        User user = new User();
        user.setName("Test Name");
        user.setEmail("email@example.com");

        when(userRepository.findByEmail(any(String.class))).thenReturn(user);

        String result = userService.findName("email@example.com");
        assertEquals("Test Name", result);
    }

    @Test
    void testGetUserById() {
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);
        assertEquals(user, result);
    }

    @Test
    void testGetUserByIdNotFound() {
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.getUserById(1L));
    }

    @Test
    void testFindAllUsers() {
        User user1 = new User();
        user1.setName("First User");
        user1.setEmail("first@example.com");

        User user2 = new User();
        user2.setName("Second User");
        user2.setEmail("second@example.com");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<UserDto> result = userService.findAllUsers();
        assertEquals(2, result.size());
        assertEquals("First", result.get(0).getFirstName());
        assertEquals("Second", result.get(1).getFirstName());
    }

    @Test
    void testGetUserByPrincipal() {
        User user = new User();
        user.setEmail("email@example.com");

        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("email@example.com");
        when(userRepository.findByEmail(any(String.class))).thenReturn(user);

        User result = userService.getUserByPrincipal(principal);
        assertEquals(user, result);
    }





}
