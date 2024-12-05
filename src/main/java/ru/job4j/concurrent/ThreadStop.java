package ru.job4j.concurrent;

public class ThreadStop {
    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(
                () -> {
                    /*
                     * Эта схема является шаблоном. Запомните, если используются методы sleep(), join(), wait() или
                     * аналогичные временно блокирующие поток методы, то нужно в блоке catch вызвать прерывание.
                     */
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println("start...");
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        progress.start();
        Thread.sleep(1000);
        progress.interrupt();
        progress.join();
    }
}
