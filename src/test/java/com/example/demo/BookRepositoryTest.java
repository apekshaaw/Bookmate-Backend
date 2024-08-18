package com.example.demo;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    private static Long savedBookId; // Store the generated book ID here

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveBookTest() {
        Book book = new Book();
        book.setTitle("Test Book Title");
        book.setDescription("Test Book Description");
        book.setImage("testimage.jpg");
        book.setPdf("testfile.pdf");

        Book savedBook = bookRepository.save(book);
        savedBookId = Long.valueOf(savedBook.getId()); // Save the generated ID
        System.out.println("Saved Book ID: " + savedBookId);

        Assertions.assertThat(savedBookId).isGreaterThan(0);
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
        Optional<Book> book = bookRepository.findById(Math.toIntExact(savedBookId)); // Use the saved ID
        Assertions.assertThat(book.isPresent()).isTrue();
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateBookTest() {
        Optional<Book> optionalBook = bookRepository.findById(Math.toIntExact(savedBookId)); // Use the saved ID
        Assertions.assertThat(optionalBook.isPresent()).isTrue();

        Book book = optionalBook.get();
        book.setTitle("Updated Book Title");
        bookRepository.save(book);

        Book updatedBook = bookRepository.findById(Math.toIntExact(savedBookId)).orElse(null); // Use the saved ID
        Assertions.assertThat(updatedBook.getTitle()).isEqualTo("Updated Book Title");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteBookTest() {
        Optional<Book> optionalBook = bookRepository.findById(Math.toIntExact(savedBookId)); // Use the saved ID
        Assertions.assertThat(optionalBook.isPresent()).isTrue();

        Book book = optionalBook.get();
        bookRepository.delete(book);

        Optional<Book> deletedBook = bookRepository.findById(Math.toIntExact(savedBookId)); // Use the saved ID
        Assertions.assertThat(deletedBook.isEmpty()).isTrue();
    }
}
