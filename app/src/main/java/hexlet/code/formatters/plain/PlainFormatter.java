package hexlet.code.formatters.plain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PlainFormatter {
    public static String formatterPlain(Map<String, Map<String, Object>> list) throws Exception {
        List<String> resultList = new ArrayList<>();

        for (var entry : list.entrySet()) {
            String key = entry.getKey();
            Map<String, Object> values = entry.getValue();
            String status = String.valueOf(values.get("status"));
            Object value = values.get("valueOld");
            Object value2 = values.get("valueNew");

            switch (status) {
                case ("added") ->
                        resultList.add("Property" + " '" + key + "' " + "was added with value: " + isComposite(value2));
                case ("changed") ->
                        resultList.add("Property" + " '" + key + "' " + "was updated. From " + isComposite(value)
                                + " to " + isComposite(value2));
                case ("deleted") -> resultList.add("Property" + " '" + key + "' " + "was removed");
                case ("unchanged") -> resultList.remove(key);
                default -> throw new Exception("Unknown status: " + "'" + "status" + "'");
            }
        }
        return String.join("\n", resultList);
    }

    public static String isComposite(Object value) {
        if (value instanceof Collection<?> || value instanceof Arrays || value instanceof Map<?, ?>) {
            value = "[complex value]";
        } else if (value instanceof String && !value.equals("null")) {
            value = "'" + value + "'";
        } else {
            value = String.valueOf(value);
        }
        return (String) value;
    }
}
