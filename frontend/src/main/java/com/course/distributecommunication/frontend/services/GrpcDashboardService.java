package com.course.distributecommunication.frontend.services;

import com.course.distributecommunication.frontend.dto.AuthorDto;
import com.course.distributecommunication.frontend.dto.BookDto;
import com.distributed.grpc.server.grpcserver.AuthorServiceGrpc;
import com.distributed.grpc.server.grpcserver.BookServiceGrpc;
import com.google.protobuf.Empty;
import lombok.val;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GrpcDashboardService {

    @GrpcClient("authors-service")
    private AuthorServiceGrpc.AuthorServiceBlockingStub authorServiceBlockingStub;

    @GrpcClient("books-service")
    private BookServiceGrpc.BookServiceBlockingStub bookServiceBlockingStub;

    public List<AuthorDto> receiveAuthors() {
        return authorServiceBlockingStub.getAllAuthors(Empty.newBuilder().build()).getAuthorListList().stream().map(author -> {
            val authorDto = new AuthorDto();
            authorDto.setId(author.getId());
            authorDto.setFirstName(author.getFirstName());
            authorDto.setLastName(author.getLastName());
            return authorDto;
        }).collect(Collectors.toList());
    }

    public List<BookDto> receiveBooks() {
        return bookServiceBlockingStub.getAllBooks(Empty.newBuilder().build()).getBookListList().stream().map(book -> {
            val bookDto = new BookDto();
            bookDto.setId(book.getId());
            bookDto.setTitle(book.getTitle());
            bookDto.setPages(book.getId());
            bookDto.setAuthorId(book.getAuthorId());
            return bookDto;
        }).collect(Collectors.toList());
    }

}
