package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " Start");
        Thread first = new Thread(
                () -> {
                }
        );
        Thread second = new Thread(
                () -> {
                }
        );
        System.out.println("first before while " + first.getState());
        System.out.println("second before while " + second.getState());
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED || second.getState() != Thread.State.TERMINATED) {
            System.out.println("first state " + first.getState());
            System.out.println("second state " + second.getState());
        }
        System.out.println("first after while " + first.getState());
        System.out.println("second after while " + second.getState());
        System.out.println(Thread.currentThread().getName() + " End");
    }
}
