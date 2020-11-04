package com.course.distributecommunication.frontend.services;

import com.course.distributecommunication.frontend.dto.AuthorDto;
import com.course.distributecommunication.frontend.dto.BookDto;
import com.course.distributecommunication.frontend.dto.FrontendDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Slf4j
@Component
public class DashboardService {

    private final WebClient webClientAuthors;
    private final WebClient webClientBooks;

    private DashboardService(WebClient.Builder webClientBuilder) {
        this.webClientAuthors = webClientBuilder.baseUrl("http://authors:8080/api/v1/authors").build();
        this.webClientBooks = webClientBuilder.baseUrl("http://books:8080/api/v1/books").build();
    }

    private List<AuthorDto> getAuthors() {
        return this.webClientAuthors
                .get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<AuthorDto>>() {
                })
                .doOnError((throwable) -> {
                    log.error("Cant get authors");
                })
                .block();
    }

    private List<BookDto> getBooks() {
        return this.webClientBooks
                .get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<BookDto>>() {
                })
                .doOnError((throwable) -> {
                    log.error("Cant get books");
                })
                .block();
    }

    public FrontendDto getAggregate() {
        FrontendDto dto = new FrontendDto();
        dto.setAuthors(getAuthors());
        dto.setBooks(getBooks());
        return dto;
    }
}