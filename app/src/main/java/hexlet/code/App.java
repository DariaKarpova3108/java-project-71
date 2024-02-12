package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true, version = "", description = "Compares two configuration files and shows a difference.")

public class App implements Callable<String> {

    private static ObjectMapper mapper = new ObjectMapper();
    @CommandLine.Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format;

    @CommandLine.Parameters(index = "0", description = "path to first file")
    private String filepath1 = "/Users/dariakarpova/Documents/java-project-71/app/filepath1.json";

    @CommandLine.Parameters(index = "1", description = "path to second file")
    private String filepath2 = "/Users/dariakarpova/Documents/java-project-71/app/filepath2.json";

    @Override
    public String call()  {
        Map<String, Object> file1 = null;
        try {
            file1 = readAndConvertFile1(filepath1);
        } catch (IOException e) {
            System.out.println("Error reading and converting file1: " + e.getMessage());
        }
        Map<String, Object> file2 = null;
        try {
            file2 = readAndConvertFile2(filepath2);
        } catch (IOException e) {
            System.out.println("Error reading and converting file2: " + e.getMessage());
        }

        Differ gif = new Differ();

        try {
            String result = gif.generate(file1, file2);
            return result;
        } catch (Exception ex) {
            System.out.println("Error reading files: " + ex.getMessage());
            return "";
        }
    }

    public static Map<String, Object> readAndConvertFile1(String filepath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filepath)));
        ContentFile1 readerFile1 = mapper.convertValue(content, ContentFile1.class);
        return mapper.convertValue(readerFile1, Map.class);
    }

    public static Map<String, Object> readAndConvertFile2(String filepath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filepath)));
        ContentFile2 readerFile2 = mapper.convertValue(content, ContentFile2.class);
        return mapper.convertValue(readerFile2, Map.class);
    }

    public static void main(String[] args) {
        App app = new App();
        CommandLine commandLine = new CommandLine(app);
        commandLine.execute(args);
    }
}


