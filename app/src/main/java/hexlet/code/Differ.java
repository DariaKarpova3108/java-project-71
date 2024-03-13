package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    public static String defineFormat(String filepath1) {
        String formatFile = filepath1.substring(filepath1.lastIndexOf(".") + 1);
        return formatFile;
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    //    public static String generate(String filepath1, String filepath2, String format) throws Exception {
//        Path pathFile1 = Paths.get(filepath1).toAbsolutePath().normalize();
//        Path pathFile2 = Paths.get(filepath2).toAbsolutePath().normalize();
//        String content1 = new String(Files.readAllBytes(pathFile1));
//        String content2 = new String(Files.readAllBytes(pathFile2));
//        String formatFile = defineFormat(filepath1);
//
//        Map<String, Object> files = Parser.parse(content1, content2, formatFile);
//        ObjectMapper objectMapper = new ObjectMapper();
//        TypeReference<Map<String, Object>> typeReference = new TypeReference<>() {
//        };
//        Map<String, Object> file1 = objectMapper.convertValue(files.get("file1"), typeReference);
//        Map<String, Object> file2 = objectMapper.convertValue(files.get("file2"), typeReference);
//        List<Map<String, StatusValue>> list = Tree.tree(file1, file2);
//        String result = Formatter.convertFormat(format, list);
//        return result;
//    }
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        String content1 = getContent(filepath1);
        String content2 = getContent(filepath2);
        String formatFile = defineFormat(filepath1);

        Map<String, Object> file1 = Parser.parse(content1, formatFile);
        Map<String, Object> file2 = Parser.parse(content2, formatFile);
        Map<String, Map<String, Object>> list = Tree.tree(file1, file2);
        String result = Formatter.convertFormat(format, list);
        return result;
    }

    public static String getContent(String filepath) throws IOException {
        Path pathFile = Paths.get(filepath).toAbsolutePath().normalize();
        String content = new String(Files.readAllBytes(pathFile));
        return content;
    }
}
