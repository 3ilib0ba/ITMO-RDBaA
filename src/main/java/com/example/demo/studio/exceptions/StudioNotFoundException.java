package com.example.demo.studio.exceptions;

public class StudioNotFoundException extends RuntimeException {
    public StudioNotFoundException(String studioName) {
        super("Studio with name[" + studioName + "] not found" );
    }

    public StudioNotFoundException(Long id) {
        super("Studio with id[" + id.toString() + "] not found" );
    }
}
