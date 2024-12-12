package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RolColSumTest {

    @Test
    void whenSequentialSum() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RolColSum.Sums[] sums = RolColSum.sum(matrix);
        assertThat(sums[0].getRowSum()).isEqualTo(6);
        assertThat(sums[1].getRowSum()).isEqualTo(15);
        assertThat(sums[2].getRowSum()).isEqualTo(24);
        assertThat(sums[0].getColSum()).isEqualTo(12);
        assertThat(sums[1].getColSum()).isEqualTo(15);
        assertThat(sums[2].getColSum()).isEqualTo(18);
    }

    @Test
    void whenAsyncSum() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RolColSum.Sums[] sums = RolColSum.asyncSum(matrix);
        assertThat(sums[0].getRowSum()).isEqualTo(6);
        assertThat(sums[1].getRowSum()).isEqualTo(15);
        assertThat(sums[2].getRowSum()).isEqualTo(24);
        assertThat(sums[0].getColSum()).isEqualTo(12);
        assertThat(sums[1].getColSum()).isEqualTo(15);
        assertThat(sums[2].getColSum()).isEqualTo(18);
    }
}