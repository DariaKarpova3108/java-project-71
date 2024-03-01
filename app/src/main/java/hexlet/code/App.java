package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<Integer> {
    final int successfulExit = 0;
    final int wrongOutput = 1;
    @Option(names = {"-f", "--format"}, defaultValue = "stylish", paramLabel = "format",
            description = "output format [default: stylish]")
    private String format;
    @Parameters(paramLabel = "filepath1", index = "0", description = "path to first file")
    private String filepath1;
    @Parameters(paramLabel = "filepath2", index = "1", description = "path to second file")
    private String filepath2;

    @Override
    public Integer call() {
        try {
            String result = Differ.generate(filepath1, filepath2, format);
            System.out.println(result);
            return successfulExit;
        } catch (Exception ex) {
            System.out.println("Error reading files: " + ex.getMessage());
            return wrongOutput;
        }
    }

    public static void main(String[] args) {
        App app = new App();
        CommandLine commandLine = new CommandLine(app);
        commandLine.execute(args);
    }
}



