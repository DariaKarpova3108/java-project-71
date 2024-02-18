package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Differ {
    static ObjectMapper mapper = new ObjectMapper();

    public static Map<String, Object> readAndConvertFile1() throws IOException {
        String absolutFilepath1 = "/Users/dariakarpova/Documents/java-project-71/app/filepath1.json";
        String relativePath1 = "app/filepath1.json";
        if (Paths.get(absolutFilepath1).isAbsolute()) {
            String content = new String(Files.readAllBytes(Paths.get(absolutFilepath1)));
            ContentFile1 readerFile1 = mapper.readValue(content, ContentFile1.class);
            return mapper.convertValue(readerFile1, Map.class);
        } else {
            String contentOfRelative = new String(Files.readAllBytes(Paths.get(relativePath1)));
            ContentFile1 readerFile1 = mapper.readValue(contentOfRelative, ContentFile1.class);
            return mapper.convertValue(readerFile1, Map.class);
        }
    }

    public static Map<String, Object> readAndConvertFile2() throws IOException {
        String absolutFilepath2 = "/Users/dariakarpova/Documents/java-project-71/app/filepath2.json";
        String relativePath2 = "app/filepath2.json";
        if (Paths.get(absolutFilepath2).isAbsolute()) {
            String content = new String(Files.readAllBytes(Paths.get(absolutFilepath2)));
            ContentFile2 readerFile2 = mapper.readValue(content, ContentFile2.class);
            return mapper.convertValue(readerFile2, Map.class);
        } else {
            String contentOfRelative2 = new String(Files.readAllBytes(Paths.get(relativePath2)));
            ContentFile2 readerFile2 = mapper.readValue(contentOfRelative2, ContentFile2.class);
            return mapper.convertValue(readerFile2, Map.class);
        }
    }

    public static String generate(Map<String, Object> file1, Map<String, Object> file2) {
        String list = "";
        for (Map.Entry<String, Object> entry : file1.entrySet()) {
            Object value = entry.getValue();
            String key = entry.getKey();
            if (file2.containsKey(key)) {
                Object value2 = file2.get(key);
                if (value.equals(value2)) {
                    list += "  " + key + ": " + value + "\n";
                } else {
                    list += "- " + key + ": " + value + "\n" + "+ " + key + ": " + value2 + "\n";
                }
            } else {
                list += "- " + key + ": " + value + "\n";
            }
        }

        for (Map.Entry<String, Object> entry2 : file2.entrySet()) {
            String key2 = entry2.getKey();
            Object value2 = entry2.getValue();
            if (!file1.containsKey(key2)) {
                list += "- " + key2 + ": " + value2 + "\n";
            }
        }
        String list2 = sort(list);
        return list2;
    }

    public static String sort(String list) {
        List<String> tmp = Arrays.asList(list.split("\n"));
        ArrayList<String> sortedList = new ArrayList<>(tmp);
        Comparator<String> comparator = (s1, s2) -> {
            String substr1 = s1.substring(2);
            String substr2 = s2.substring(2);

            String prefix1 = s1.substring(0, 1);
            String prefix2 = s2.substring(0, 1);
            if (!substr1.equals(substr2)) {
                return substr1.compareTo(substr2);
            }
            return prefix2.compareTo(prefix1);
        };
        sortedList.sort(comparator);

        String list3 = String.join("\n", sortedList);
        String result = "{" + "\n" + list3 + "\n" + "}";
        return result;
    }
}
