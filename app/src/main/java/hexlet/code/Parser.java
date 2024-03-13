package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String content, String formatFile) throws IOException {
        ObjectMapper mapper = getMapper(formatFile);
        return mapper.readValue(content, new TypeReference<>() {
        });
    }

    public static ObjectMapper getMapper(String formatFile) {
        ObjectMapper mapper = switch (formatFile) {
            case ("json") -> new ObjectMapper();
            case ("yaml"), ("yml") -> new YAMLMapper();
            default -> throw new IllegalStateException("Unexpected value: " + formatFile);
        };
        return mapper;
    }
}
