package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<Integer> {
    public static final int SUCCESSFUL_EXIT = 0;
    public static final int WRONG_OUTPUT = 1;
    @Option(names = {"-f", "--format"}, defaultValue = "stylish", paramLabel = "format",
            description = "output format [default: stylish]")
    private String format;
    @Parameters(paramLabel = "filepath1", index = "0", description = "path to first file")
    private String filepath1;
    @Parameters(paramLabel = "filepath2", index = "1", description = "path to second file")
    private String filepath2;

    /**
     * Executes the command to generate the difference between two files.
     *
     * @return the exit code indicating the success or failure of the command
     */

    @Override
    public Integer call() {
        try {
            String result = Differ.generate(filepath1, filepath2, format);
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



