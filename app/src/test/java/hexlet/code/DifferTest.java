package hexlet.code;

import java.nio.file.Files;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
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

    @BeforeAll
    public static void beforeAll() throws IOException {
        String path = "src/main/java/resources";
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();

        filepathJson1 = absolutePath + "/filepath1.json";
        filepathJson2 = absolutePath + "/filepath2.json";
        filepathYaml1 = absolutePath + "/filepath1.yml";
        filepathYaml2 = absolutePath + "/filepath2.yml";

        expectedJson = Files.readString(Paths.get("src/test/java/recources/expectedJson.json")).trim();
        expectedStylish = Files.readString(Paths.get("src/test/java/recources/expectedStylish.text")).trim();
        expectedPlain = Files.readString(Paths.get("src/test/java/recources/expectedPlain.text")).trim();
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
    public void testGenStylishJson() throws Exception {
        var actual = Differ.generate(filepathJson1, filepathJson2);
        assertEquals(expectedStylish, actual);
    }

    @Test
    public void testGenStylishYaml() throws Exception {
        var actual = Differ.generate(filepathYaml1, filepathYaml2);
        assertEquals(expectedStylish, actual);
    }

    @Test
    public void testGenPlain() throws Exception {
        var actual = Differ.generate(filepathJson1, filepathJson2, "plain");
        assertEquals(expectedPlain, actual);
    }
}
