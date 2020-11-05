package com.course.distributecommunication.frontend.services;

import com.course.distributecommunication.frontend.dto.AuthorDto;
import com.course.distributecommunication.frontend.dto.BookDto;
import com.distributed.grpc.server.grpcserver.AuthorList;
import com.distributed.grpc.server.grpcserver.AuthorServiceGrpc;
import com.distributed.grpc.server.grpcserver.BookList;
import com.distributed.grpc.server.grpcserver.BookServiceGrpc;
import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GrpcDashboardService {

    public List<AuthorDto> receiveAuthors() {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("authors:9091")
                .usePlaintext()
                .build();
        AuthorServiceGrpc.AuthorServiceBlockingStub stub = AuthorServiceGrpc.newBlockingStub(channel);
        AuthorList response = stub.getAllAuthors(Empty.newBuilder().build());
        channel.shutdown();
        return response.getAuthorListList().stream()
                .map(author -> {
                    val authorDto = new AuthorDto();
                    authorDto.setId(author.getId());
                    authorDto.setFirstName(author.getFirstName());
                    authorDto.setLastName(author.getLastName());
                    return authorDto;
                })
                .collect(Collectors.toList());
    }

    public List<BookDto> receiveBooks() {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("books:9092")
                .usePlaintext()
                .build();
        BookServiceGrpc.BookServiceBlockingStub stub = BookServiceGrpc.newBlockingStub(channel);
        BookList response = stub.getAllBooks(Empty.newBuilder().build());
        return response.getBookListList().stream().map(book -> {
            val bookDto = new BookDto();
            bookDto.setId(book.getId());
            bookDto.setTitle(book.getTitle());
            bookDto.setPages(book.getId());
            bookDto.setAuthorId(book.getAuthorId());
            return bookDto;
        }).collect(Collectors.toList());
    }

}
