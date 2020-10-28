package com.course.distributecommunication.authors.services;

import com.distributed.grpc.server.grpcserver.Author;
import com.distributed.grpc.server.grpcserver.AuthorList;
import com.distributed.grpc.server.grpcserver.AuthorServiceGrpc;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
@RequiredArgsConstructor
public class GrpcAuthorClient extends AuthorServiceGrpc.AuthorServiceImplBase {

    private final AuthorService authorService;

    @Override
    public void getAllAuthors(com.google.protobuf.Empty request,
                              io.grpc.stub.StreamObserver<com.distributed.grpc.server.grpcserver.AuthorList> responseObserver) {

        List<Author> authorList = authorService.getAuthors().stream().map(
                author -> Author.newBuilder()
                        .setId(author.getId())
                        .setFirstName(author.getFirstName())
                        .setLastName(author.getLastName())
                        .build()
        )
                .collect(Collectors.toList());

        AuthorList response = AuthorList.newBuilder()
                .addAllAuthorList(authorList)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}