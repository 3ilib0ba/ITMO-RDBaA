package com.example.demo.utils;

import com.example.demo.balance.model.Balance;

import static com.example.demo.utils.RoleObjects.MANAGER_ROLE;

public class UtilsObjects {
    // TODO добавить сюда разные объекты для тестирования, так как много где они будут общими, похожими...

    public static Long STUDIO_ID = 100L;

    public static Balance STUDIO_BALANCE = new Balance(
            STUDIO_ID,
            15000F,
            MANAGER_ROLE
    );

}
