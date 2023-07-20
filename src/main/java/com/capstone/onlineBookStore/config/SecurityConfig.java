//package com.capstone.onlineBookStore.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/signup", "/css/**", "/js/**").permitAll() // Allow public access to certain URLs
//                .anyRequest().authenticated() // Require authentication for all other URLs
//                .and()
//                .formLogin()
//                .loginPage("/login").permitAll() // Custom login page
//                .and()
//                .logout().permitAll(); // Enable logout
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user") // Replace with your desired username
//                .password(passwordEncoder().encode("password")) // Replace with your desired password
//                .roles("USER");
//    }
//
//    // Bean for password encoder
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}