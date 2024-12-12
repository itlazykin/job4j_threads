package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.job4j.pools.ParallelSearch.search;

class ParallelSearchTest {
    @Test
    public void whenStringShortArrayThanFindIndex() {
        String[] array = new String[]{"aa", "bb", "cc", "dd", "ee"};
        String object = "cc";
        assertThat(search(array, object)).isEqualTo(2);
    }

    @Test
    public void whenStringShortArrayThanNotFindIndex() {
        String[] array = new String[]{"aa", "bb", "cc", "dd", "ee"};
        String object = "ff";
        assertThat(search(array, object)).isEqualTo(-1);
    }

    @Test
    public void whenIntegerRecursiveArrayThanFindIndex() {
        Integer[] array = new Integer[]{11, -8, 3, 1, 16, -44, 5, 32, 4, 22, -15, 0, 7, 19};
        int object = -15;
        assertThat(search(array, object)).isEqualTo(10);
    }

    @Test
    public void whenFloatRecursiveArrayThanIndex() {
        Float[] array = new Float[]{1.1f, -83.4f, 3f, 1f, 1.16f, -44f, 5f, 32f, 4f, 22f, -0.15f, 0f, 7f, 2.19f};
        float object = -0.15f;
        assertThat(search(array, object)).isEqualTo(10);
    }

    @Test
    public void whenLastIndexThanOk() {
        Integer[] array = new Integer[]{11, -8, 3, 1, 16, -44, 5, 32, 4, 22, -15, 0, 7, 19};
        int object = 19;
        assertThat(search(array, object)).isEqualTo(13);
    }
}