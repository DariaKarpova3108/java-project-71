package hexlet.code;

import java.nio.file.Files;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.skyscreamer.jsonassert.JSONAssert;

public class DifferTest {
    private static String filepathJson1;
    private static String filepathJson2;
    private static String filepathYaml1;
    private static String filepathYaml2;

    private static String expectedJson;
    private static String expectedStylish;
    private static String expectedPlain;

    public static String getPath(String path) {
        Path pathFile = Paths.get(path);
        return pathFile.toAbsolutePath().toString();
    }

    public static String generatePath(String absolutePath, String pathFile) {
        return absolutePath + pathFile;
    }

    public static String readFixture(String absolutePath, String pathFile) throws IOException {
        return Files.readString(Paths.get(generatePath(absolutePath, pathFile)));
    }

    @BeforeAll
    public static void beforeAll() throws IOException {
        String path = getPath("src/test/java/resources");

        filepathJson1 = generatePath(path, "/filepath1.json");
        filepathJson2 = generatePath(path, "/filepath2.json");
        filepathYaml1 = generatePath(path, "/filepath1.yml");
        filepathYaml2 = generatePath(path, "/filepath2.yml");

        expectedJson = readFixture(path, "/expectedJson.json");
        expectedStylish = readFixture(path, "/expectedStylish.text");
        expectedPlain = readFixture(path, "/expectedPlain.text");
    }

    @Test
    public void testGenJs() throws Exception {
        var actual = Differ.generate(filepathJson1, filepathJson2);
        assertEquals(expectedStylish, actual);
    }

    @Test
    public void testGenYaml() throws Exception {
        var actual = Differ.generate(filepathYaml1, filepathYaml2);
        assertEquals(expectedStylish, actual);
    }

    @Test
    public void testGenJson() throws Exception {
        var actual = Differ.generate(filepathJson1, filepathJson2, "json");
        JSONAssert.assertEquals(expectedJson, actual, false);
    }

    @Test
    public void testGenJsonYaml() throws Exception {
        var actual = Differ.generate(filepathYaml1, filepathYaml2, "json");
        JSONAssert.assertEquals(expectedJson, actual, false);
    }

    @Test
    public void testGenStylishYaml() throws Exception {
        var actual = Differ.generate(filepathYaml1, filepathYaml2, "stylish");
        assertEquals(expectedStylish, actual);
    }

    @Test
    public void testGenStylishJs() throws Exception {
        var actual = Differ.generate(filepathJson1, filepathJson2, "stylish");
        assertEquals(expectedStylish, actual);
    }

    @Test
    public void testGenPlainJs() throws Exception {
        var actual = Differ.generate(filepathJson1, filepathJson2, "plain");
        assertEquals(expectedPlain, actual);
    }

    @Test
    public void testGenPlainYml() throws Exception {
        var actual = Differ.generate(filepathYaml1, filepathYaml2, "plain");
        assertEquals(expectedPlain, actual);
    }
}
