package com.university.auth.grpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class AuthGrpcService extends AuthServiceGrpc.AuthServiceImplBase {

    @Override
    public void validateToken(
            TokenRequest request,
            StreamObserver<TokenResponse> responseObserver) {

        TokenResponse response = TokenResponse.newBuilder()
                .setValid(true)
                .setRole("ADMIN")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
