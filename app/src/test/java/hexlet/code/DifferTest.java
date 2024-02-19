package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Map;

public class DifferTest {
    Map<String, Object> file1;
    {
        try {
            file1 = Differ.readAndConvertFile1();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Map<String, Object> file2;
    {
        try {
            file2 = Differ.readAndConvertFile2();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void generateFirstTest() {
        String expected = "{\n" +
                " - follow: false\n" +
                "    host: hexlet.io\n" +
                "  - proxy: 123.234.53.22\n" +
                "  - timeout: 50\n" +
                "  + timeout: 20\n" +
                "  + verbose: true\n" + "}\n";
        assertEquals(expected, Differ.generate(file1, file2));
    }

    @Test
    void generateSecondTest() {
        String expected = "";
        String actual = "";
        assertEquals(expected, actual);
    }
}
