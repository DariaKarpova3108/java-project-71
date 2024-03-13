package hexlet.code;

import hexlet.code.formatters.json.JsonFormatter;
import hexlet.code.formatters.plain.PlainFormatter;
import hexlet.code.formatters.stylish.StylishFormatter;

import java.util.Map;

public class Formatter {
    public static String convertFormat(String format, Map<String, Map<String, Object>> list) throws Exception {
        switch (format) {
            case ("json"):
                return JsonFormatter.formatterJson(list);
            case ("plain"):
                return PlainFormatter.formatterPlain(list);
            case ("stylish"):
                return StylishFormatter.formatterStylish(list);
            default:
                throw new IllegalStateException("Unexpected value: " + format);
        }
    }
}
