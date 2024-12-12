package ru.job4j.pools;

import java.util.concurrent.CompletableFuture;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }
    }

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
        Sums sums = new Sums();
        int rowSum = 0;
        int colSum = 0;
        for (int j = 0; j < matrix.length; j++) {
            rowSum += matrix[index][j];
            colSum += matrix[j][index];
        }
        sums.setRowSum(rowSum);
        sums.setColSum(colSum);
        return sums;
    }
}


