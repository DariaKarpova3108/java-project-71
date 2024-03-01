package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Differ {
    public static String defineFormat(String filepath1) {
        String formatFile = filepath1.substring(filepath1.lastIndexOf(".") + 1);
        return formatFile;
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        String formatFile = defineFormat(filepath1);
        Map<String, Object> files = Parser.pars(filepath1, filepath2, formatFile);
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<Map<String, Object>> typeReference = new TypeReference<>() {
        };
        Map<String, Object> file1 = objectMapper.convertValue(files.get("file1"), typeReference);
        Map<String, Object> file2 = objectMapper.convertValue(files.get("file2"), typeReference);
        List<Map<String, StatusValue>> list = tree(file1, file2);
        String result = Formatter.convertFormat(format, list);
        return result;
    }

    public static List<Map<String, StatusValue>> tree(Map<String, Object> file1, Map<String, Object> file2) {
        List<Map<String, StatusValue>> differ = new ArrayList<>();

        for (var entry : file1.entrySet()) {
            Object value = entry.getValue();
            String key = entry.getKey();
            if (file2.containsKey(key)) {
                Object value2 = file2.get(key);
                if (value != null && value2 != null && value.toString().equals(value2.toString())) {
                    differ.add(Map.of(key, new StatusValue("withoutChanges", value, value)));
                } else {
                    differ.add(Map.of(key, new StatusValue("update", Optional.ofNullable(value).orElse("null"),
                            Optional.ofNullable(value2).orElse("null"))));
                }
            } else {
                differ.add(Map.of(key, new StatusValue("deleted", Optional.ofNullable(value).orElse("null"),
                        Optional.empty())));
            }
        }

        for (var entry2 : file2.entrySet()) {
            String key2 = entry2.getKey();
            Object value2 = entry2.getValue();
            if (!file1.containsKey(key2)) {
                differ.add(Map.of(key2, new StatusValue("added", Optional.empty(),
                        Optional.ofNullable(value2).orElse("null"))));
            }
        }
        return differ;
    }
}
