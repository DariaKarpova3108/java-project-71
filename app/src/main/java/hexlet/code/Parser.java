package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String content, String formatFile) throws IOException {
        ObjectMapper mapper = getMapper(formatFile);
        Map<String, Object> file = mapper.readValue(content, new TypeReference<>() {
        });

        return file;
    }

    public static ObjectMapper getMapper(String formatFile) {
        ObjectMapper mapper;
        switch (formatFile) {
            case ("json"):
                mapper = new ObjectMapper();
                break;
            case ("yaml"):
            case ("yml"):
                mapper = new YAMLMapper();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + formatFile);
        }
        return mapper;
    }
}
