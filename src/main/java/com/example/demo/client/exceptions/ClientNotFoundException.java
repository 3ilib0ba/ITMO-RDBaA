package com.example.demo.client.exceptions;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(Long id) {
        super("Client with id[" + id.toString() + "] not found");
    }
}
