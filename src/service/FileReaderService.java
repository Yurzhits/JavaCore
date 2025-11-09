package service;

import exception.FileProcessingException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderService {

    public List<String> readFileLines(File file) throws FileProcessingException {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            throw new FileProcessingException("Ошибка чтения файла: " + file.getName(), e);
        }

        return lines;
    }

    public File[] findTxtFiles(String directoryPath) {
        File directory = new File(directoryPath);

        if (!directory.exists() || !directory.isDirectory()) {
            return new File[0];
        }

        return directory.listFiles((dir, name) ->
                name.toLowerCase().endsWith(".txt")
        );
    }
}