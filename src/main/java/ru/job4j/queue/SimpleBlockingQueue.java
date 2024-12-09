package ru.job4j.queue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private final int capacity;

    public SimpleBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void offer(T value) throws InterruptedException {
        synchronized (queue) {
            while (queue.size() == capacity) {
                System.out.println("Buffer is full. Producer is waiting...");
                queue.wait();
            }
            queue.add(value);
            System.out.println("Produced: " + value);
            queue.notify();
        }
    }

    public T poll() throws InterruptedException {
        synchronized (queue) {
            while (queue.isEmpty()) {
                System.out.println("Buffer is empty. Consumer is waiting...");
                queue.wait();
            }
            T value = queue.poll();
            System.out.println("Consumed: " + value);
            queue.notify();
            return value;
        }
    }
}
