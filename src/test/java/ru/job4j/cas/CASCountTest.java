package ru.job4j.cas;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class CASCountTest {
    @Test
    void whenTreeThreadsWitch30To90() throws InterruptedException {
        CASCount counter = new CASCount();
        Thread t1 = new Thread(() ->
                IntStream.range(0, 30).forEach(i -> counter.increment())
        );
        Thread t2 = new Thread(() ->
                IntStream.range(0, 30).forEach(i -> counter.increment())
        );
        Thread t3 = new Thread(() ->
                IntStream.range(0, 30).forEach(i -> counter.increment())
        );
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        assertThat(counter.get()).isEqualTo(90);
    }
}