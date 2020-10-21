package com.course.distributecommunication.frontend.dto;

import lombok.Data;

import java.util.List;

@Data
public class FrontendDto {
    private List<AuthorDto> authors;
    private List<BookDto> books;
}
