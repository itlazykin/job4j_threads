package ru.job4j.pool;

import ru.job4j.queue.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * Класс ThreadPool реализует пул потоков, который управляет фиксированным количеством потоков
 * и распределяет задачи (объекты Runnable) между ними.
 * Потоки берут задачи из блокирующей очереди SimpleBlockingQueue и выполняют их.
 * Размер пула потоков, равный количеству доступных процессоров - private final int size.
 */
public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(5);
    private final int size = Runtime.getRuntime().availableProcessors();

    public ThreadPool() {
        for (int i = 0; i < size; i++) {
            Thread thread = new Thread(() -> {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        Runnable task = tasks.poll();
                        if (task != null) {
                            task.run();
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            threads.add(thread);
            thread.start();
        }
    }

    /**
     * Добавляет новую задачу в очередь задач.
     * Если очередь заполнена, метод блокируется до тех пор, пока не освободится место.
     *
     * @param job задача, которую необходимо выполнить.
     * @throws InterruptedException если текущий поток был прерван.
     */
    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    /**
     * Завершает работу всех потоков в пуле.
     * Потоки прерываются, и метод ожидает их завершения.
     */
    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}
