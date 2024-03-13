package hexlet.code.formatters.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JsonFormatter {
    public static String formatterJson(Map<String, Map<String, Object>> list) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(list);
    }
}

