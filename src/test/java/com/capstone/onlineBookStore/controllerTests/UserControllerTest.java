package com.capstone.onlineBookStore.controllerTests;

import com.capstone.onlineBookStore.controller.UserController;
import com.capstone.onlineBookStore.dto.UserDto;
import com.capstone.onlineBookStore.model.User;
import com.capstone.onlineBookStore.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testHome() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("homepage"));
    }







    @Test
    void testRegistrationFailure() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setEmail("user@test.com");

        given(userService.findByEmail(anyString())).willReturn(new User());

        mockMvc.perform(post("/register/save")
                        .flashAttr("user", userDto))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("register"));
    }
}
