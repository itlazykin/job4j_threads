package ru.job4j.io;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        File file = new File("temp.txt");
        ParseFileWriter writer = new ParseFileWriter(file);
        writer.saveContent("Hello, world! Привет, мир!");
        ParseFileReader reader = new ParseFileReader(file);
        System.out.println("Полное содержимое: " + reader.getContent());
        System.out.println("Только ASCII: " + reader.getContentWithoutUnicode());
    }
}
