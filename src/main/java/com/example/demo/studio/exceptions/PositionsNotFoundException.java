package com.example.demo.studio.exceptions;

public class PositionsNotFoundException extends RuntimeException {
    public PositionsNotFoundException(Long studioId) {
        super("Positions with studio-id[" + studioId.toString() + "] not found");
    }

    public PositionsNotFoundException(String address) {
        super("Positions with address[" + address + "] not found");
    }
}
