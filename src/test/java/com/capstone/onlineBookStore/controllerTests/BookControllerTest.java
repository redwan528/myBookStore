package com.capstone.onlineBookStore.controllerTests;

import com.capstone.onlineBookStore.controller.BookController;
import com.capstone.onlineBookStore.exception.BookNotFoundException;
import com.capstone.onlineBookStore.model.Book;
import com.capstone.onlineBookStore.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {
    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void testGetAllBooks() throws Exception {
        Book book1 = new Book();
        book1.setId(1L);
        Book book2 = new Book();
        book2.setId(2L);
        given(bookService.getAllBooks()).willReturn(Arrays.asList(book1, book2));

        mockMvc.perform(get("/all-books"))
                .andExpect(status().isOk())
                .andExpect(view().name("bookList"))
                .andExpect(model().attributeExists("books"));
    }

    @Test
    void testGetBookById() throws Exception {
        Book book = new Book();
        book.setId(1L);
        given(bookService.getBookById(1L)).willReturn(book);

        mockMvc.perform(get("/book-1"))
                .andExpect(status().isOk())
                .andExpect(view().name("bookDetails"))
                .andExpect(model().attributeExists("book"));
    }

    @Test
    void testGetBookByIdNotFound() throws Exception {
        given(bookService.getBookById(1L)).willThrow(new BookNotFoundException("Book not found"));

        mockMvc.perform(get("/book-1"))
                .andExpect(status().isNotFound());
    }


}

