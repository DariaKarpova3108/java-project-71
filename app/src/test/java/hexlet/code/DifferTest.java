package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DifferTest {
    @Test
    public void testGen() throws IOException {
        var expected = "{\n" +
                "  - follow: false\n" +
                "    host: hexlet.io\n" +
                "  - proxy: 123.234.53.22\n" +
                "  - timeout: 50\n" +
                "  + timeout: 20\n" +
                "  + verbose: true\n" +
                "}";
        String filepath1 = Parser.pars("/Users/dariakarpova/Documents/java-project-71/app/filepath1.json");
        String filepath2 = Parser.pars("/Users/dariakarpova/Documents/java-project-71/app/filepath2.json");

        assertEquals(expected, Differ.generate(filepath1,filepath2,""));
    }

}
