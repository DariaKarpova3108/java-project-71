package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Differ {
    static ObjectMapper mapper = new ObjectMapper();

    /*  public static Map<String, Object> generate(String filepath1, String filepath2) throws IOException {
            Map<String, Object> result;
            result = generate(filepath1, filepath2, "");
            return result;
        }*/
    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        ContentFile1 readerFile1 = mapper.readValue(Parser.parsJson(filepath1), ContentFile1.class);
        Map<String, Object> file1 = mapper.convertValue(readerFile1, Map.class);

        ContentFile2 readerFile2 = mapper.readValue(Parser.parsJson(filepath2), ContentFile2.class);
        Map<String, Object> file2 = mapper.convertValue(readerFile2, Map.class);

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
    }
}
