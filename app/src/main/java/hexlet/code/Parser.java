package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Parser {

    public static Map parsJson(String filepath1, String filepath2) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
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

    public static Map parsYml(String filepath1, String filepath2) throws IOException {
        ObjectMapper mapper = new YAMLMapper();
        Path pathfile1 = Paths.get(filepath1).toAbsolutePath().normalize();
        String content1 = new String(Files.readString(pathfile1));
        ContentFile1 file1 = mapper.readValue(content1, ContentFile1.class);
        Map<String, Object> file1Convert = mapper.convertValue(file1, Map.class);

        Path pathFile2 = Paths.get(filepath2).toAbsolutePath().normalize();
        String content2 = new String(Files.readString(pathFile2));
        ContentFile2 file2 = mapper.readValue(content2, ContentFile2.class);
        Map<String, Object> file2Convert = mapper.convertValue(file2, Map.class);

        Map<String, Object> result = new HashMap<>();
        result.put("file1", file1Convert);
        result.put("file2", file2Convert);
        return result;
    }
}
