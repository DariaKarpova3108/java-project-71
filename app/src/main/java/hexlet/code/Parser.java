package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> pars(String filepath1, String filepath2, String format) throws IOException {
        Map<String, Object> result = new HashMap<>();
        Path pathFile1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path pathFile2 = Paths.get(filepath2).toAbsolutePath().normalize();
        String content1;
        String content2;
        Map<String, Object> file1;
        Map<String, Object> file2;

        ObjectMapper mapper;
        switch (format) {
            case ("json"):
                mapper = new ObjectMapper();
                content1 = new String(Files.readAllBytes(pathFile1));
                file1 = mapper.readValue(content1, new TypeReference<>() {
                });
                content2 = new String(Files.readAllBytes(pathFile2));
                file2 = mapper.readValue(content2, new TypeReference<>() {
                });
                break;
            case ("yaml"):
            case ("yml"):
                mapper = new YAMLMapper();
                content1 = Files.readString(pathFile1);
                file1 = mapper.readValue(content1, new TypeReference<>() {
                });
                content2 = Files.readString(pathFile2);
                file2 = mapper.readValue(content2, new TypeReference<>() {
                });
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + format);
        }

        result.put("file1", file1);
        result.put("file2", file2);
        return result;
    }
}
