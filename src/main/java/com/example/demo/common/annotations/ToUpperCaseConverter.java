package com.example.demo.common.annotations;

import com.fasterxml.jackson.databind.util.StdConverter;

public class ToUpperCaseConverter extends StdConverter<String, String> {
    @Override
    public String convert(String value) {
        if (value == null){
            return null;
        }
        return value.toUpperCase();
    }
}
