package hexlet.code;

import picocli.CommandLine;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0", description = "Compares two configuration files and shows a difference.")

public class App implements Callable<Integer> {
    final int SUCCESSFUL_EXIT = 0;
    final int WRONG_OUTPUT = 1;

    @CommandLine.Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format;
    @CommandLine.Parameters(index = "0", description = "path to first file")
    private String filepath1 = "path first";
    @CommandLine.Parameters(index = "1", description = "path to second file")
    private String filepath2 = "path second";

    @Override
    public Integer call() throws IOException {

        Map<String, Object> file1 = Differ.readAndConvertFile1();
        Map<String, Object> file2 = Differ.readAndConvertFile2();

        try {
            String result = Differ.generate(file1, file2);
            System.out.println(result);
            return SUCCESSFUL_EXIT;
        } catch (Exception ex) {
            System.out.println("Error reading files: " + ex.getMessage());
            return WRONG_OUTPUT;
        }
    }

    public static void main(String[] args) {
        App app = new App();
        CommandLine commandLine = new CommandLine(app);
        commandLine.execute(args);
    }
}



