import java.util.HashMap;
import java.util.Map;

public class Util {
    /**
     * @param n - it should be greater than 0, signifies nth element of the fibonacci sequence
     *                    n = 1, 2, 3, 4, 5, 6, 7,  8, ........
     *   fibonacci sequence = 1, 1, 2, 3, 5, 8, 13, 21, ......
     * @return value of nth number in fibonacci sequence
     * Time complexity - O(2^n), Space complexity - O(n)
     */
    private long fibonacci(int n){
        if(n <= 2){
            return 1;
        }else{
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
    /**
     * @param n - it should be greater than 0, signifies nth element of the fibonacci sequence
     *                    n = 1, 2, 3, 4, 5, 6, 7,  8, ........
     *   fibonacci sequence = 1, 1, 2, 3, 5, 8, 13, 21, ......
     * @param cache - Used to store values of number found out in fib sequence.
     *    cache - [1,1], [2,1], [3,2], [4,3], [5,5], [6,8], [7,13], [8.21] ....
     * @return value of nth number in fibonacci sequence
     * Time complexity - O(n), Space complexity - O(n)
     */
    private long efficientFibonacci(int n, Map<Integer, Long> cache){
        if(cache.containsKey(n)){
            return cache.get(n);
        } else if(n <= 2){
            return 1;
        }
        long tmp = efficientFibonacci(n - 1, cache) + efficientFibonacci(n - 2, cache);
        cache.put(n, tmp);
        return tmp;
    }

    /**
     * @param row - it should be non-negative, signifies no of rows in a grid
     * @param column - it should be non-negative, signifies no of columns in a grid
     * @return - no of ways to travel from top left to bottom right of the grid.
     * Time complexity - O(2^(n + m)), Space complexity - O(n + m)
     */
    private long gridTraveller(int row, int column){
        if((row == 1 && column == 1) || (row == 1 && column == 2) || (row == 2 && column == 1)){
            return 1;
        } else if(row == 0 || column == 0 ){
            return 0;
        }else{
            return gridTraveller(row, column - 1) + gridTraveller(row - 1, column);
        }
    }

    /**
     * @param row - it should be non-negative, signifies no of rows in a grid
     * @param column - it should be non-negative, signifies no of columns in a grid
     * @return - no of ways to travel from top left to bottom right of the grid.
     * Time complexity - O(n * m), Space complexity - O(n + m)
     */
    private long efficientGridTraveller(int row, int column, Map<String, Long> cache){
        String key = String.valueOf(row) + "x" + column;

        if(cache.containsKey(key)) {
            return cache.get(key);
        } else if((row == 1 && column == 1) || (row == 1 && column == 2) || (row == 2 && column == 1)){
            return 1;
        } else if(row == 0 || column == 0 ){
            return 0;
        }

        long tmp = efficientGridTraveller(row, column - 1, cache) +
                efficientGridTraveller(row - 1, column, cache);
        cache.put(key, tmp);
        return tmp;
    }

    public static void main(String[] args) {
        Util f = new Util();

        long startTime = System.nanoTime();
        System.out.println(f.fibonacci(50));
        long endOfInefficient = System.nanoTime();
        System.out.println(f.efficientFibonacci(50, new HashMap<>()));
        long endOfEfficientFibo = System.nanoTime();

        System.out.println("Time taken for normal fibo - " + (endOfInefficient - startTime) + " nano seconds");
        System.out.println("Time taken for efficient fibo - " + (endOfEfficientFibo - endOfInefficient) + " nano seconds");

        startTime = System.nanoTime();
        System.out.println(f.gridTraveller(18, 18));
        endOfInefficient = System.nanoTime();
        System.out.println(f.efficientGridTraveller(18, 18, new HashMap<>()));
        endOfEfficientFibo = System.nanoTime();

        System.out.println("Time taken for normal - " + (endOfInefficient - startTime) + " nano seconds");
        System.out.println("Time taken for efficient - " + (endOfEfficientFibo - endOfInefficient) + " nano seconds");
    }
}
