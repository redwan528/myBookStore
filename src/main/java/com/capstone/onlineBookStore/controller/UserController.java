package com.capstone.onlineBookStore.controller;
import com.capstone.onlineBookStore.dto.UserDto;
import com.capstone.onlineBookStore.model.User;
import com.capstone.onlineBookStore.service.UserService;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {


        private UserService userService;

        public UserController(UserService userService) {
            this.userService = userService;
        }

        @GetMapping("/")
        public String home(){
            return "homepage";
        }

    @GetMapping("/login")
        public String loginForm() {



            return "login";
        }

        @GetMapping("register")
        public String showRegistrationForm(Model model){
            UserDto user = new UserDto();
            model.addAttribute("user", user);
            return "register";
        }

        // handler method to handle register user form submit request
        @PostMapping("/register/save")
        public String registration(@Valid @ModelAttribute("user") UserDto user,
                                   BindingResult result,
                                   Model model){
            User existing = userService.findByEmail(user.getEmail());
            if (existing != null) {
                result.rejectValue("email", null, "There is already an account registered with that email");
            }
            if (result.hasErrors()) {
                model.addAttribute("user", user);
                return "register";
            }
            userService.saveUser(user);
            return "redirect:/register?success";
        }

    }



