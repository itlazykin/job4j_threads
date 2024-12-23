package ru.job4j.ref;

public class ShareNotSafe {
    public static void main(String[] args) throws InterruptedException {
        UserCache cache = new UserCache();
        User user = User.of("main");
        User u = User.of("niam");
        cache.add(user);
        cache.add(u);
        Thread first = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                user.setName("first");
            }
        });
        Thread second = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                user.setName("second");
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
        System.out.println(cache.findById(2).getName());
        System.out.println(cache.findAll());
    }
}
