//package com.capstone.onlineBookStore.model;
//
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.CreationTimestamp;
//
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.List;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "checkout")
//public class Checkout {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @ManyToMany
//    @JoinTable(
//            name = "checkout_books",
//            joinColumns = @JoinColumn(name = "checkout_id"),
//            inverseJoinColumns = @JoinColumn(name = "book_id"))
//    private List<Book> books;
//
//    @Column(name = "order_total")
//    private BigDecimal orderTotal;
//
//    // Add more fields for shipping address, payment details, etc.
//
//    @CreationTimestamp
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "created_at")
//    private Date createdAt;
//
//    // Add getters, setters, constructors, and other methods as needed
//}

package com.capstone.onlineBookStore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "checkout")
public class Checkout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "checkout_books",
            joinColumns = @JoinColumn(name = "checkout_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;

    @Column(name = "order_total")
    private BigDecimal orderTotal;

    // Transient field to hold the user's email (not persisted to the database)
    @Transient
    private String email;

    // Add more fields for card number, shipping address, and zip code
    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "shipping_address", nullable = false)
    private String shippingAddress;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    // Add constructors, getters, and setters
    // Note: Lombok generates these for us automatically, so we don't need to write them explicitly.
}

