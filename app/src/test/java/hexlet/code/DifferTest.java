package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    Map<String, Object> files;
    @BeforeEach
    public void beforeEach() throws IOException {
        files = Parser.parsJson("/Users/dariakarpova/Documents/java-project-71/app/filepath1.json",
                "/Users/dariakarpova/Documents/java-project-71/app/filepath2.json");
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
        var actual = Differ.generate(files, "Json");
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
        var actual = Differ.generate(files, "yml");
        assertEquals(expected, actual);
    }
}
