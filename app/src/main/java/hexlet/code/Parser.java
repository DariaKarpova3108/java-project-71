package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Parser {
    public static String pars(String filepath) throws IOException {
        Path pathfile = Paths.get(filepath).toAbsolutePath().normalize();
        String content = new String(Files.readAllBytes(pathfile));
        return content;
    }
}