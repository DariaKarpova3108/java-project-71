package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Differ {

    public static String defineFormat(String filepath1, String filepath2) {
        String formatFile1 = filepath1.substring(filepath1.lastIndexOf(".") + 1);
        String formatFile2 = filepath2.substring(filepath2.lastIndexOf(".") + 1);

        if (formatFile1.equals(formatFile2)) {
            return formatFile1;
        } else {
            return "stylish";
        }
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, defineFormat(filepath1, filepath2));
    }

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        Map<String, Object> files = Parser.pars(filepath1, filepath2, format);
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<Map<String, Object>> typeReference = new TypeReference<>() {
        };
        Map<String, Object> file1 = objectMapper.convertValue(files.get("file1"), typeReference);
        Map<String, Object> file2 = objectMapper.convertValue(files.get("file2"), typeReference);
        return tree(file1, file2);
    }

    public static String tree(Map<String, Object> file1, Map<String, Object> file2) {
        List<Map<String, String>> result = new ArrayList<>();

        for (var entry : file1.entrySet()) {
            Object value = entry.getValue();
            String key = entry.getKey();
            if (file2.containsKey(key)) {
                Object value2 = file2.get(key);
                if (value != null && value2 != null && value.toString().equals(value2.toString())) {
                    result.add(Map.of("  " + key, value.toString()));
                } else {
                    result.add(Map.of("- " + key, Optional.ofNullable(value).map(Object::toString).orElse("null")));
                    result.add(Map.of("+ " + key, Optional.ofNullable(value2).map(Object::toString).orElse("null")));
                }
            } else {
                result.add(Map.of("- " + key, Optional.ofNullable(value).map(Object::toString).orElse("null")));
            }
        }

        for (var entry2 : file2.entrySet()) {
            String key2 = entry2.getKey();
            Object value2 = entry2.getValue();
            if (!file1.containsKey(key2)) {
                result.add(Map.of("+ " + key2, Optional.ofNullable(value2).map(Object::toString).orElse("null")));
            }
        }

        String list = result.stream()
                .sorted(Comparator.comparing(map -> map.entrySet().iterator().next().getKey().substring(2)))
                .map(map -> map.entrySet().iterator().next())
                .map(map -> "  " + map.getKey() + ": " + map.getValue())
                .collect(Collectors.joining("\n", "{\n", "\n}"));
        return list;
    }
}