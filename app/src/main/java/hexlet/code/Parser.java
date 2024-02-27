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

// создать общий класс парсинга и в зависимости от свич-кейс - от приходящего формата - паристь файлы (фргументы - пути, формат)
// форматер - это формат вывода, а не приходящих файлов

public class Parser {
    public static Map<String, Object> pars(String filepath1, String filepath2, String format) throws IOException {
        Map<String, Object> result = new HashMap<>();
        Path pathFile1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path pathFile2 = Paths.get(filepath2).toAbsolutePath().normalize();
        String content1;
        String content2;
        ContentFile1 file1;
        ContentFile2 file2;

        ObjectMapper mapper;
        switch (format) {
            case ("json"):
                mapper = new ObjectMapper();
                content1 = new String(Files.readAllBytes(pathFile1));
                file1 = mapper.readValue(content1, ContentFile1.class);
                content2 = new String(Files.readAllBytes(pathFile2));
                file2 = mapper.readValue(content2, ContentFile2.class);
                break;
            case ("yaml"):
            case ("yml"):
                mapper = new YAMLMapper();
                content1 = Files.readString(pathFile1);
                file1 = mapper.readValue(content1, ContentFile1.class);
                content2 = Files.readString(pathFile2);
                file2 = mapper.readValue(content2, ContentFile2.class);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + format);
        }
        Map<String, Object> mapFile1 = mapper.convertValue(file1, new TypeReference<>() {
        });
        result.put("file1", mapFile1);
        Map<String, Object> mapFile2 = mapper.convertValue(file2, new TypeReference<>() {
        });
        result.put("file2", mapFile2);
        return result;
    }
/*    public static Map<String, Object> parsJson(String filepath1, String filepath2) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> result = new HashMap<>();

        Path pathfile1 = Paths.get(filepath1).toAbsolutePath().normalize();
        String content1 = new String(Files.readAllBytes(pathfile1));
        ContentFile1 readerFile1 = mapper.readValue(content1, ContentFile1.class);
        Map<String, Object> file1 = mapper.convertValue(readerFile1, new TypeReference<>() {
        });
        result.put("file1", file1);

        Path pathfile2 = Paths.get(filepath2).toAbsolutePath().normalize();
        String content2 = new String(Files.readAllBytes(pathfile2));
        ContentFile2 readerFile2 = mapper.readValue(content2, ContentFile2.class);
        Map<String, Object> file2 = mapper.convertValue(readerFile2, new TypeReference<>() {
        });
        result.put("file2", file2);
        return result;
    }

    public static Map<String, Object> parsYml(String filepath1, String filepath2) throws IOException {
        ObjectMapper mapper = new YAMLMapper();
        Path pathfile1 = Paths.get(filepath1).toAbsolutePath().normalize();
        String content1 = Files.readString(pathfile1);
        ContentFile1 file1 = mapper.readValue(content1, ContentFile1.class);
        Map<String, Object> file1Convert = mapper.convertValue(file1, new TypeReference<>() {
        });

        Path pathFile2 = Paths.get(filepath2).toAbsolutePath().normalize();
        String content2 = Files.readString(pathFile2);
        ContentFile2 file2 = mapper.readValue(content2, ContentFile2.class);
        Map<String, Object> file2Convert = mapper.convertValue(file2, new TypeReference<>() {
        });

        Map<String, Object> result = new HashMap<>();
        result.put("file1", file1Convert);
        result.put("file2", file2Convert);
        return result;
    }*/
}
