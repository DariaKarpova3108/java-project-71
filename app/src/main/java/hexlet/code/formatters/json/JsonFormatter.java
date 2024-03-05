package hexlet.code.formatters.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.StatusValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonFormatter {
    public static String formatterJson(List<Map<String, StatusValue>> list) throws Exception {
        Map<String, Object> json = new HashMap<>();

        for (Map<String, StatusValue> map : list) {
            String key = map.entrySet().iterator().next().getKey();
            StatusValue valueStatus = map.entrySet().iterator().next().getValue();
            String status = valueStatus.getStatus();
            Object value = valueStatus.getOldValue();
            Object value2 = valueStatus.getNewValue();

            switch (status) {
                case ("withoutChanges") -> json.put(key, isComposite(value));
                case ("added"), ("update") -> json.put(key, isComposite(value2));
                case ("deleted") -> json.remove(key);
                default ->  throw new Exception("Unknown status: " + "'" + "status" + "'");
            }
        }
        var listStr = json.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .toList();

        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(listStr);
        return result;
    }

    public static Object isComposite(Object value) {
        if (value instanceof String && !value.equals("null")) {
            value = value.toString();
        } else if (value.equals("null")) {
            value = null;
        }
        return value;
    }
}
