package com.capstone.onlineBookStore.controller;

import com.capstone.onlineBookStore.dto.UserDto;
import com.capstone.onlineBookStore.model.User;
import com.capstone.onlineBookStore.repository.UserRepository;
import com.capstone.onlineBookStore.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;


@Controller
public class UserController {

//    private final UserRepository userRepository;
//
//    @Autowired
//    public UserController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @PostMapping("/signup")
//    public String registerUser(@RequestParam String username, @RequestParam String password) {
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password); // For now, store the password as plain text
//        userRepository.save(user);
//        // Redirect to a success page or login page after registration
//        return "redirect:/login";
//    }
//
//    @PostMapping("/login")
//    public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
//        // Perform user authentication (in-memory authentication for now)
//        User user = userRepository.findByUsername(username);
//        if (user != null && user.getPassword().equals(password)) {
//            // Successful login, redirect to the homepage or some other page
//            return "redirect:/";
//        } else {
//            // Failed login, show the login page with an error message
//            model.addAttribute("error", "Invalid username or password");
//            return "login";
//        }
//    }
//
//    @GetMapping("/login")
//    public String showLoginForm() {
//        return "login"; // Return the name of the Thymeleaf template for the login page
//    }
//
//    @GetMapping("/signup")
//    public String showSignupForm() {
//        UserDto user = new UserDto();
//        model.addAttribute("user", user);
//        return "register";
//    }
private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // handler method to handle user registration request
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

    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

}
