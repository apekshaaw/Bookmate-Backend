package com.example.demo.service.impl;

import com.example.demo.entity.Book;
import com.example.demo.pojo.BookPojo;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public void saveBook(BookPojo bookPojo) {
        Book book = new Book();
        book.setTitle(bookPojo.getTitle());
        book.setDescription(bookPojo.getDescription());
        book.setImage(bookPojo.getImage());
        book.setPdf(bookPojo.getPdf());
        bookRepository.save(book);
    }

    @Override
    public List<BookPojo> getAllBooks() {
        return bookRepository.findAll().stream().map(book -> {
            BookPojo bookPojo = new BookPojo();
            bookPojo.setId(book.getId());
            bookPojo.setTitle(book.getTitle());
            bookPojo.setDescription(book.getDescription());
            bookPojo.setImage(book.getImage());
            bookPojo.setPdf(book.getPdf());
            return bookPojo;
        }).collect(Collectors.toList());
    }

    @Override
    public BookPojo getBookById(Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        BookPojo bookPojo = new BookPojo();
        bookPojo.setId(book.getId());
        bookPojo.setTitle(book.getTitle());
        bookPojo.setDescription(book.getDescription());
        bookPojo.setImage(book.getImage());
        bookPojo.setPdf(book.getPdf());
        return bookPojo;
    }

    @Override
    public void updateBook(BookPojo bookPojo) {
        Book book = bookRepository.findById(bookPojo.getId()).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setTitle(bookPojo.getTitle());
        book.setDescription(bookPojo.getDescription());
        book.setImage(bookPojo.getImage());
        book.setPdf(bookPojo.getPdf());
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }
}
