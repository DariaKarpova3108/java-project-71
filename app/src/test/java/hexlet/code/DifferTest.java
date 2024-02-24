package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    Map<String, Object> filesJson;
    Map<String, Object> filesYaml;

    @BeforeEach
    public void beforeEach() throws IOException {
        String path = "src/test/java/resources";
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();

        filesJson = Parser.parsJson(absolutePath + "/filepath1.json",
                absolutePath + "/filepath2.json");

        filesYaml = Parser.parsYml(absolutePath + "/filepath1.yml",
                absolutePath + "/filepath2.yml");
    }

    @Test
    public void testGenJson() {
        var expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        var actual = Differ.generate(filesJson, "json");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenYaml() {
        var expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        var actual = Differ.generate(filesYaml, "yaml");
        assertEquals(expected, actual);
    }
}
