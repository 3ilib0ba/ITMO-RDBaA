package com.example.demo.classes.exceptions;

import java.sql.Time;

public class ClassesStartTimeGreaterOrEqualsClassesEndTimeException extends RuntimeException{
    public ClassesStartTimeGreaterOrEqualsClassesEndTimeException(Time start, Time end) {
        super("Classes start-time[" + start.toString() + "] must be lesser than classes end-time[" + end.toString() + "]");
    }
}
