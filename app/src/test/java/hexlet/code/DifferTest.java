package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
  /*  Map<String, Object> filesJson;
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
    }*/

    String filepathJson1;
    String filepathJson2;
    String filepathYaml1;
    String filepathYaml2;

    @BeforeEach
    public void beforeEach() throws IOException {
        String path = "src/test/java/resources";
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();

        filepathJson1 = absolutePath + "/filepath1.json";
        filepathJson2 = absolutePath + "/filepath2.json";

        filepathYaml1 = absolutePath + "/filepath1.yml";
        filepathYaml2 = absolutePath + "/filepath2.yml";
    }

    @Test
    public void testGenJson() throws IOException {
        var expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        var actual = Differ.generate(filepathJson1, filepathJson2, "json");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenYaml() throws IOException {
        var expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        var actual = Differ.generate(filepathYaml1, filepathYaml2, "yaml");
        assertEquals(expected, actual);
    }
}
