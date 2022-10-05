package pl.jiohim.task2;

import java.util.Arrays;

public class Task2 {

    public static void main(String[] args) {
        int[] numbers = new int[]{122, 105, 55, 34, 13, 2, 1};
        int x = 105;
        Task2 t = new Task2();
        boolean result = t.search(numbers, x);
        System.out.println(result);

    }

//binary search : złożoność czasowa O(log n) ,złożoność pamięciowa: O(1) w metodzie iteracyjnej
// i O(log n) w metodzie rekurencyjnej

    private boolean search(int[] numbers, int x) {
        int low = 0;
        int high = numbers.length - 1;

        while (low <= high) {
            int middle = (low + high) / 2;

            if (numbers[middle] == x) {
                return true;
            } else if (numbers[middle] < x) {
                high = middle - 1;
            } else if (numbers[middle] > x) {
                low = middle + 1;

            }
        }
        return false;
    }
}
