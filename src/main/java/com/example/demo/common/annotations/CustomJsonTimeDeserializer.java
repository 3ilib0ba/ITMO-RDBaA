package com.example.demo.common.annotations;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CustomJsonTimeDeserializer extends JsonDeserializer<Time> {
    @SneakyThrows
    @Override
    public Time deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String time = p.getText();
        try {
            return new Time(format.parse(time).getTime());
        } catch (ParseException e) {
            throw new ParseException("\"" + time + "\": expected format HH:mm:ss", e.getErrorOffset());
        }
    }
}
