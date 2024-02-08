package hexlet.code;

import picocli.CommandLine;

import java.util.Arrays;

@CommandLine.Command
public class App implements Runnable {
    @CommandLine.Option(names = {"-f", "--format=format"}, paramLabel = "format", defaultValue = "", description = "output format [default: stylish]")
    private String format;

    @CommandLine.Option(names = {"-h", "--help"}, description = "Show this help message and exit.")
    private String help;

    @CommandLine.Option(names = {"-v", "--version"}, description = "Print version information and exit.")
    private String version;
    @CommandLine.Parameters(index = "0", description = "path to first file")
    private String filepath1;

    @CommandLine.Parameters(index = "1", description = "path to second file")
    private String filepath2;


    public void run() {
        System.out.println(filepath1 + " path to first file");
        System.out.println(filepath2 + " path to second file");
        System.out.println(format + " output format [default: stylish]");
        System.out.println(help + " Show this help message and exit.");
        System.out.println(version + " Print version information and exit.");
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        new CommandLine(new App()).execute(args);
    }
}
