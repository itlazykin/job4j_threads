package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(5000);
        progress.interrupt();
        progress.join();
    }

    @Override
    public void run() {
        var process = new char[]{'-', '\\', '|', '/' };
        while (!Thread.currentThread().isInterrupted()) {
            try {
                for (char c : process) {
                    System.out.print("\r load: " + c);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
