package com.example.demo.utils;

import com.example.demo.client.model.Role;

public class RoleObjects {
    public static Role UNAUTHORIZED_ROLE = new Role(
            1L, "UNAUTHORIZED"
    );
    public static Role USER_ROLE = new Role(
            2L, "USER"
    );
    public static Role ADMIN_ROLE = new Role(
            3L, "ADMIN"
    );
    public static Role MANAGER_ROLE = new Role(
            4L, "MANAGER"
    );
    public static Role INSTRUCTOR_ROLE = new Role(
            5L, "INSTRUCTOR"
    );
}
