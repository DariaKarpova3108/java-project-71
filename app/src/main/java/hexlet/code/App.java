package hexlet.code;

import picocli.CommandLine;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true, version = "",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<String> {
    @CommandLine.Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format;

    @CommandLine.Parameters(index = "0", description = "path to first file")
    private File filepath1;

    @CommandLine.Parameters(index = "1", description = "path to second file")
    private File filepath2;

    @Override
    public String call() throws Exception {
        String contentFomFirstFile = new String(Files.readAllBytes(Paths.get(filepath1.toURI())));
        String contentFromSecondFile = new String(Files.readAllBytes(Paths.get(filepath2.toURI())));
        String result = "";

        if(contentFomFirstFile.length()==0 && contentFromSecondFile.length()==0) {
            System.out.println("");
        }

        for (int i = 0; i < contentFomFirstFile.length(); i++) {
            for (int k = 0; k < contentFromSecondFile.length(); k++) {
                if (contentFomFirstFile.charAt(i) != contentFromSecondFile.charAt(k)) {
                    result = "" + contentFomFirstFile.charAt(i);
                }
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int gendiff = new CommandLine(new App()).execute(args);
        System.exit(gendiff);
    }
}

