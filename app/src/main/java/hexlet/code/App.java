package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true, version = "", description = "Compares two configuration files and shows a difference.")

public class App implements Callable<String> {
    @CommandLine.Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format;

    @CommandLine.Parameters(index = "0", description = "path to first file")
    private String filepath1 = "/Users/dariakarpova/Documents/java-project-71/app/filepath1.json";

    @CommandLine.Parameters(index = "1", description = "path to second file")
    private String filepath2 = "/Users/dariakarpova/Documents/java-project-71/app/filepath2.json";

    @Override
    public String call() throws IOException {

        String contentFomFirstFile = new String(Files.readAllBytes(Paths.get(filepath1)));
        String contentFromSecondFile = new String(Files.readAllBytes(Paths.get(filepath2)));

        ObjectMapper objectMapper = new ObjectMapper();
        ContentFile1 content1 = objectMapper.readValue(contentFomFirstFile, ContentFile1.class);
        ContentFile2 content2 = objectMapper.readValue(contentFromSecondFile, ContentFile2.class);

        Map<String, Object> file1 = objectMapper.convertValue(content1, Map.class);
        Map<String, Object> file2 = objectMapper.convertValue(content2, Map.class);
        Differ gif = new Differ();
        try {
            String result = gif.generate(file1, file2);
            return result;
        } catch (Exception ex) {
            System.out.println("Error reading files: " + ex.getMessage());
            return "";
        }
    }

    public static void main(String[] args) {
        App app = new App();
        CommandLine commandLine = new CommandLine(app);
        commandLine.execute(args);
    }
}


