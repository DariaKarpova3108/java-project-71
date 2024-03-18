package hexlet.code.formatters.stylish;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StylishFormatter {
    public static String formatterStylish(Map<String, Map<String, Object>> list) throws Exception {
        List<Map<String, String>> resultList = new ArrayList<>();

        for (var entry : list.entrySet()) {
            String key = entry.getKey();
            Map<String, Object> values = entry.getValue();
            String status = String.valueOf(values.get("status"));

            switch (status) {
                case ("unchanged") -> resultList.add(Map.of("  " + key,
                        Optional.ofNullable(values.get("value1")).map(Object::toString).orElse("null")));
                case ("changed") -> {
                    resultList.add(Map.of("- " + key,
                            Optional.ofNullable(values.get("value1")).map(Object::toString).orElse("null")));
                    resultList.add(Map.of("+ " + key,
                            Optional.ofNullable(values.get("value2")).map(Object::toString).orElse("null")));
                }
                case ("deleted") -> resultList.add(Map.of("- " + key,
                        Optional.ofNullable(values.get("value1")).map(Object::toString).orElse("null")));
                case ("added") -> resultList.add(Map.of("+ " + key,
                        Optional.ofNullable(values.get("value2")).map(Object::toString).orElse("null")));
                default -> throw new Exception("Unknown status: " + "'" + "status" + "'");
            }
        }

        return resultList.stream()
                .map(entry -> entry.entrySet().iterator().next())
                .map(entry -> "  " + entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining("\n", "{\n", "\n}"));
    }
}
