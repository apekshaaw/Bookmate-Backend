package com.example.demo.controller;

import com.example.demo.pojo.BookPojo;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/save")
    public void save(@RequestBody BookPojo bookPojo) {
        this.bookService.saveBook(bookPojo);
    }

    @GetMapping("/getAll")
    public List<BookPojo> getAll() {
        return this.bookService.getAllBooks();
    }

    @GetMapping("/getById/{id}")
    public BookPojo getById(@PathVariable Integer id) {
        return this.bookService.getBookById(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody BookPojo bookPojo) {
        this.bookService.updateBook(bookPojo);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        this.bookService.deleteBook(id);
    }
}
