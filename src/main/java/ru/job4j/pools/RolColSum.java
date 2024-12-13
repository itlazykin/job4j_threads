package ru.job4j.pools;

import java.util.concurrent.CompletableFuture;

public class RolColSum {
    public static Sums[] sum(int[][] matrix) {
        int size = matrix.length;
        Sums[] result = new Sums[size];
        for (int i = 0; i < size; i++) {
            result[i] = calculateSums(matrix, i);
        }
        return result;
    }

    public static Sums[] asyncSum(int[][] matrix) {
        int size = matrix.length;
        CompletableFuture<Sums>[] futures = new CompletableFuture[size];
        for (int i = 0; i < size; i++) {
            int row = i;
            futures[row] = CompletableFuture.supplyAsync(() -> calculateSums(matrix, row));
        }
        Sums[] result = new Sums[size];
        for (int i = 0; i < size; i++) {
            result[i] = futures[i].join();
        }
        return result;
    }

    private static Sums calculateSums(int[][] matrix, int index) {
        int rowSum = 0;
        int colSum = 0;
        for (int j = 0; j < matrix.length; j++) {
            rowSum += matrix[index][j];
            colSum += matrix[j][index];
        }
        return new Sums(rowSum, colSum);
    }
}


