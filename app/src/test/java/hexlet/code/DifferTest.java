package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private String fileContent1;
    private String fileContent2;

    @BeforeEach
    public void beforeAll() throws IOException {
        fileContent1 = Parser.parsJson("/Users/dariakarpova/Documents/java-project-71/app/filepath1.json");
        fileContent2 = Parser.parsJson("/Users/dariakarpova/Documents/java-project-71/app/filepath2.json");
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
        var actual = Differ.generate(fileContent1, fileContent2, "JSON");
        assertEquals(expected, actual);
    }
}
