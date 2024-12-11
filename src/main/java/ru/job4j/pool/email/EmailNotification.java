package ru.job4j.pool.email;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void emailTo(User user) {
        String subject = String.format("Notification %s to email %s", user.username(), user.email());
        String body = String.format("Add a new event to %s", user.username());
        pool.submit(() -> send(subject, body, user.email()));
    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String subject, String body, String email) {
        System.out.println(subject);
        System.out.println(body);
        System.out.println(email);
    }

    public static void main(String[] args) {
        EmailNotification emailNotification = new EmailNotification();
        try {
            emailNotification.emailTo(new User("Alice", "alice@example.com"));
            emailNotification.emailTo(new User("Bob", "bob@example.com"));
        } finally {
            emailNotification.close();
        }
    }
}
