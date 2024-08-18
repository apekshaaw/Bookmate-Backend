package com.example.demo;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveBookTest() {
        Book book = new Book();
        book.setTitle("Test Book Title");
        book.setDescription("Test Book Description");
        book.setImage("testimage.jpg");
        book.setPdf("testfile.pdf");

        bookRepository.save(book);

        Assertions.assertThat(book.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void findAllBooksTest() {
        List<Book> books = bookRepository.findAll();
        Assertions.assertThat(books.size()).isGreaterThan(0);
    }

    @Test
    @Order(3)
    public void findBookByIdTest() {
        Book book = bookRepository.findById(1).orElse(null);
        Assertions.assertThat(book).isNotNull();
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateBookTest() {
        Book book = bookRepository.findById(1).orElse(null);
        book.setTitle("Updated Book Title");
        bookRepository.save(book);
        Book updatedBook = bookRepository.findById(1).orElse(null);
        Assertions.assertThat(updatedBook.getTitle()).isEqualTo("Updated Book Title");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteBookTest() {
        Book book = bookRepository.findById(1).orElse(null);
        bookRepository.delete(book);
        Book deletedBook = bookRepository.findById(1).orElse(null);
        Assertions.assertThat(deletedBook).isNull();
    }
}
