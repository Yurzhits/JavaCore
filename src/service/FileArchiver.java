package service;

import exception.FileProcessingException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileArchiver {

    private final String archiveDir;

    public FileArchiver(String archiveDir) {
        this.archiveDir = archiveDir;
        isArchiveDirectoryExists();
    }

    private void isArchiveDirectoryExists() {
        File archiveDirectory = new File(archiveDir);
        if (!archiveDirectory.exists()) {
            archiveDirectory.mkdirs();
        }
    }

    public void moveToArchive(File file) throws FileProcessingException {
        try {
            Path source = file.toPath();
            Path target = Paths.get(archiveDir, file.getName());
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING); //замена
        } catch (IOException e) {
            throw new FileProcessingException("Не удалось переместить файл: " + file.getName(), e);
        }
    }
}