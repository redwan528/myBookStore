package com.capstone.onlineBookStore.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
//@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name="author")
    private String author;

    @Column(name="price")
    private double price;

    public Book(String title, String author, double price) {
        this.title=title;
        this.author=author;
        this.price=price;
    }



}