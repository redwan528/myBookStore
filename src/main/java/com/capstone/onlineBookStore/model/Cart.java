package com.capstone.onlineBookStore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // One-to-one relationship with User (assuming each user has only one cart)
    @OneToOne(mappedBy = "cart")
    private User user;

    @ManyToMany
    private List<Book> books = new ArrayList<>();

    // Constructors, getters, and setters
}
