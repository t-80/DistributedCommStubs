package com.course.distributecommunication.authors.services;

import com.course.distributecommunication.authors.models.BookAndAuthor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class BookListener {

    @Autowired
    private AuthorService authorService;

    @RabbitListener(queues = "${queue.book.name}")
    public void listen(@Payload BookAndAuthor bookAndAuthor) {
        System.out.println("Message read from queue : " + bookAndAuthor);
        authorService.add(bookAndAuthor);
    }
}
