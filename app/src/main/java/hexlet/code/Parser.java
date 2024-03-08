package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String content1, String content2, String formatFile) throws IOException {
        ObjectMapper mapper = getMapper(formatFile);
        Map<String, Object> file1 = mapper.readValue(content1, new TypeReference<>() {
        });
        Map<String, Object> file2 = mapper.readValue(content2, new TypeReference<>() {
        });
        Map<String, Object> result = new HashMap<>();
        result.put("file1", file1);
        result.put("file2", file2);
        return result;
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
