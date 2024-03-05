package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Tree {
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
