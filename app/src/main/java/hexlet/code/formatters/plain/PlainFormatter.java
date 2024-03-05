package hexlet.code.formatters.plain;

import hexlet.code.StatusValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlainFormatter {
    public static String formatterPlain(List<Map<String, StatusValue>> list) throws Exception {
        List<String> resultList = new ArrayList<>();

        for (Map<String, StatusValue> map : list) {

            String key = map.entrySet().iterator().next().getKey();
            StatusValue valueStatus = map.entrySet().iterator().next().getValue();
            String status = valueStatus.getStatus();
            Object value = valueStatus.getOldValue();
            Object value2 = valueStatus.getNewValue();

            switch (status) {
                case ("added") ->
                        resultList.add("Property" + " '" + key + "' " + "was added with value: " + isComposite(value2));
                case ("update") ->
                        resultList.add("Property" + " '" + key + "' " + "was updated. From " + isComposite(value)
                                + " to " + isComposite(value2));
                case ("deleted") -> resultList.add("Property" + " '" + key + "' " + "was removed");
                case ("withoutChanges") -> resultList.remove(key);
                default -> throw new Exception("Unknown status: " + "'" + "status" + "'");
            }
        }
        var result = resultList.stream()
                .sorted(Comparator.comparing(PlainFormatter::getSubstr))
                .collect(Collectors.joining("\n"));
        return result;
    }

    public static String getSubstr(String str) {
        int indexStart = str.indexOf("'") + 1;
        return str.substring(indexStart);
    }

    public static String isComposite(Object value) {
        if (value instanceof Collection<?> || value.getClass().isArray() || value instanceof Map<?, ?>) {
            value = "[complex value]";
        } else if (value instanceof String && !value.equals("null")) {
            value = "'" + value + "'";
        } else if (value.equals("null")) {
            value = null;
        } else {
            value = String.valueOf(value);
        }
        return (String) value;
    }
}
