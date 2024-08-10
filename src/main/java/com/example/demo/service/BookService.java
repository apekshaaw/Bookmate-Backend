package com.example.demo.service;

import com.example.demo.pojo.BookPojo;

import java.util.List;

public interface BookService {
    void saveBook(BookPojo bookPojo);
    List<BookPojo> getAllBooks();
    BookPojo getBookById(Integer id);
    void updateBook(BookPojo bookPojo);
    void deleteBook(Integer id);
}
