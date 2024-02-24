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
    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format;
    @Parameters(index = "0", description = "path to first file")
    private String filepath1;
    @Parameters(index = "1", description = "path to second file")
    private String filepath2;

    @Override
    public Integer call() {
        try {
            String result;
            switch (format) {
                case "json":
                    result = Differ.generate(Parser.parsJson(filepath1, filepath2), format);
                    System.out.println(result);
                    break;
                case "yaml":
                    result = Differ.generate(Parser.parsYml(filepath1, filepath2), format);
                    System.out.println(result);
                    break;
                default:
                    result = Differ.generate(filepath1, filepath2);
                    System.out.println(result);
            }
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



