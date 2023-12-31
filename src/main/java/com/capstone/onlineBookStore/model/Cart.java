//package com.capstone.onlineBookStore.model;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Data
//@RequiredArgsConstructor
//@AllArgsConstructor
//@Table(name="cart")
//public class Cart {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    // One-to-one relationship with User (assuming each user has only one cart)
////    @OneToOne
////    @JoinColumn(name = "user_id")
//    // One-to-one relationship with User (assuming each user has only one cart)
//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//
//    @ManyToMany
//    @JoinTable(
//            name = "cart_books",
//            joinColumns = @JoinColumn(name = "cart_id"),
//            inverseJoinColumns = @JoinColumn(name = "book_id"))
//    private List<Book> books = new ArrayList<>();
//
//}

package com.capstone.onlineBookStore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name="cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "cart_books",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books = new ArrayList<>();
}
