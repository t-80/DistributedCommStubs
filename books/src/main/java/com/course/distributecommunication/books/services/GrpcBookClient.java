package com.course.distributecommunication.books.services;

import com.distributed.grpc.server.grpcserver.Book;
import com.distributed.grpc.server.grpcserver.BookList;
import com.distributed.grpc.server.grpcserver.BookServiceGrpc;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
@RequiredArgsConstructor
public class GrpcBookClient extends BookServiceGrpc.BookServiceImplBase {

    private final BookService bookService;

    @Override
    public void getAllBooks(com.google.protobuf.Empty request,
                            io.grpc.stub.StreamObserver<com.distributed.grpc.server.grpcserver.BookList> responseObserver) {

        List<Book> bookList = bookService.getBooks().stream().map(
                book -> Book.newBuilder()
                        .setId(book.getId())
                        .setTitle(book.getTitle())
                        .setPages(book.getPages())
                        .setAuthorId(book.getAuthorId())
                        .build()
        )
                .collect(Collectors.toList());


        BookList response = BookList.newBuilder()
                .addAllBookList(bookList)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}