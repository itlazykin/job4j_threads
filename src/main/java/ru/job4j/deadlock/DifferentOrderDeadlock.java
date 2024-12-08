package ru.job4j.deadlock;

public class DifferentOrderDeadlock {
    private final Object resource1 = new Object();
    private final Object resource2 = new Object();

    public void runExample() {
        Thread thread1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: Захватил ресурс 1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resource2) {
                    System.out.println("Thread 1: Захватил ресурс 2");
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println("Thread 2: Захватил ресурс 2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resource1) {
                    System.out.println("Thread 2: Захватил ресурс 1");
                }
            }
        });
        thread1.start();
        thread2.start();
    }

    public static void main(String[] args) {
        new DifferentOrderDeadlock().runExample();
    }
}
