package functional.execute_around;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteAround {

    @FunctionalInterface
    interface FileProcessor {
        void process(BufferedReader reader) throws IOException;
    }

    public static void processFile(
            String fileName,
            FileProcessor processor
    ) {

        System.out.println("Opening file...");

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(fileName))) {

            processor.process(reader);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Closing file...");
    }

    public static void main(String[] args) {

        processFile("test.txt", reader -> {

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        });
    }
}