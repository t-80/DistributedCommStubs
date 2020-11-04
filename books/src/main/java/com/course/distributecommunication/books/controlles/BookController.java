package com.course.distributecommunication.books.controlles;

import com.course.distributecommunication.books.models.Book;
import com.course.distributecommunication.books.services.BookService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<Collection<Book>> getAll() {
        return ResponseEntity.ok().body(bookService.getBooks());
    }

}
