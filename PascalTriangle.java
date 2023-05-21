package patterns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PascalTriangle {
    private static Map<Integer,Long> factorials = new HashMap<>();
    private static long factorial(int n){
        if (n == 0){
            return 1;
        }
        if (factorials.containsKey(n)){
            return factorials.get(n);
        }
        long result = n * factorial(n - 1);
        factorials.put(n,result);
        return result;
    }
    public static void main(String[] args) {
        int rows = 5;
        pascalTriangleIteration(rows);
        pascalTriangleRecursion(rows);
        System.out.println();
        List<List<Integer>> triangle = generatePascalTriangle(rows);

        // Print Pascal's Triangle
        for (List<Integer> row : triangle) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
    public static void pascalTriangleIteration(int row){
        for (int i = 0; i < row; i++) {
            // spacing
            for (int j = 0; j < row - i ; j++) {
                System.out.print(" ");
            }
            //putting numbers
            for (int j = 0; j <= i; j++) {
                //combination
                long fact = factorial(i) / (factorial(i - j) * factorial(j));
                System.out.print(fact + " ");
            }
            System.out.println();
        }
    }
    public static void pascalTriangleRecursion(int rows){
        pascalTriangleRecursion(rows - 1,0);
    }
    private static void pascalTriangleRecursion(int row,int col){
        if (row < 0){
            return;
        }
        if (col <= row){
            // This is for printing in a particular row
            // combination
            pascalTriangleRecursion(row, col + 1);
            long comb = factorial(row) / (factorial(row - col) * factorial(col));
            System.out.print(comb + " ");
        } else {
            pascalTriangleRecursion(row-1,0);
            System.out.println();
        }
    }
    private static Map<Integer, Map<Integer, Integer>> memoCache = new HashMap<>();

    public static List<List<Integer>> generatePascalTriangle(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();

        for (int row = 0; row < numRows; row++) {
            List<Integer> currentRow = new ArrayList<>();

            for (int col = 0; col <= row; col++) {
                int coefficient = computeBinomialCoefficient(row, col);
                currentRow.add(coefficient);
            }

            triangle.add(currentRow);
        }

        return triangle;
    }
    private static int computeBinomialCoefficient(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }

        if (memoCache.containsKey(n) && memoCache.get(n).containsKey(k)) {
            return memoCache.get(n).get(k);
        }

        int coefficient = computeBinomialCoefficient(n - 1, k - 1) + computeBinomialCoefficient(n - 1, k);

        if (!memoCache.containsKey(n)) {
            memoCache.put(n, new HashMap<>());
        }
        memoCache.get(n).put(k, coefficient);

        return coefficient;
    }
}
