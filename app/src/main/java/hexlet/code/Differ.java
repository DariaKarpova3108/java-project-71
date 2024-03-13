package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    public static String defineFormat(String filepath1) {
        return filepath1.substring(filepath1.lastIndexOf(".") + 1);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        String content1 = getContent(filepath1);
        String content2 = getContent(filepath2);
        String formatFile = defineFormat(filepath1);

        Map<String, Object> file1 = Parser.parse(content1, formatFile);
        Map<String, Object> file2 = Parser.parse(content2, formatFile);
        Map<String, Map<String, Object>> list = Tree.tree(file1, file2);
        return Formatter.convertFormat(format, list);
    }

    public static String getContent(String filepath) throws IOException {
        Path pathFile = Paths.get(filepath).toAbsolutePath().normalize();
        return new String(Files.readAllBytes(pathFile));
    }
}
