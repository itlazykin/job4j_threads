package ru.job4j.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.function.Predicate;

public class ParseFileReader {
    private final File file;

    public ParseFileReader(File file) {
        this.file = file;
    }

    public String content(Predicate<Character> filter) throws IOException {
        StringBuilder output = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            int data;
            while ((data = reader.read()) != -1) {
                char character = (char) data;
                if (filter.test(character)) {
                    output.append(character);
                }
            }
        }
        return output.toString();
    }

    public String getContent() throws IOException {
        return content(character -> true);
    }

    public String getContentWithoutUnicode() throws IOException {
        return content(character -> character < 0x80);
    }
}
