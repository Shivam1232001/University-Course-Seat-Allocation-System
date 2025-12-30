package com.university.seat.grpc;


import org.springframework.stereotype.Service;

import net.devh.boot.grpc.client.inject.GrpcClient;

// gRPC generated classes (from auth.proto)
import com.university.auth.grpc.AuthServiceGrpc;
import com.university.auth.grpc.TokenRequest;
import com.university.auth.grpc.TokenResponse;

@Service
public class AuthGrpcClient {

    @GrpcClient("auth-service")
    private AuthServiceGrpc.AuthServiceBlockingStub authStub;

    public boolean isAdmin() {

        TokenRequest request = TokenRequest.newBuilder()
                .setToken("dummy-token")
                .build();

        TokenResponse response = authStub.validateToken(request);

        return response.getValid() && "ADMIN".equals(response.getRole());
    }

    public boolean isTokenValid(String token) {

        TokenResponse response = authStub.validateToken(
                TokenRequest.newBuilder()
                        .setToken(token)
                        .build()
        );

        return response.getValid();
    }
}

