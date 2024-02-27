package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Differ {

    public static String defineFormat(String filepath1, String filepath2) {
        String format;
        if (filepath1.endsWith(".json") && filepath2.endsWith(".json")) {
            format = "json";
        } else if (filepath1.endsWith(".yml") && filepath2.endsWith(".yml")) {
            format = "yaml";
        } else {
            format = "stylish";
        }

        if (format == null) {
            return "Unsupported format";
        }
        return format;
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, defineFormat(filepath1, filepath2));
    }

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        if (!format.equals("json") && !format.equals("yaml")) {
            return "stylish";
        }

        Map<String, Object> files = Parser.pars(filepath1, filepath2, format);

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<Map<String, Object>> typeReference = new TypeReference<>() {
        };

        Map<String, Object> file1 = objectMapper.convertValue(files.get("file1"), typeReference);
        Map<String, Object> file2 = objectMapper.convertValue(files.get("file2"), typeReference);

        return tree(file1, file2);
    }

    public static String tree(Map<String, Object> file1, Map<String, Object> file2) {
        Map<String, String> result = new HashMap<>();
        for (var entry : file1.entrySet()) {
            Object value = entry.getValue();
            String key = entry.getKey();
            if (file2.containsKey(key)) {
                Object value2 = file2.get(key);
                if (value != null && value2 != null && value.toString().equals(value2.toString())) {
                    result.put("  " + key, value.toString());
                } else {
                    result.put("- " + key, Optional.ofNullable(value).map(Object::toString).orElse("null"));
                    result.put("+ " + key, Optional.ofNullable(value2).map(Object::toString).orElse("null"));
                }
            } else {
                result.put("- " + key, Optional.ofNullable(value).map(Object::toString).orElse("null"));
            }
        }

        for (var entry2 : file2.entrySet()) {
            String key2 = entry2.getKey();
            Object value2 = entry2.getValue();
            if (!file1.containsKey(key2)) {
                result.put("+ " + key2, Optional.ofNullable(value2).map(Object::toString).orElse("null"));
            }
        }

        String list = result.entrySet().stream()
                .sorted(Comparator.comparing(k -> k.getKey().substring(2)))
                .map(entry -> "  " + entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining("\n"));
        return "{" + "\n" + list + "\n" + "}";
    }

//    public static String tree(Map<String, Object> file1, Map<String, Object> file2) {
//        Map<String, Object> result = new HashMap<>();
//        for (var entry : file1.entrySet()) {
//            Object value = entry.getValue();
//            String key = entry.getKey();
//            if (file2.containsKey(key)) {
//                Object value2 = file2.get(key);
//                if (value != null && value.equals(value2)) {
//                    result.put("  " + key, getValue(value));
//                } else {
//                    result.put("- " + key, getValue(value));
//                    result.put("+ " + key, getValue(value2));
//                }
//            } else {
//                result.put("- " + key, getValue(value));
//            }
//        }
//
//        for (var entry2 : file2.entrySet()) {
//            String key2 = entry2.getKey();
//            Object value2 = entry2.getValue();
//            if (!file1.containsKey(key2)) {
//                result.put("+ " + key2, getValue(value2));
//            }
//        }
//
//        String list = result.entrySet().stream()
//                .sorted(Comparator.comparing(k -> k.getKey().substring(2)))
//                .map(entry -> "  " + entry.getKey() + ": " + entry.getValue())
//                .collect(Collectors.joining("\n"));
//        return "{" + "\n" + list + "\n" + "}";
//    }
//
//    public static String getValue(Object val) {
//        if (val == null) {
//            return "null";
//        } else if (val.getClass().isArray() || val instanceof Collections) {
//            return val.toString();
//        } else {
//            return Optional.ofNullable(val).map(Object::toString).orElse("default");
//        }
//    }
}
