package com.own.space.web.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Writer;

public class JsonUtils {

    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String jsonFromObj(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Failed to convert object to JSON string", e);
            return null;
        }
    }

    public static <T> T objFromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json,clazz);
        } catch (IOException e) {
            log.error("Failed to create object from json", e);
            return null;
        }
    }

    public static void write(Writer writer, Object value) throws IOException {
        new ObjectMapper().writeValue(writer, value);
    }
}
