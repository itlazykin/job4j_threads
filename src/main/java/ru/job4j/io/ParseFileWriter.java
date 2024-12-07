package ru.job4j.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ParseFileWriter {
    private final File file;

    public ParseFileWriter(File file) {
        this.file = file;
    }

    public void saveContent(String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
            writer.write(content);
        }
    }
}
