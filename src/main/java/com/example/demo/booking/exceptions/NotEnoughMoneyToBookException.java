package com.example.demo.booking.exceptions;

public class NotEnoughMoneyToBookException extends RuntimeException {
    public NotEnoughMoneyToBookException() {
        super("Not enough money to book");
    }
}
