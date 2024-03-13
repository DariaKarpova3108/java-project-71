package hexlet.code;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Tree {
    public static Map<String, Map<String, Object>> tree(Map<String, Object> file1, Map<String, Object> file2) {
        Set<String> keys = new TreeSet<>(Comparator.naturalOrder());
        keys.addAll(file1.keySet());
        keys.addAll(file2.keySet());
        Map<String, Map<String, Object>> result = new LinkedHashMap<>();

        for (var key : keys) {
            Map<String, Object> values = new LinkedHashMap<>();
            if (!file1.containsKey(key)) {
                values.put("status", "added");
                values.put("valueOld", null);
                values.put("valueNew", file2.get(key));
                result.put(key, values);
            } else if (!file2.containsKey(key)) {
                values.put("status", "deleted");
                values.put("valueOld", file1.get(key));
                values.put("valueNew", null);
                result.put(key, values);
            } else if (Objects.equals(file1.get(key), file2.get(key))) {
                values.put("status", "unchanged");
                values.put("valueOld", file1.get(key));
                values.put("valueNew", file1.get(key));
                result.put(key, values);
            } else if (!Objects.equals(file1.get(key), file2.get(key))) {
                values.put("status", "changed");
                values.put("valueOld", file1.get(key));
                values.put("valueNew", file2.get(key));
                result.put(key, values);
            }
        }
        return result;
    }
}


