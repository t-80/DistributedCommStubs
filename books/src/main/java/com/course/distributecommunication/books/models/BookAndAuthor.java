package com.course.distributecommunication.books.models;

import lombok.Data;

@Data
public class BookAndAuthor {
    private int id;
    private String title;
    private int pages;
    private int authorId;
    private String firstName;
    private String lastName;
}