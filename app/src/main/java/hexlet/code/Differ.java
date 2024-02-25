package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Differ {
    /*public static String generate(String filepath1, String filepath2) throws IOException {
        Map<String, Object> files;
        String format = "default";
        switch (format) {
            case "json":
                files = Parser.parsJson(filepath1, filepath2);
                return Differ.generate(files, "json");
            case "yaml":
                files = Parser.parsYml(filepath1, filepath2);
                return Differ.generate(files, "yaml");
            default:
                throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }

    public static String generate(Map<String, Object> files, String format) {
        Map<String, Object> file1 = (Map<String, Object>) files.get("file1");
        Map<String, Object> file2 = (Map<String, Object>) files.get("file2");

        Map<String, Object> result = new HashMap<>();
        for (var entry : file1.entrySet()) {
            Object value = entry.getValue();
            String key = entry.getKey();
            if (file2.containsKey(key)) {
                Object value2 = file2.get(key);
                if (value.equals(value2)) {
                    result.put("  " + key, value);
                } else {
                    result.put("- " + key, value);
                    result.put("+ " + key, value2);
                }
            } else {
                result.put("- " + key, value);
            }
        }

        for (var entry2 : file2.entrySet()) {
            String key2 = entry2.getKey();
            Object value2 = entry2.getValue();
            if (!file1.containsKey(key2)) {
                result.put("+ " + key2, value2);
            }
        }

        String list = result.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining("\n"));
        return "{ " + "\n" + list + "\n" + "}";
    }*/

    public static String generate(String filepath1, String filepath2) throws IOException {
        String format;
        if (filepath1.endsWith(".json") && filepath2.endsWith(".json")) {
            format = "json";
        } else if (filepath1.endsWith(".yml") && filepath2.endsWith(".yml")) {
            format = "yaml";
        } else {
            return "Files not found or invalid format";
        }

        if (format == null) {
            return "Unsupported format";
        }

        return generate(filepath1, filepath2, format);
    }

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        if (!format.equals("json") && !format.equals("yaml")) {
            return "Unsupported format";
        }

        Map<String, Object> files = format.equals("json") ?
                Parser.parsJson(filepath1, filepath2) : Parser.parsYml(filepath1, filepath2);

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<Map<String, Object>> typeReference = new TypeReference<>() {
        };

        Map<String, Object> file1 = objectMapper.convertValue(files.get("file1"), typeReference);
        Map<String, Object> file2 = objectMapper.convertValue(files.get("file2"), typeReference);

        Map<String, Object> result = new HashMap<>();
        for (var entry : file1.entrySet()) {
            Object value = entry.getValue();
            String key = entry.getKey();
            if (file2.containsKey(key)) {
                Object value2 = file2.get(key);
                if (value.equals(value2)) {
                    result.put("  " + key, value);
                } else {
                    result.put("- " + key, value);
                    result.put("+ " + key, value2);
                }
            } else {
                result.put("- " + key, value);
            }
        }

        for (var entry2 : file2.entrySet()) {
            String key2 = entry2.getKey();
            Object value2 = entry2.getValue();
            if (!file1.containsKey(key2)) {
                result.put("+ " + key2, value2);
            }
        }

        String list = result.entrySet().stream()
                .sorted(Comparator.comparing(k -> k.getKey().substring(2)))
                .map(entry -> "  " + entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining("\n"));
        return "{" + "\n" + list + "\n" + "}";
    }
}
