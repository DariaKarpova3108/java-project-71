package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

import static hexlet.code.Tree.tree;

public class Differ {
    public static String defineFormat(String filepath1) {
        String formatFile = filepath1.substring(filepath1.lastIndexOf(".") + 1);
        return formatFile;
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        String formatFile = defineFormat(filepath1);
        Map<String, Object> files = Parser.pars(filepath1, filepath2, formatFile);
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<Map<String, Object>> typeReference = new TypeReference<>() {
        };
        Map<String, Object> file1 = objectMapper.convertValue(files.get("file1"), typeReference);
        Map<String, Object> file2 = objectMapper.convertValue(files.get("file2"), typeReference);
        List<Map<String, StatusValue>> list = tree(file1, file2);
        return Formatter.convertFormat(format, list);
    }
}
