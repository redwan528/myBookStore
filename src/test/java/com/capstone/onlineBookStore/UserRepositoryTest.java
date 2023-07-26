//package com.capstone.onlineBookStore;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//import com.capstone.onlineBookStore.model.User;
//import com.capstone.onlineBookStore.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@ExtendWith(SpringExtension.class)
//@ExtendWith(MockitoExtension.class)
//public class UserRepositoryTest {
//
//    @InjectMocks
//    private UserRepository userRepository; // This will be automatically injected with the mocked instance.
//
//    @Mock
//    private User userMock;
//
//    @Test
//    public void testFindByEmail() {
//        // Arrange
//        String email = "test@example.com";
//        when(userRepository.findByEmail(email)).thenReturn(userMock);
//
//        // Act
//        User foundUser = userRepository.findByEmail(email);
//
//        // Assert
//        assertEquals(userMock, foundUser);
//    }
//
//    @Test
//    public void testFindByEmail_NonExistingEmail_ReturnsNull() {
//        // Arrange
//        String email = "nonexistent@example.com";
//        when(userRepository.findByEmail(email)).thenReturn(null);
//
//        // Act
//        User foundUser = userRepository.findByEmail(email);
//
//        // Assert
//        assertEquals(null, foundUser);
//    }
//}
