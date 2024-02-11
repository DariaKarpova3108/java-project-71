package hexlet.code;

import java.util.Map;

public class Differ {
    public  String generate(Map<String, Object> file1, Map<String, Object> file2) {
        String result = "";
        for (Map.Entry<String, Object> entry : file1.entrySet()) {
            Object value = entry.getValue();
            String key = entry.getKey();
            if (file2.containsKey(key)) {
                Object value2 = file2.get(key);
                if (value.equals(value2)) {
                    result += key + ": " + value + "\n";
                } else {
                    result += "- " + key + ": " + value + "\n" + "+ " + key + ": " + value2 + "\n";
                }
            } else {
                result += "- " + key + ": " + value + "\n";
            }
        }

        for(Map.Entry<String, Object> entry2 : file2.entrySet()) {
            String key2 = entry2.getKey();
            Object value2 = entry2.getValue();
            if (!file1.containsKey(key2)) {
                result += "- " + key2 + ": " + value2 + "\n";
            }
        }
        return result;
    }
}
