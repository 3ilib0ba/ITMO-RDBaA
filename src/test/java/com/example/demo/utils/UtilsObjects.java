package com.example.demo.utils;

import com.example.demo.balance.model.Balance;
import com.example.demo.classes.model.Classes;
import com.example.demo.client.model.Client;
import com.example.demo.common.enums.Gender;
import com.example.demo.studio.model.LegalInfo;
import com.example.demo.studio.model.Position;
import com.example.demo.studio.model.Studio;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import static com.example.demo.utils.RoleObjects.MANAGER_ROLE;
import static com.example.demo.utils.RoleObjects.USER_ROLE;

public class UtilsObjects {

    public static Long STUDIO_ID = 1L;
    public static Long POSITION_ID = 1L;
    public static Long CLASSES_ID = 1L;
    public static Long CLIENT_ID = 1L;
    public static Long LEGAL_INFO_ID = 1L;

    public Balance CLIENT_BALANCE = new Balance(
           CLIENT_ID,
            200F,
            null
    );

    public Balance STUDIO_BALANCE = new Balance(
            STUDIO_ID,
            15000F,
            MANAGER_ROLE
    );

    public Client CLIENT = new Client(
            CLIENT_ID,
            "CLIENT_NAME",
            "client@mail.ru",
            "+79527977524",
            Gender.MALE.name(),
            null,
            CLIENT_BALANCE
    );

    public LegalInfo LEGAL_INFO = new LegalInfo(
            LEGAL_INFO_ID,
            "FULL DESCRIPTION",
            "+79527977524",
            "legal-info@mail.ru",
            "7394-134"
    );

    public Studio STUDIO = new Studio(
            STUDIO_ID,
            "STUDIO_NAME",
            "DESCRIPTION",
            null,
            LEGAL_INFO,
            STUDIO_BALANCE
    );

    public Position POSITION = new Position(
            POSITION_ID,
            "ADDRESS",
            "06:00-22:00",
            STUDIO,
            null
    );
    public Classes CLASSES = new Classes(
            CLASSES_ID,
            "CLASSES_NAME",
            new Date(new java.util.Date().getTime() + 86400000),
            Time.valueOf("10:00:00"),
            Time.valueOf("12:00:00"),
            100,
            POSITION,
            List.of()
    );

}
