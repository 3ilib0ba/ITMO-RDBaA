package com.example.demo.client.exceptions;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String roleName) {
        super("Role with name[" + roleName + "] not found");
    }
}
