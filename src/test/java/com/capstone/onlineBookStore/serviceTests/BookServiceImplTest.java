
package com.capstone.onlineBookStore.serviceTests;

import com.capstone.onlineBookStore.exception.BookNotFoundException;
import com.capstone.onlineBookStore.model.Book;
import com.capstone.onlineBookStore.repository.BookRepository;
import com.capstone.onlineBookStore.service.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBooks() {
        // Arrange
        List<Book> mockBooks = new ArrayList<>();
        mockBooks.add(new Book(1L, "Book 1", "Author 1", 25.99));
        mockBooks.add(new Book(2L, "Book 2", "Author 2", 19.99));

        when(bookRepository.findAll()).thenReturn(mockBooks);

        // Act
        List<Book> result = bookService.getAllBooks();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Book 1", result.get(0).getTitle());
        assertEquals("Book 2", result.get(1).getTitle());
    }

    @Test
    void testGetBookByIdFound() {
        // Arrange
        Long bookId = 1L;
        Book mockBook = new Book(bookId, "Book 1", "Author 1", 25.99);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(mockBook));

        // Act
        Book result = bookService.getBookById(bookId);

        // Assert
        assertNotNull(result);
        assertEquals("Book 1", result.getTitle());
        assertEquals("Author 1", result.getAuthor());
        assertEquals(25.99, result.getPrice());
    }

    @Test
    void testGetBookByIdNotFound() {
        // Arrange
        Long bookId = 1L;

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BookNotFoundException.class, () -> bookService.getBookById(bookId));
    }

    @Test
    void testSaveBook() {
        // Arrange
        Book bookToSave = new Book(null, "New Book", "New Author", 29.99);
        Book savedBook = new Book(1L, "New Book", "New Author", 29.99);

        when(bookRepository.save(bookToSave)).thenReturn(savedBook);

        // Act
        Book result = bookService.saveBook(bookToSave);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("New Book", result.getTitle());
        assertEquals("New Author", result.getAuthor());
        assertEquals(29.99, result.getPrice());
    }

    @Test
    void testDeleteBookById() {
        // Arrange
        Long bookId = 1L;

        // Act
        bookService.deleteBookById(bookId);

        // Assert
        verify(bookRepository, times(1)).deleteById(bookId);
    }

}
