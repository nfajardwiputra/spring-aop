package chapter.aop.shared.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtil {

    private JsonUtil() {}

    /**
     * Convert any object to string json
     *
     * @param data {Object}
     * @param <T> {type}
     * @return String
     */
    public static <T> String toStringJson(T data) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException ignored) {
            return null;
        }
    }

}
