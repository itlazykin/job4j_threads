package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearch<T> extends RecursiveTask<Integer> {
    private final T[] objects;
    private final T object;
    private final int from;
    private final int to;

    public ParallelSearch(T[] objects, T object, int from, int to) {
        this.objects = objects;
        this.object = object;
        this.from = from;
        this.to = to;
    }

    private int indexSearch() {
        int index = -1;
        for (int i = from; i < to; i++) {
            if (object.equals(objects[i])) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static <T> int search(T[] objects, T object) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelSearch<>(objects, object, 0, objects.length - 1));
    }

    @Override
    protected Integer compute() {
        if (to - from <= 10) {
            return indexSearch();
        }
        int middle = (from + to) / 2;
        ParallelSearch<T> leftSort = new ParallelSearch<>(objects, object, from, middle);
        ParallelSearch<T> rightSort = new ParallelSearch<>(objects, object, middle + 1, to);
        leftSort.fork();
        rightSort.fork();
        int left = leftSort.join();
        int right = rightSort.join();
        return Math.max(left, right);
    }
}
