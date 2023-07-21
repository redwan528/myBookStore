package com.capstone.onlineBookStore.controller;

import com.capstone.onlineBookStore.model.User;
import com.capstone.onlineBookStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/signup")
    public String registerUser(@RequestParam String username, @RequestParam String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // For now, store the password as plain text
        userRepository.save(user);
        // Redirect to a success page or login page after registration
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
        // Perform user authentication (in-memory authentication for now)
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            // Successful login, redirect to the homepage or some other page
            return "redirect:/";
        } else {
            // Failed login, show the login page with an error message
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Return the name of the Thymeleaf template for the login page
    }

    @GetMapping("/signup")
    public String showSignupForm() {
        return "signup"; // Return the name of the Thymeleaf template for the signup page
    }

    // Add code to handle form submissions for login and signup, e.g., POST methods
    // You'll need to handle the form data, perform user authentication (if needed), and redirect accordingly
    // Other methods for handling user-related requests (e.g., login, profile)
}
