package ru.job4j.nio.piped;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedUsage {
    public static void main(String[] args) throws IOException {
        final PipedInputStream inputStream = new PipedInputStream();
        final PipedOutputStream outputStream = new PipedOutputStream();
        Thread firstThread = new Thread(() -> {
            try {
                outputStream.write("job4j".getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Thread secondThread = new Thread(() -> {
            try {
                int character;
                while ((character = inputStream.read()) != -1) {
                    System.out.print((char) character);
                }
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        inputStream.connect(outputStream);
        firstThread.start();
        secondThread.start();
    }
}
