package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Parser {

    static ObjectMapper mapper = new ObjectMapper();

    public static Map parsJson(String filepath1, String filepath2) throws IOException {
        Map<String, Object> result = new HashMap<>();
        Path pathfile1 = Paths.get(filepath1).toAbsolutePath().normalize();
        String content1 = new String(Files.readAllBytes(pathfile1));
        ContentFile1 readerFile1 = mapper.readValue(content1, ContentFile1.class);
        Map<String, Object> file1 = mapper.convertValue(readerFile1, Map.class);
        result.put("file1", file1);
        Path pathfile2 = Paths.get(filepath2).toAbsolutePath().normalize();
        String content2 = new String(Files.readAllBytes(pathfile2));
        ContentFile2 readerFile2 = mapper.readValue(content2, ContentFile2.class);
        Map<String, Object> file2 = mapper.convertValue(readerFile2, Map.class);
        result.put("file2", file2);
        return result;
    }

 /*   public static String parsYml(String filepath) {
    }*/


}
