package com.course.distributecommunication.books.controlles;

import com.course.distributecommunication.books.models.Book;
import com.course.distributecommunication.books.models.BookAndAuthor;
import com.course.distributecommunication.books.services.BookService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping
    public ResponseEntity<HttpStatus> add(@RequestBody BookAndAuthor bookAndAuthor) {
        bookService.add(bookAndAuthor);
        return ResponseEntity.ok().build();
    }
}
