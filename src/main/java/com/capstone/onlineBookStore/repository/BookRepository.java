package com.capstone.onlineBookStore.repository;

import com.capstone.onlineBookStore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}