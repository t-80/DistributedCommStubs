package com.course.distributecommunication.frontend.dto;

import lombok.Data;

@Data
public class BookDto {
    private int id;
    private String title;
    private int pages;
    private int authorId;
}
